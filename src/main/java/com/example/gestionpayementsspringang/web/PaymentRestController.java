package com.example.gestionpayementsspringang.web;

import com.example.gestionpayementsspringang.entities.Payment;
import com.example.gestionpayementsspringang.entities.PaymentStatus;
import com.example.gestionpayementsspringang.entities.PaymentType;
import com.example.gestionpayementsspringang.entities.Student;
import com.example.gestionpayementsspringang.repository.PaymentRepository;
import com.example.gestionpayementsspringang.repository.StudentRepository;
import com.example.gestionpayementsspringang.services.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
public class PaymentRestController {

    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
private PaymentService paymentService;
    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository, PaymentService paymentService){

        this.studentRepository=studentRepository;
        this.paymentRepository=paymentRepository;
        this.paymentService = paymentService;
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

        return paymentService.updatePaymentStatus(status,id);
}
@PostMapping(path = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public Payment savePayment(@RequestParam MultipartFile file, LocalDate date,double amount,PaymentType type,String studentCode) throws IOException {

    return   this.paymentService.savePayment(file,date,amount,type,studentCode);
}
@GetMapping(path = "/paymentFile/{paymentId}",produces = MediaType.APPLICATION_PDF_VALUE)
public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        return paymentService.getPaymentFile(paymentId);
}

}
