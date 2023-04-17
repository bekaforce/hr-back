package com.example.hrback.service.participant.impl;

import com.example.hrback.dto.FinalDto;
import com.example.hrback.model.participant.Mail;
import com.example.hrback.model.participant.Message;
import com.example.hrback.repository.participant.MessageRepo;
import com.example.hrback.service.participant.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;
    public final JavaMailSender emailSender;
    public final SpringTemplateEngine thymeleafTemplateEngine;
    @Value("${spring.mail.username}")
    private String FROM;
    Long introduction_id = 1L;
    Long body_id = 2L;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public MessageServiceImpl(MessageRepo messageRepo, JavaMailSender emailSender, SpringTemplateEngine thymeleafTemplateEngine) {
        this.messageRepo = messageRepo;
        this.emailSender = emailSender;
        this.thymeleafTemplateEngine = thymeleafTemplateEngine;
    }


    @Override
    public Message messageById(Long id) {
        return messageRepo.findMessageById(id);
    }

    @Override
    public String getText(Long id) {
        Message message = messageById(id);
        if (message != null){
            return message.getText();
        }
        else {
            return null;
        }
    }

    @Override
    public boolean sendEmail(String name, String email, String password) {
        Mail mail = new Mail();
        mail.setTemplateId("email");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("introduction", setMessage(name, password, introduction_id));
        paramMap.put("body", setMessage(name, password, body_id));
        paramMap.put("login", "https://recruiting.beeline.kg/#/?email=" + email + "&login=true"); //https://recruiting.beeline.kg/#/login

        mail.setParamMap(paramMap);
        mail.setFrom(FROM);
        mail.setTo(email);
        mail.setSubject("Подтвердите авторизацию");
        return sendMessage(mail);
    }

    @Override
    public String setMessage(String name, String password, Long message_id) {
        String text = getText(message_id);
        text = text.replaceAll("name", name);
        text = text.replaceAll("password", password);
        return text;
    }

    @Override
    public boolean sendMessage(Mail mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(mail.getFrom());
            messageHelper.setTo(mail.getTo());
            messageHelper.setSubject(mail.getSubject());
            Context context = new Context();
            context.setVariables(mail.getParamMap());
            String content = thymeleafTemplateEngine.process(mail.getTemplateId(), context);
            messageHelper.setText(content, true);
            emailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String setSuccessText(String name, String vacancy, Long message_id) {
        String text = getText(message_id);
        text = text.replaceAll("name", name);
        text = text.replaceAll("vacancy", vacancy);
        return text;
    }

    @Override
    public FinalDto success(String name, String vacancy) {
        Long title = 3L;
        Long text = 4L;
        Long description = 5L;
        FinalDto finalDto = new FinalDto();
        finalDto.setTitle(setSuccessText(name, vacancy, title));
        finalDto.setText(setSuccessText(name, vacancy, text));
        finalDto.setDescription(setSuccessText(name, vacancy, description));
        return finalDto;
    }
}
