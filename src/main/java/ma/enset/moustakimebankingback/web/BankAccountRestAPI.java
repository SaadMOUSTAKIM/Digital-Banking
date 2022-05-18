package ma.enset.moustakimebankingback.web;

import lombok.AllArgsConstructor;
import ma.enset.moustakimebankingback.dtos.BankAccountDTO;
import ma.enset.moustakimebankingback.exceptions.BankAccountNOtFoundException;
import ma.enset.moustakimebankingback.services.BankAccountservice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
