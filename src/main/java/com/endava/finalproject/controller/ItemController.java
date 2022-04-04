package com.endava.finalproject.controller;

import com.endava.finalproject.dto.ItemDto;
import com.endava.finalproject.model.Item;
import com.endava.finalproject.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ModelMapper modelMapper;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
        modelMapper = new ModelMapper();
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
        Item newItem = modelMapper.map(itemDto, Item.class);
        ItemDto createdItem = modelMapper.map(itemService.createItem(newItem), ItemDto.class);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }
}
