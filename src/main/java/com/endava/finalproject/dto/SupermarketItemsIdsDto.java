package com.endava.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.endava.finalproject.common.ValidationMessages.PROVIDE_VALID_LIST_OF_ITEM_IDS;
import static com.endava.finalproject.common.ValidationMessages.PROVIDE_VALID_SUPERMARKET_ID;

@Getter
@Setter
public class SupermarketItemsIdsDto {

    @NotNull(message = PROVIDE_VALID_SUPERMARKET_ID)
    private String supermarketId;

    @NotEmpty(message = PROVIDE_VALID_LIST_OF_ITEM_IDS)
    private List<String> itemIds = new ArrayList<>();
}
