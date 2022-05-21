package ma.enset.moustakimebankingback.repositories;

import ma.enset.moustakimebankingback.entities.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
    List<AccountOperation> findByBankAccountId(String accoundId);
    Page<AccountOperation> findByBankAccountId(String accoundId, Pageable pageable);
}
