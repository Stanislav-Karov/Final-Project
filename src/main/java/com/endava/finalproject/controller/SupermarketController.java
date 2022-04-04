package com.endava.finalproject.controller;

import com.endava.finalproject.dto.SupermarketItemsIdsDto;
import com.endava.finalproject.dto.SupermarketDto;
import com.endava.finalproject.dto.SupermarketItemsNamesDto;
import com.endava.finalproject.model.Supermarket;
import com.endava.finalproject.service.SupermarketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/supermarkets")
public class SupermarketController {

    private final SupermarketService supermarketService;
    private final ModelMapper modelMapper;

    @Autowired
    public SupermarketController(SupermarketService supermarketService) {
        this.supermarketService = supermarketService;
        modelMapper = new ModelMapper();
    }

    @PostMapping
    public ResponseEntity<SupermarketDto> createSupermarket(@Valid @RequestBody SupermarketDto supermarketDto) {
        Supermarket newSupermarket = modelMapper.map(supermarketDto, Supermarket.class);
        SupermarketDto createdSupermarket = modelMapper.map(supermarketService.createSupermarket(newSupermarket),
                SupermarketDto.class);
        return new ResponseEntity<>(createdSupermarket, HttpStatus.CREATED);
    }

    @PostMapping("/add-items")
    public ResponseEntity<SupermarketItemsNamesDto> addItemsToSupermarket(@Valid @RequestBody SupermarketItemsIdsDto supermarketItemsIdsDto) {
        return ResponseEntity.ok(supermarketService.addItemsToSupermarket(supermarketItemsIdsDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupermarketDto> findById(@PathVariable(name = "id") String id) {
        SupermarketDto supermarketDto = modelMapper.map(supermarketService.getById(id), SupermarketDto.class);
        return ResponseEntity.ok(supermarketDto);
    }
}

