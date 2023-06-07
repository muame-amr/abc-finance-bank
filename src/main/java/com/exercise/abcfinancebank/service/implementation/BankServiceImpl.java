package com.exercise.abcfinancebank.service.implementation;

import com.exercise.abcfinancebank.dao.BankDAO;
import com.exercise.abcfinancebank.model.Bank;
import com.exercise.abcfinancebank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BankServiceImpl implements BankService {

    @Autowired
    private BankDAO bankDAO;

    @Override
    public Page<Bank> getAllBanks(Pageable pageable) {
        return bankDAO.getAllBanks(pageable);
    }

    @Override
    public Optional<Bank> getBankById(Long id) {
        return bankDAO.getBankById(id);
    }

    @Override
    public Bank addBank(Bank bank) {
        return bankDAO.addBank(bank);
    }

    @Override
    public Bank updateBank(Bank bank) {
        return bankDAO.updateBank(bank);
    }

    @Override
    public void deleteBank(Long id) {
        bankDAO.deleteBank(id);
    }
}
