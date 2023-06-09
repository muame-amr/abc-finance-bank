package com.exercise.abcfinancebank.controller;

import com.exercise.abcfinancebank.dto.BankDTO;
import com.exercise.abcfinancebank.exception.ResourceNotFoundException;
import com.exercise.abcfinancebank.mapstruct.BankMapper;
import com.exercise.abcfinancebank.model.Bank;
import com.exercise.abcfinancebank.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/banks")
public class BankController {
    private final BankService bankService;
    private final BankMapper mapper = BankMapper.INSTANCE;

    @GetMapping("/")
    public ResponseEntity<?> getAllBanks(Pageable pageable) {
        log.info("[getAllBanks] Fetching All Records ...");
        return ResponseEntity.ok(bankService.getAllBanks(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBankById(@PathVariable("id") Long id) {
        log.info("[getBankById] Fetching Bank with Id=" + id);
        return ResponseEntity.ok(bankService.getBankById(id));
    }

    @GetMapping
    public ResponseEntity<?> getBankByBranchCode(@RequestParam("brnchCd") int branchCode) {
        log.info("[getBankByBranchCode] Fetching Bank with BranchCode=" + branchCode);
        return ResponseEntity.ok(bankService.getBankByBranchCode(branchCode));
    }

    @PostMapping("/")
    public ResponseEntity<?> createBank(@Valid @RequestBody BankDTO bankRequest) throws Exception {
        log.info("[createBank] Added New Bank=" + bankRequest.getName());
        Bank bank = mapper.bankDTOToBank(bankRequest);
        try {
            Bank res = bankService.addBank(bank);
            BankDTO bankResponse = mapper.bankToBankDTO(res);
            return ResponseEntity.status(HttpStatus.CREATED).body(bankResponse);
        } catch (Exception ex) {
            throw new Exception(ex.toString());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBank(@PathVariable("id") Long id, @Valid @RequestBody BankDTO
            bankRequest) {
        log.info("[updateBank] Update bank with bankId=" + id + " ...");
        return bankService.getBankById(id).map(bank -> {
            mapper.updateBankFromDTO(bankRequest, bank);
            return ResponseEntity.ok(mapper.bankToBankDTO(bankService.updateBank(bank)));
        }).orElseThrow(() -> new ResourceNotFoundException("[UpdateBank] BankId: " +
                id + " not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBank(@PathVariable("id") Long id) {
        log.info("[deleteBank] Delete bank with bankId=" + id + " ...");
        return bankService.getBankById(id).map(bank -> {
            bankService.deleteBank(bank.getId());
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("BankId " +
                id + " not found"));
    }

}
