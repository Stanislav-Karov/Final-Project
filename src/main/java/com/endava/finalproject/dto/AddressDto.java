package com.endava.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import static com.endava.finalproject.common.ValidationMessages.*;

@Getter
@Setter
public class AddressDto {

    @NotBlank(message = PROVIDE_VALID_CITY_NAME)
    private String city;

    @NotBlank(message = PROVIDE_VALID_STREET_NAME)
    private String street;

    @NotBlank(message = PROVIDE_VALID_STREET_NUMBER)
    private String streetNumber;
}
