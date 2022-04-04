package com.endava.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static com.endava.finalproject.common.ValidationMessages.PROVIDE_VALID_TOTAL_PRICE;

@Getter
@Setter
public class PurchaseResponseDto {

    @NotNull(message = PROVIDE_VALID_TOTAL_PRICE)
    private double totalPrice;

    private double changeMoney;

    private LocalDate executedPayment;
}
