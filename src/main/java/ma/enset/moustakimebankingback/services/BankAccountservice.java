package ma.enset.moustakimebankingback.services;

import ma.enset.moustakimebankingback.dtos.CustomerDTO;
import ma.enset.moustakimebankingback.entities.BankAccount;
import ma.enset.moustakimebankingback.entities.CurrentAccount;
import ma.enset.moustakimebankingback.entities.Customer;
import ma.enset.moustakimebankingback.entities.SavingAccount;
import ma.enset.moustakimebankingback.exceptions.BalanceNotSufficientException;
import ma.enset.moustakimebankingback.exceptions.BankAccountNOtFoundException;
import ma.enset.moustakimebankingback.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountservice {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<CustomerDTO> listCustomers();
    BankAccount getBankAccount(String accountId) throws BankAccountNOtFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNOtFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNOtFoundException;
    void transfert(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNOtFoundException, BalanceNotSufficientException;

    List<BankAccount> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);
}
