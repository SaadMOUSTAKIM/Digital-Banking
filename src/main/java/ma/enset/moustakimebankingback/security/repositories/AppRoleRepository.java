package ma.enset.moustakimebankingback.security.repositories;



import ma.enset.moustakimebankingback.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);

}
