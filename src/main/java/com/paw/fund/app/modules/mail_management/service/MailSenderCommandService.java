package com.paw.fund.app.modules.mail_management.service;

import com.paw.fund.app.modules.mail_management.domain.MailSender;
import com.paw.fund.utils.validation.ValidationUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailSenderCommandService {
    @NonNull
    JavaMailSender jvMailSender;

    public void sendMail(MailSender mailSender) {
        ValidationUtil.validateNotNullPointerException(mailSender);
        try {
            MimeMessage mimeMessage = jvMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(new InternetAddress(mailSender.from(), "Paw Fund"));
            helper.setSubject(mailSender.subject());
            helper.setTo(mailSender.to());
            helper.setText(mailSender.body(), true);
            helper.setSentDate(new Date());

            jvMailSender.send(mimeMessage);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
