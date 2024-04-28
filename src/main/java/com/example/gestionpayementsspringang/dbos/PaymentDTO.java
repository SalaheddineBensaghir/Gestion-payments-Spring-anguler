package com.example.gestionpayementsspringang.dbos;

import com.example.gestionpayementsspringang.entities.PaymentStatus;
import com.example.gestionpayementsspringang.entities.PaymentType;
import com.example.gestionpayementsspringang.entities.Student;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class PaymentDTO {


    private Long id;
    private LocalDate date;
    private  double amount;
    private PaymentType type;
    private PaymentStatus status;
}
