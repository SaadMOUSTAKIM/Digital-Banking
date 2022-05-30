package ma.enset.moustakimebankingback.security.repositories;



import ma.enset.moustakimebankingback.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
