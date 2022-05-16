package ma.enset.moustakimebankingback.dtos;


import lombok.Data;
import ma.enset.moustakimebankingback.enums.AccountStatus;

import java.util.Date;


@Data
public class CurrentBankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;

}