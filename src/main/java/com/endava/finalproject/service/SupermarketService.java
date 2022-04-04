package com.endava.finalproject.service;

import com.endava.finalproject.dto.SupermarketItemsIdsDto;
import com.endava.finalproject.dto.SupermarketItemsNamesDto;
import com.endava.finalproject.model.Supermarket;

public interface SupermarketService {

    Supermarket createSupermarket(Supermarket supermarket);

    SupermarketItemsNamesDto addItemsToSupermarket(SupermarketItemsIdsDto supermarketItemsIdsDto);

    Supermarket getById(String id);

}
