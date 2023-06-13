package com.exercise.abcfinancebank.dao.implementation;

import com.exercise.abcfinancebank.dao.BankDAO;
import com.exercise.abcfinancebank.model.Bank;
import com.exercise.abcfinancebank.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BankDAOImpl implements BankDAO {

    @Autowired
    private BankRepository bankRepository;

    public Page<Bank> getAllBanks(Pageable pageable) {
        return bankRepository.findAll(pageable);
    }

    @Override
    public Optional<Bank> getBankById(Long id) {
        return bankRepository.findById(id);
    }

    @Override
    public Optional<Bank> getBankByBranchCode(int branchCode) {
        return bankRepository.findByBranchCode(branchCode);
    }

    @Override
    public Bank addBank(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Bank updateBank(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long bankId) {
        return bankRepository.existsById(bankId);
    }

    @Override
    public boolean existsByBranchCode(int branchCode) {
        return bankRepository.existsByBranchCode(branchCode);
    }
}
