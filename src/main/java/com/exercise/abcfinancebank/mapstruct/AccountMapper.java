package com.exercise.abcfinancebank.mapstruct;

import com.exercise.abcfinancebank.dto.AccountDTO;
import com.exercise.abcfinancebank.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO accountToAccountDTO(Account account);

    Account accountDTOToAccount(AccountDTO accountDTO);

    void updateAccountFromDTO(AccountDTO accountDTO, @MappingTarget Account account);
}
