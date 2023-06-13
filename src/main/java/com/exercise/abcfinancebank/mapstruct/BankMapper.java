package com.exercise.abcfinancebank.mapstruct;

import com.exercise.abcfinancebank.dto.BankDTO;
import com.exercise.abcfinancebank.model.Bank;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankMapper {
    BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

    BankDTO bankToBankDTO(Bank bank);

    Bank bankDTOToBank(BankDTO bankDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBankFromDTO(BankDTO bankDTO, @MappingTarget Bank bank);
}
