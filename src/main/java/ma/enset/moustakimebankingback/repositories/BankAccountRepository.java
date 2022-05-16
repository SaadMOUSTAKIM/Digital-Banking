package ma.enset.moustakimebankingback.repositories;

import ma.enset.moustakimebankingback.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
