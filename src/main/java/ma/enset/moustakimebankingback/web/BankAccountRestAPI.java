package ma.enset.moustakimebankingback.web;

import lombok.AllArgsConstructor;
import ma.enset.moustakimebankingback.dtos.*;
import ma.enset.moustakimebankingback.exceptions.BalanceNotSufficientException;
import ma.enset.moustakimebankingback.exceptions.BankAccountNOtFoundException;
import ma.enset.moustakimebankingback.services.BankAccountservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class BankAccountRestAPI {

    private BankAccountservice bankAccountservice;
    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccounts(@PathVariable String accountId) throws BankAccountNOtFoundException {
        return bankAccountservice.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountservice.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountservice.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "size",defaultValue = "5")int size) throws BankAccountNOtFoundException {
        return bankAccountservice.getAccountHistory(accountId,page,size);
    }

    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BalanceNotSufficientException, BankAccountNOtFoundException {
        this.bankAccountservice.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }
    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNOtFoundException {
        this.bankAccountservice.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }
    @PostMapping("/accounts/transfer")
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNOtFoundException, BalanceNotSufficientException {
        this.bankAccountservice.transfert(
                transferRequestDTO.getAccountSource(),
                transferRequestDTO.getAccountDestination(),
                transferRequestDTO.getAmount());
    }
}
