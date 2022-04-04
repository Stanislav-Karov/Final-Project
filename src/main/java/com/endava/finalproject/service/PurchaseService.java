package com.endava.finalproject.service;

import com.endava.finalproject.dto.PurchaseRequestDto;
import com.endava.finalproject.dto.PurchaseResponseDto;
import com.endava.finalproject.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.io.Writer;

public interface PurchaseService {

    PurchaseResponseDto buyItemsFromSupermarket(PurchaseRequestDto purchaseRequestDto);

    Page<Purchase> findAll(Pageable pageable);

    void exportPurchasesToCSV(Writer writer, Pageable pageable) throws IOException;
}
