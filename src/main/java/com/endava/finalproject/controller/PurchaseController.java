package com.endava.finalproject.controller;

import com.endava.finalproject.dto.PurchaseDto;
import com.endava.finalproject.dto.PurchaseRequestDto;
import com.endava.finalproject.dto.PurchaseResponseDto;
import com.endava.finalproject.service.PurchaseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final ModelMapper modelMapper;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
        modelMapper = new ModelMapper();
    }

    @PostMapping
    public ResponseEntity<PurchaseResponseDto> buyItemsFromSupermarket(@Valid @RequestBody PurchaseRequestDto purchaseRequestDto) {
        PurchaseResponseDto purchaseResponseDto = purchaseService.buyItemsFromSupermarket(purchaseRequestDto);
        return new ResponseEntity<>(purchaseResponseDto, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<Page<PurchaseDto>> getAll(Pageable pageable) {

        return new ResponseEntity<>(new PageImpl<>(purchaseService.findAll(pageable).stream()
                .map(purchase -> modelMapper.map(purchase, PurchaseDto.class))
                .collect(Collectors.toList())),
                HttpStatus.OK);

    }

    @GetMapping("/exportCSV")
    public void exportPurchasesToCSV(HttpServletResponse servletResponse, Pageable pageable) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"purchases.csv\"");
        purchaseService.exportPurchasesToCSV(servletResponse.getWriter(), pageable);
    }
}
