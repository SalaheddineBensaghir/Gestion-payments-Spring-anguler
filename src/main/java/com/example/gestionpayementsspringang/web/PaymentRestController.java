package com.example.gestionpayementsspringang.web;

import com.example.gestionpayementsspringang.entities.Payment;
import com.example.gestionpayementsspringang.entities.PaymentStatus;
import com.example.gestionpayementsspringang.entities.PaymentType;
import com.example.gestionpayementsspringang.entities.Student;
import com.example.gestionpayementsspringang.repository.PaymentRepository;
import com.example.gestionpayementsspringang.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentRestController {

    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;

    public PaymentRestController(StudentRepository studentRepository,PaymentRepository paymentRepository){

        this.studentRepository=studentRepository;
        this.paymentRepository=paymentRepository;
    }
@GetMapping(path = "/payments")
    public List<Payment> allPayments(){
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/students/{code}/payments")
    public List<Payment> PaymentsByStudents(@PathVariable String code){
        return paymentRepository.findByStudentCode(code);
    }
    @GetMapping(path = "/paymentsByStatus")
    public List<Payment> PaymentsByStatus(@RequestParam PaymentStatus status){
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path = "/studentsByType")
    public List<Payment> PaymentsByType(@RequestParam PaymentType type){
        return paymentRepository.findByType(type);
    }

    @GetMapping(path = "/payments/{id}")
    public Payment getPaymentById(Long id){
        return paymentRepository.findById(id).get();
    }
    @GetMapping("/students")
    public List<Student> allStudents(){
        return studentRepository.findAll();

    }

    @GetMapping("/students/{code}")
public Student getStudentByCode(String code){
return studentRepository.findByCode(code);
}
@GetMapping("/studentsByProgramId")
public List<Student> getStudentsByProgramId(@RequestParam String programId){
        return studentRepository.findByProgramId(programId);
}
@PutMapping("/payments/{id}")
public Payment updatePaymentStatus(@RequestParam PaymentStatus status,@PathVariable Long id){
        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
}

}
