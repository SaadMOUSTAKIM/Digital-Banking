package ma.enset.moustakimebankingback.web;

import lombok.AllArgsConstructor;
import ma.enset.moustakimebankingback.dtos.AccountHistoryDTO;
import ma.enset.moustakimebankingback.dtos.AccountOperationDTO;
import ma.enset.moustakimebankingback.dtos.BankAccountDTO;
import ma.enset.moustakimebankingback.exceptions.BankAccountNOtFoundException;
import ma.enset.moustakimebankingback.services.BankAccountservice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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
}
