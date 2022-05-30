package ma.enset.moustakimebankingback;

import ma.enset.moustakimebankingback.dtos.BankAccountDTO;
import ma.enset.moustakimebankingback.dtos.CurrentBankAccountDTO;
import ma.enset.moustakimebankingback.dtos.CustomerDTO;
import ma.enset.moustakimebankingback.dtos.SavingBankAccountDTO;
import ma.enset.moustakimebankingback.entities.*;
import ma.enset.moustakimebankingback.enums.AccountStatus;
import ma.enset.moustakimebankingback.enums.operationType;
import ma.enset.moustakimebankingback.exceptions.BalanceNotSufficientException;
import ma.enset.moustakimebankingback.exceptions.BankAccountNOtFoundException;
import ma.enset.moustakimebankingback.exceptions.CustomerNotFoundException;
import ma.enset.moustakimebankingback.repositories.AccountOperationRepository;
import ma.enset.moustakimebankingback.repositories.BankAccountRepository;
import ma.enset.moustakimebankingback.repositories.CustomerRepository;
import ma.enset.moustakimebankingback.security.entities.AppRole;
import ma.enset.moustakimebankingback.security.entities.AppUser;
import ma.enset.moustakimebankingback.security.service.AccountService;
import ma.enset.moustakimebankingback.services.BankAccountservice;
import ma.enset.moustakimebankingback.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class MoustakimEBankingBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoustakimEBankingBackApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunner(AccountService accountService){
        return args -> {
            accountService.addNewRole(new AppRole(null,"USER"));
            accountService.addNewRole(new AppRole(null,"SUPER_ADMIN"));
            accountService.addNewRole(new AppRole(null," ADMIN"));

            accountService.addNewUser(new AppUser(null,"mouatta","mouatta@mail.com","123",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"kabboura","kabboura@mail.com","123",new ArrayList<>()));
            accountService.addNewUser(new AppUser(null,"sahel","sahel@mail.com","123",new ArrayList<>()));

            accountService.addRoleToUser("mouatta","ADMIN");
            accountService.addRoleToUser("mouatta","SUPER_ADMIN");
            accountService.addRoleToUser("mouatta","USER");
            accountService.addRoleToUser("kabboura","ADMIN");
            accountService.addRoleToUser("kabboura","USER");
            accountService.addRoleToUser("sahel","USER");
        };
    }


   @Bean
    CommandLineRunner commandLineRunner(BankAccountservice bankAccountservice){
        return args -> {
            Stream.of("Achraf","Alaa","Kaoutar").forEach(name->{
                CustomerDTO customer=new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountservice.saveCustomer(customer);
            });
            bankAccountservice.listCustomers().forEach(customer -> {
                try {
                    bankAccountservice.saveCurrentBankAccount(Math.random()*80000,8000, customer.getId());
                    bankAccountservice.saveSavingBankAccount(Math.random()*10000,5.5, customer.getId());

                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccountList=bankAccountservice.bankAccountList();
            for (BankAccountDTO bankAccount:bankAccountList) {
                for (int i = 0; i < 10 ; i++) {
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId=((SavingBankAccountDTO) bankAccount).getId();
                    }else {
                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountservice.credit(accountId, 10000+Math.random()*12000,"Credit");
                    bankAccountservice.debit(accountId, 1000+Math.random()*9000,"Credit");
                }
            }
        };
    }

//    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Achraf","Alaa","Kaoutar").forEach(name->{
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
            });

            customerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount=new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*9000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount=new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*9000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });

            bankAccountRepository.findAll().forEach(acc->{
                for(int i=0; i<5; i++){
                    AccountOperation accountOperation=new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*5000);
                    accountOperation.setType(Math.random()>0.5? operationType.DEBIT:operationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }
            });
        };
    }
}
