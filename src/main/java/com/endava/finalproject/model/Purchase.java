package com.endava.finalproject.model;

import com.endava.finalproject.common.enums.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Purchase extends BaseEntity{

    @NotBlank
    private String supermarketId;

    @NotEmpty
    @ManyToMany
    @JoinTable(name = "purchase_items",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private double cashAmount;

    @NotNull
    private double totalPrice;

    private double changeMoney;

    private LocalDate executedPayment;

}
