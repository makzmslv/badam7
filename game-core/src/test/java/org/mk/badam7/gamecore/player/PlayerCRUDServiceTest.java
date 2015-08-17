package org.mk.badam7.gamecore.player;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mk.badam7.gamecore.common.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml", "classpath:databaseContextforTest.xml" })
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
