package com.endava.finalproject.dto;

import com.endava.finalproject.common.enums.ItemType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import static com.endava.finalproject.common.ValidationMessages.*;

@Getter
@Setter
public class ItemDto {

    @NotBlank(message = PROVIDE_VALID_NAME)
    @Size(max = 64)
    private String name;

    @NotNull(message = PROVIDE_VALID_PRICE)
    @Positive
    private double price;

    @NotNull(message = PROVIDE_VALID_ITEM_TYPE)
    @Enumerated(EnumType.STRING)
    private ItemType itemType;
}
