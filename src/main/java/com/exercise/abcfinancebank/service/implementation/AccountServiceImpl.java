package com.exercise.abcfinancebank.service.implementation;

import com.exercise.abcfinancebank.dao.AccountDAO;
import com.exercise.abcfinancebank.model.Account;
import com.exercise.abcfinancebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountDAO.getAllAccounts(pageable);
    }

    @Override
    public Page<Account> getAllAccountsByBankBranchCode(int branchCode, Pageable pageable) {
        return accountDAO.getAllAccountsByBankBranchCode(branchCode, pageable);
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountDAO.getAccountById(id);
    }

    @Override
    public Optional<Account> getAccountByIdAndBankBranchCode(Long id, int branchCode) {
        return accountDAO.getAccountByIdAndBankBranchCode(id, branchCode);
    }

    @Override
    public Account addAccount(Account account) {
        return accountDAO.addAccount(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountDAO.updateAccount(account);
    }

    @Override
    public void deleteAccount(Account account) {
        accountDAO.deleteAccount(account);
    }
}
