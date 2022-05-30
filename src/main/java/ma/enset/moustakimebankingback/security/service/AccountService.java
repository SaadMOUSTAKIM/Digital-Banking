package ma.enset.moustakimebankingback.security.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import ma.enset.moustakimebankingback.security.entities.AppRole;
import ma.enset.moustakimebankingback.security.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);

    String loadUserByUsernameWithoutPass(String username) throws JsonProcessingException;

    List<AppUser> listUsers();
}
