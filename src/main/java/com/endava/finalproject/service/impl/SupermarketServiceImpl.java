package com.endava.finalproject.service.impl;

import com.endava.finalproject.dto.SupermarketItemsIdsDto;
import com.endava.finalproject.dto.SupermarketItemsNamesDto;
import com.endava.finalproject.exception.ItemNotFoundException;
import com.endava.finalproject.exception.SupermarketAlreadyExistException;
import com.endava.finalproject.exception.SupermarketNotFoundException;
import com.endava.finalproject.model.Item;
import com.endava.finalproject.model.Supermarket;
import com.endava.finalproject.repository.ItemRepository;
import com.endava.finalproject.repository.SupermarketRepository;
import com.endava.finalproject.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.endava.finalproject.common.ExceptionMessages.*;

@Service
public class SupermarketServiceImpl implements SupermarketService {

    private final SupermarketRepository supermarketRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public SupermarketServiceImpl(SupermarketRepository supermarketRepository, ItemRepository itemRepository) {
        this.supermarketRepository = supermarketRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Supermarket createSupermarket(Supermarket supermarket) {
        if (supermarketRepository.existsByName(supermarket.getName())) {
            throw new SupermarketAlreadyExistException(String.format(SUPERMARKET_ALREADY_EXIST, supermarket.getName()));
        }
        return supermarketRepository.save(supermarket);
    }

    @Override
    public SupermarketItemsNamesDto addItemsToSupermarket(SupermarketItemsIdsDto supermarketItemsIdsDto) {
        List<Item> itemsToAdd = new ArrayList<>();
        for (String itemId : supermarketItemsIdsDto.getItemIds()) {
            Item item = itemRepository.findById(itemId).orElseThrow(() -> {
                throw new ItemNotFoundException(String.format(ITEM_NOT_FOUND, itemId));
            });
            itemsToAdd.add(item);
        }

        Supermarket supermarket =
                supermarketRepository.findById(supermarketItemsIdsDto.getSupermarketId())
                        .orElseThrow(() -> {
                            throw new SupermarketNotFoundException(
                                    String.format(SUPERMARKET_NOT_FOUND, supermarketItemsIdsDto.getSupermarketId()));
                        });

        supermarket.setItems(itemsToAdd);
        supermarketRepository.save(supermarket);

        List<String> itemNames = itemsToAdd.stream().map(Item::getName).collect(Collectors.toList());

        return new SupermarketItemsNamesDto(supermarketItemsIdsDto.getSupermarketId(), itemNames);
    }

    @Override
    public Supermarket getById(String id) {
        return supermarketRepository.findById(id).orElseThrow(() -> {
            throw new SupermarketNotFoundException(String.format(SUPERMARKET_NOT_FOUND, id));
        });
    }
}
