package com.endava.finalproject.dto;

import com.endava.finalproject.model.Item;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PurchaseDto {

    @NotBlank
    private String supermarketId;

    private List<Item> items = new ArrayList<>();

    @NotNull
    private double totalPrice;

    private LocalDate executedPayment;
}
