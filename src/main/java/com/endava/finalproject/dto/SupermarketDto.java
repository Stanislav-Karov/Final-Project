package com.endava.finalproject.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static com.endava.finalproject.common.ValidationMessages.*;

@Getter
@Setter
public class SupermarketDto {

    @NotBlank(message = PROVIDE_VALID_NAME)
    @Column(unique = true)
    @Size(max = 64)
    private String name;

    @Valid
    @NotNull(message = PROVIDE_VALID_ADDRESS)
    private AddressDto address;

    //This regex matches phone numbers in format: "087xxxxxxx", "088xxxxxxx" or "089xxxxxxx"
    @Pattern(regexp = "^08[7-9][0-9]{7}$", message = PROVIDE_VALID_PHONE_NUMBER)
    private String phoneNumber;

    //This regex matches working hours in 24h format separeted by "-": {HH:mm}-{HH:mm}
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]$",
            message = PROVIDE_VALID_WORKING_HOURS)
    private String workingHours;

    private List<ItemWithIdDto> items = new ArrayList<>();
}