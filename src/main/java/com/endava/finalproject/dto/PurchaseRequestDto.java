package com.endava.finalproject.dto;

import com.endava.finalproject.common.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static com.endava.finalproject.common.ValidationMessages.PROVIDE_VALID_PAYMENT_TYPE;

@Getter
@Setter
public class PurchaseRequestDto extends SupermarketItemsIdsDto {

    @NotNull(message = PROVIDE_VALID_PAYMENT_TYPE)
    private PaymentType paymentType;

    private double cashAmount;
}
