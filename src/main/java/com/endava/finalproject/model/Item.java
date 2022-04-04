package com.endava.finalproject.model;


import com.endava.finalproject.common.enums.ItemType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item extends BaseEntity{

    @NotBlank
    private String name;

    @NotNull
    private double price;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;
}
