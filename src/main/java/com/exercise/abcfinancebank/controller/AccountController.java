package com.exercise.abcfinancebank.controller;

import com.exercise.abcfinancebank.dto.AccountDTO;
import com.exercise.abcfinancebank.dto.BankDTO;
import com.exercise.abcfinancebank.exception.ResourceNotFoundException;
import com.exercise.abcfinancebank.mapstruct.AccountMapper;
import com.exercise.abcfinancebank.mapstruct.BankMapper;
import com.exercise.abcfinancebank.model.Account;
import com.exercise.abcfinancebank.service.AccountService;
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
@RequestMapping("/api/v1")
public class AccountController {
    private final AccountService accountService;
    private final BankService bankService;
    private final AccountMapper accountMapper = AccountMapper.INSTANCE;
    private final BankMapper bankMapper = BankMapper.INSTANCE;

    private final String ACC_ENDPOINT = "/accounts";
    private final String BANK_ENDPOINT = "/banks";

    @GetMapping(ACC_ENDPOINT)
    public ResponseEntity<?> getAllAccounts(Pageable pageable) {
        log.info("[getAllAccounts] Fetching All Accounts ...");
        return ResponseEntity.ok(accountService.getAllAccounts(pageable));
    }

    @GetMapping(BANK_ENDPOINT + "/{branchCode}" + ACC_ENDPOINT)
    public ResponseEntity<?> getAllAccountsByBankBranchCode(@PathVariable("branchCode") int branchCode, Pageable pageable) {
        log.info("[getAllAccountsByBankBranchCode] Fetching All Accounts with branchCode=" + branchCode + " ...");
        return ResponseEntity.ok(accountService.getAllAccountsByBankBranchCode(branchCode, pageable));
    }

    @PostMapping(BANK_ENDPOINT + "/{branchCode}" + ACC_ENDPOINT)
    public ResponseEntity<?> createAccount(@PathVariable("branchCode") int branchCode,
                                           @Valid @RequestBody AccountDTO accountRequest) {
        log.info("[createAccount] Added new account to branchCode=" + branchCode + " ...");
        return bankService.getBankByBranchCode(branchCode).map(bank -> {
            BankDTO bankDTO = bankMapper.bankToBankDTO(bank);
            accountRequest.setBank(bankDTO);

            Account account = accountMapper.accountDTOToAccount(accountRequest);
            Account res = accountService.addAccount(account);
            AccountDTO accountResponse = accountMapper.accountToAccountDTO(res);

            return ResponseEntity.ok(accountResponse);
        }).orElseThrow(() -> new ResourceNotFoundException("[BankId: " + branchCode + "] Not Found!"));
    }

    @PutMapping(BANK_ENDPOINT + "/{branchCode}" + ACC_ENDPOINT + "/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable("branchCode") int branchCode,
                                           @PathVariable("id") Long id,
                                           @Valid @RequestBody AccountDTO accountRequest) {
        log.info("[updateAccount] Update account with branchCode=" + branchCode + "& accountId=" + id + " ...");
        if (!bankService.existsByBranchCode(branchCode))
            return new ResponseEntity<>("[updateAccount] branchCode=" + branchCode + " Not Found!",
                    HttpStatus.BAD_REQUEST);

        return accountService.getAccountById(id).map(account -> {
            accountMapper.updateAccountFromDTO(accountRequest, account);
            Account res = accountService.updateAccount(account);
            AccountDTO accountResponse = accountMapper.accountToAccountDTO(res);
            return ResponseEntity.ok(accountResponse);
        }).orElseThrow(() -> new ResourceNotFoundException("[updateAccount] accountId=" + id + " Not Found!"));
    }

    @DeleteMapping(BANK_ENDPOINT + "/{branchCode}" + ACC_ENDPOINT + "/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable("branchCode") int branchCode,
                                           @PathVariable("id") Long id) {
        log.info("[deleteAccount] Delete account with branchCode=" + branchCode + "& accountId=" + id + " ...");
        return accountService.getAccountByIdAndBankBranchCode(id, branchCode).map(account -> {
            accountService.deleteAccount(account);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("[deleteAccount] accountId=" + id + "& branchCode=" + branchCode + " Not Found!"));
    }
}
