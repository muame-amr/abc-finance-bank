package com.exercise.abcfinancebank.service;

import com.exercise.abcfinancebank.model.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BankService {
    Page<Bank> getAllBanks(Pageable pageable);
    Optional<Bank> getBankById(Long id);
    Optional<Bank> getBankByBranchCode(int branchCode);
    Bank addBank(Bank bank);
    Bank updateBank(Bank bank);
    void deleteBank(Long id);
    boolean existsById(Long bankId);
    boolean existsByBranchCode(int branchCode);
}
