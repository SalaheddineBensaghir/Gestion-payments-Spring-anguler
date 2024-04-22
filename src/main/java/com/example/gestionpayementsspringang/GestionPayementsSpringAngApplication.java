package com.example.gestionpayementsspringang;

import com.example.gestionpayementsspringang.entities.Payment;
import com.example.gestionpayementsspringang.entities.PaymentStatus;
import com.example.gestionpayementsspringang.entities.PaymentType;
import com.example.gestionpayementsspringang.entities.Student;
import com.example.gestionpayementsspringang.repository.PaymentRepository;
import com.example.gestionpayementsspringang.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class GestionPayementsSpringAngApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionPayementsSpringAngApplication.class, args);
    }



        @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository){
        return  args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Salaheddine").code("12354").programId("SDIA").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Ahmed").code("12355").programId("BDIA").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Foad").code("12356").programId("IDBCC").build());
            PaymentType[] paymentTypes= PaymentType.values();
            Random random = new Random();
            studentRepository.findAll().forEach(student -> {
                for (int i=0;i<10;i++){
                    int index = random.nextInt(paymentTypes.length);

                    Payment payment= Payment.builder().amount(1000+(int)(Math.random()+20000)).type(paymentTypes[index]).status(PaymentStatus.CREATED).date(LocalDate.now()).student(student).build();
                    paymentRepository.save(payment);
                }
            });
        };
        }
}
