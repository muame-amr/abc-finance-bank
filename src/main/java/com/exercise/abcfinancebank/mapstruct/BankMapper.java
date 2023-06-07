package com.exercise.abcfinancebank.mapstruct;

import com.exercise.abcfinancebank.dto.BankDTO;
import com.exercise.abcfinancebank.model.Bank;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    BankDTO bankToBankDTO(Bank bank);
    Bank bankDTOToBank(BankDTO bankDTO);
}
