package com.exercise.abcfinancebank.dao.implementation;

import com.exercise.abcfinancebank.dao.AccountDAO;
import com.exercise.abcfinancebank.model.Account;
import com.exercise.abcfinancebank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountDAOImpl implements AccountDAO {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Page<Account> getAllAccountsByBankBranchCode(int branchCode, Pageable pageable) {
        return accountRepository.findByBankBranchCode(branchCode, pageable);
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }


    @Override
    public Optional<Account> getAccountByIdAndBankBranchCode(Long id, int branchCode) {
        return accountRepository.findByIdAndBankBranchCode(id, branchCode);
    }

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }
}
