package com.exercise.abcfinancebank;

import com.exercise.abcfinancebank.dto.BankDTO;
import com.exercise.abcfinancebank.mapstruct.BankMapper;
import com.exercise.abcfinancebank.model.Bank;

public class TestMapper {
    public static void main(String[] args) {
        BankMapper mapper = BankMapper.INSTANCE;
        Bank bank = new Bank();
        bank.setId(6L);
        bank.setName("ABC");
        bank.setBranchCode(101);
        BankDTO bankDTO = mapper.bankToBankDTO(bank);
        System.out.println(bankDTO.getId());
        System.out.println(bankDTO.getName());
        System.out.println(bankDTO.getBranchCode());
    }
}
