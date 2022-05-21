package ma.enset.moustakimebankingback.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.moustakimebankingback.entities.BankAccount;
import ma.enset.moustakimebankingback.enums.operationType;

import javax.persistence.*;
import java.util.Date;


@Data
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private operationType type;
    private String description;
}
