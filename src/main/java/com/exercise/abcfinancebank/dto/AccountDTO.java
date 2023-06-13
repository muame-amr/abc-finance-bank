package com.exercise.abcfinancebank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private Long id;
    private int accountNumber;
    private float value;
    private BankDTO bank;
}
