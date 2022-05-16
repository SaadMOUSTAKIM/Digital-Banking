package ma.enset.moustakimebankingback.services;

import ma.enset.moustakimebankingback.entities.BankAccount;
import ma.enset.moustakimebankingback.entities.CurrentAccount;
import ma.enset.moustakimebankingback.entities.SavingAccount;
import ma.enset.moustakimebankingback.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount=bankAccountRepository.findById("076187b9-4687-458f-93c4-834277c988f5").orElse(null);
        System.out.println("---------------------------");
        System.out.println(bankAccount.getId());
        System.out.println(bankAccount.getBalance());
        System.out.println(bankAccount.getCreatedAt());
        System.out.println(bankAccount.getStatus());
        System.out.println(bankAccount.getCustomer().getName());
        System.out.println(bankAccount.getClass().getSimpleName());
        if (bankAccount instanceof CurrentAccount){
            System.out.println("Draft => "+((CurrentAccount)bankAccount).getOverDraft());
        }else if (bankAccount instanceof SavingAccount){
            System.out.println("Rate => "+((SavingAccount)bankAccount).getInterestRate());
        }

        bankAccount.getAccountOperations().forEach(op->{
            System.out.println(op.getType()+"\t"+op.getOperationDate()+"\t"+op.getAmount());
        });

    }
}
