package br.com.ht7.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Document
public class Employee {
    @Id
    private String id;
    @NotNull
    private String name;
    private BigDecimal salary;
}
