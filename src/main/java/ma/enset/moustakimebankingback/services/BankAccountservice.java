package ma.enset.moustakimebankingback.services;

import ma.enset.moustakimebankingback.dtos.*;
import ma.enset.moustakimebankingback.exceptions.BalanceNotSufficientException;
import ma.enset.moustakimebankingback.exceptions.BankAccountNOtFoundException;
import ma.enset.moustakimebankingback.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountservice {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId) throws BankAccountNOtFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNOtFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNOtFoundException;
    void transfert(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNOtFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNOtFoundException;

    List<CustomerDTO> searchCustomers(String keyword);
}
