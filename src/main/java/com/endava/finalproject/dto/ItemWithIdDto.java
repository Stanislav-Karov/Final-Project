package com.endava.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemWithIdDto extends ItemDto {

    @NotNull
    private String id;
}
