package com.exercise.abcfinancebank.repository;

import com.exercise.abcfinancebank.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Page<Account> findByBankId(Long bankId, Pageable pageable);
    Optional<Account> findByIdAndBankId(Long id, Long bankId);
}
