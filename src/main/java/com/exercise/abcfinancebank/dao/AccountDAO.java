package com.exercise.abcfinancebank.dao;

import com.exercise.abcfinancebank.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AccountDAO {
    Page<Account> getAllAccounts(Pageable pageable);

    Page<Account> getAllAccountsByBankBranchCode(int branchCode, Pageable pageable);

    Optional<Account> getAccountById(Long id);

    Optional<Account> getAccountByIdAndBankBranchCode(Long id, int branchCode);

    Account addAccount(Account account);

    Account updateAccount(Account account);

    void deleteAccount(Account account);
}
