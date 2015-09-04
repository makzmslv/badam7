package org.mk.badam7.gamecore.player;

import org.junit.Ignore;
import org.junit.Test;
import org.mk.badam7.gamecore.common.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerCRUDServiceTest
{
    @Autowired
    private EmailSender emailSender;

    @Test
    @Ignore
    public void testEmailSending()
    {
        emailSender.sendMail("makzmslv@gmail.com");
    }
}
