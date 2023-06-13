package com.exercise.abcfinancebank.repository;

import com.exercise.abcfinancebank.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    public Optional<Bank> findByBranchCode(int branchCode);

    public Boolean existsByBranchCode(int branchCode);
}
