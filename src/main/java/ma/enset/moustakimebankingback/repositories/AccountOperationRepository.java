package ma.enset.moustakimebankingback.repositories;

import ma.enset.moustakimebankingback.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
