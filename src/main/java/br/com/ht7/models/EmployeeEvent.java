package br.com.ht7.models;

import br.com.ht7.documents.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEvent {
    private Employee employee;
    private Date date;
}
