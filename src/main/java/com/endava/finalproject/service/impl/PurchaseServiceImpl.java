package com.endava.finalproject.service.impl;

import com.endava.finalproject.common.enums.PaymentType;
import com.endava.finalproject.dto.PurchaseRequestDto;
import com.endava.finalproject.dto.PurchaseResponseDto;
import com.endava.finalproject.exception.ItemNotFoundException;
import com.endava.finalproject.exception.NotEnoughCashException;
import com.endava.finalproject.model.Item;
import com.endava.finalproject.model.Purchase;
import com.endava.finalproject.repository.ItemRepository;
import com.endava.finalproject.repository.PurchaseRepository;
import com.endava.finalproject.service.PurchaseService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.endava.finalproject.common.ExceptionMessages.*;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ItemRepository itemRepository) {
        this.purchaseRepository = purchaseRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public PurchaseResponseDto buyItemsFromSupermarket(PurchaseRequestDto purchaseRequestDto) {
        double totalSum = 0.0;
        List<Item> items = new ArrayList<>();
        PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto();
        for (String itemId : purchaseRequestDto.getItemIds()) {
            Item item = itemRepository.findById(itemId).orElseThrow(() -> {
                throw new ItemNotFoundException(String.format(ITEM_NOT_FOUND, itemId));
            });
            items.add(item);
            totalSum += item.getPrice();
        }
        if (purchaseRequestDto.getPaymentType() == PaymentType.CASH) {
            purchaseResponseDto.setChangeMoney(Double.parseDouble(String.format("%.2f",
                    purchaseRequestDto.getCashAmount() - totalSum)));
        }

        if (purchaseRequestDto.getCashAmount() < totalSum) {
            throw new NotEnoughCashException(String.format(NOT_ENOUGH_CASH,
                    Math.abs(totalSum - purchaseRequestDto.getCashAmount())));
        }
        purchaseResponseDto.setTotalPrice(totalSum);
        purchaseResponseDto.setExecutedPayment(LocalDate.now());

        Purchase purchase = new Purchase();
        purchase.setSupermarketId(purchaseRequestDto.getSupermarketId());
        purchase.setChangeMoney(Double.parseDouble(String.format("%.2f",
                purchaseRequestDto.getCashAmount() - totalSum)));
        purchase.setExecutedPayment(LocalDate.now());
        purchase.setItems(items);
        purchase.setPaymentType(purchaseRequestDto.getPaymentType());
        purchase.setCashAmount(purchaseRequestDto.getCashAmount());
        purchase.setTotalPrice(totalSum);
        purchaseRepository.save(purchase);

        return purchaseResponseDto;
    }

    @Override
    public Page<Purchase> findAll(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }

    @Override
    public void exportPurchasesToCSV(Writer writer, Pageable pageable) throws IOException {
        Page<Purchase> purchases = purchaseRepository.findAll(pageable);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Id", "Supermarket Id", "Items", "Payment Type", "Cash Amount", "Total Price",
                    "Change Money", "Executed Payment");
            for (Purchase purchase : purchases) {
                csvPrinter.printRecord(purchase.getId(), purchase.getSupermarketId(), purchase.getItems(),
                        purchase.getPaymentType(), purchase.getCashAmount(),
                        purchase.getTotalPrice(), purchase.getChangeMoney(), purchase.getExecutedPayment());
            }
        } catch (IOException exception) {
            throw new IOException(CANNOT_EXPORT_CSV);
        }

    }
}
