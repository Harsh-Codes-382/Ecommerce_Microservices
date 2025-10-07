package com.ecommerce.Notification.service;

import com.ecommerce.Notification.model.dto.Order_DTO.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ecommerce.Notification.EmailTemplates.EmailTemplates.ORDER_CONFIRMATION;
import static com.ecommerce.Notification.EmailTemplates.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Service
@RequiredArgsConstructor

public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendPaymentEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(
                        mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        UTF_8.name()
                );

        mimeMessageHelper.setFrom("harshtesting851@gmail.com");
        final String templateName = PAYMENT_CONFIRMATION.getTemplateName();

        Map<String, Object> dynamicVariables = new HashMap<>();

        dynamicVariables.put("customerName", customerName);
        dynamicVariables.put("amount", amount);
        dynamicVariables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(dynamicVariables);
        mimeMessageHelper.setSubject(PAYMENT_CONFIRMATION.getTemplateSubject());

        try{

            String HTML = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(HTML, true);
            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Payment Confirmation Email Successfully sent to {} with this template {}", destinationEmail, templateName);
        } catch (MessagingException e) {
            log.warn("WARNING - Payment Confirmation Email couldn't send to customer: {} on email: {}", customerName, destinationEmail);
        }
    }


    @Async
    public void sendOrderConfirmEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            List<Product> products
    ) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper =
                new MimeMessageHelper(
                        mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        UTF_8.name()
                );

        mimeMessageHelper.setFrom("harshtesting851@gmail.com");
        final String templateName = ORDER_CONFIRMATION.getTemplateName();

        Map<String, Object> dynamicVariables = new HashMap<>();

        dynamicVariables.put("customerName", customerName);
        dynamicVariables.put("amount", amount);
        dynamicVariables.put("products", products);
        dynamicVariables.put("orderReference", orderReference);

        Context context = new Context();
        context.setVariables(dynamicVariables);
        mimeMessageHelper.setSubject(ORDER_CONFIRMATION.getTemplateSubject());

        try{

            String HTML = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(HTML, true);
            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info("Order Confirmation Email Successfully sent to {} with this template {}", destinationEmail, templateName);
        } catch (MessagingException e) {
            log.warn("WARNING - Order Confirmation Email couldn't send to customer: {} on email: {}", customerName, destinationEmail);
        }
    }
}
