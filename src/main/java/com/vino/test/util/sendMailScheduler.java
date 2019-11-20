package com.vino.test.util;

import com.vino.test.dto.EmployeeDto;
import com.vino.test.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class sendMailScheduler {

    Email email;

    @Autowired
    private void sendMailScheduler(Email email){
        this.email = email;
    }

    private static final Logger log = LoggerFactory.getLogger(sendMailScheduler.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRate = 3000, initialDelay = 3000)
    public void reportCurrentTime(){
       //Employee employee = employeeRepository.findAllByEmail();
       //EmployeeDto employeeDto = employee.toDto();
        email.setSubject("TEST");
        email.setTo("vpnfake02@gmail.com");
        email.sendMail();
        log.info("Task Execution was Successful ! to vpn fake", formatter.format(LocalDateTime.now()));
    }

    @Scheduled(fixedRate = 3000, initialDelay = 3000)
    public void reportCurrentTime1(){
        //Employee employee = employeeRepository.findAllByEmail();
        //EmployeeDto employeeDto = employee.toDto();
        email.setSubject("TEST");
        email.setTo("faridfajar.chamim@gmail.com");
        email.sendMail();
        log.info("Task Execution was Successful to farid !", formatter.format(LocalDateTime.now()));
    }

    @Scheduled(fixedRate = 3000, initialDelay = 3000)
    public void reportCurrentTime2(){
        //Employee employee = employeeRepository.findAllByEmail();
        //EmployeeDto employeeDto = employee.toDto();
        email.setSubject("TEST");
        email.setTo("vino8959@gmail.com");
        email.sendMail();
        log.info("Task Execution was Successful to Vino !", formatter.format(LocalDateTime.now()));
    }




    /*public void sendEmail(Employee employee) throws  Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        Map<String, Object> model = new HashMap<>();

        helper.setTo(employee.getEmail());
        helper.setText("hallo", true); // set to html
        helper.setSubject("Hi");
        sender.send(message);

        System.out.println("Email Sent Successfull");
    }*/

}
