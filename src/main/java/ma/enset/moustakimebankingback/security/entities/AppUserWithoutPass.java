package ma.enset.moustakimebankingback.security.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserWithoutPass {
    private Long id;
    private String  username;
    private String email;
    private Collection<String> appRoles=new ArrayList<>();
}
