package com.vino.test.util;

import com.vino.test.dto.EmployeeDto;
import com.vino.test.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Email {

    private JavaMailSender sender;

    private String body;

    private String template;

    private Object param;

    private InternetAddress from;

    private InternetAddress[] to;

    private InternetAddress[] bcc;

    private String subject;

    private Environment env;

    private ServletContext servletContext;


    @Autowired
    private void Email(JavaMailSender sender, Environment env, ServletContext servletContext) {
        this.sender = sender;
        this.env = env;
        this.servletContext = servletContext;
    }

    public String getBody() {
        return body;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public InternetAddress getFrom() {
        return from;
    }

    public void setFrom(InternetAddress from) {
        this.from = from;
    }

    public InternetAddress[] getTo() {
        return to;
    }

    public void setTo(InternetAddress[] to) {
        this.to = to;
    }

    public void setTo(InternetAddress to) {
        this.to = new InternetAddress[] {to};
    }

    public void setTo(String to) {
        List<InternetAddress> toList = new ArrayList<>();
        try {
            toList.addAll(Arrays.stream(InternetAddress.parse(to)).collect(Collectors.toList()));
        } catch (AddressException e) {
            e.printStackTrace();
        }
        this.to = toList.toArray(new InternetAddress[toList.size()]);
    }

    public void setTo(String[] to) {
        List<InternetAddress> toList = new ArrayList<>();
        for (String t : to) {
            try {
                toList.addAll(Arrays.stream(InternetAddress.parse(t)).collect(Collectors.toList()));
            } catch (AddressException e) {
                e.printStackTrace();
            }
        }
        this.to = toList.toArray(new InternetAddress[toList.size()]);
    }

    public InternetAddress[] getBcc() {
        return bcc;
    }

    public void setBcc(InternetAddress bcc) {
        this.bcc = new InternetAddress[] {bcc};
    }

    public void setBcc(InternetAddress[] bcc) {
        this.bcc = bcc;
    }

    public void setBcc(String bcc) {
        List<InternetAddress> bccList = new ArrayList<>();
        try {
            bccList.addAll(Arrays.stream(InternetAddress.parse(bcc)).collect(Collectors.toList()));
        } catch (AddressException e) {
            e.printStackTrace();
        }
        this.bcc = bccList.toArray(new InternetAddress[bccList.size()]);
    }

    public void setBcc(String[] bcc) {
        List<InternetAddress> bccList = new ArrayList<>();
        for (String b : bcc) {
            try {
                bccList.addAll(Arrays.stream(InternetAddress.parse(b)).collect(Collectors.toList()));
            } catch (AddressException e) {
                e.printStackTrace();
            }
        }
        this.bcc = bccList.toArray(new InternetAddress[bccList.size()]);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void initializeEmail(String template, Object param, String subject) {
        this.template = template;
        this.param = param;
        this.subject = subject;

        try {
            this.from = InternetAddress.parse("sadkpp.test@gmail.com")[0];
        } catch (AddressException e) {
            e.printStackTrace();
        }

        this.bcc = new InternetAddress[0];
    }


    public void sendMail() {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("sadkpp.test@gmail.com");
            helper.setTo(this.to);
            helper.setSubject(this.subject);
            helper.setText("<b>You Crazy !</b>", true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
    }

    public void addTo(String s , String s1, String s2) {
        setTo(s);
        setTo(s1);
        setTo(s2);
    }
}
