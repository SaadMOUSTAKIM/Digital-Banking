package ma.enset.moustakimebankingback.mappers;

import ma.enset.moustakimebankingback.dtos.CurrentBankAccountDTO;
import ma.enset.moustakimebankingback.dtos.CustomerDTO;
import ma.enset.moustakimebankingback.dtos.SavingBankAccountDTO;
import ma.enset.moustakimebankingback.entities.CurrentAccount;
import ma.enset.moustakimebankingback.entities.Customer;
import ma.enset.moustakimebankingback.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO=new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        return customerDTO;
    }

    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }

    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){

    }

    public SavingAccount fromSavingAccount(SavingBankAccountDTO savingBankAccountDTO){

    }


    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount){

    }

    public CurrentAccount fromCurrentAccount(CurrentBankAccountDTO currentBankAccountDTO){

    }
}
