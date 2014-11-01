package org.mk.badam7.gamecore.common;

import org.mk.badam7.gamecore.library.Badam7Constants;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailSender
{
    private MailSender mailSender;

    public void setMailSender(MailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public void sendMail(String to)
    {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(Badam7Constants.EMAIL_SENDER);
        message.setTo(to);
        message.setSubject(Badam7Constants.SUBJECT);
        message.setText(Badam7Constants.MESSAGE);
        mailSender.send(message);
    }

}
