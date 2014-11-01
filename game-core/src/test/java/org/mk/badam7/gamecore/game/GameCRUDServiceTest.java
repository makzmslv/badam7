package org.mk.badam7.gamecore.game;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mk.badam7.gamecore.dto.GameDTO;
import org.mk.badam7.gamecore.dto.GameInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml", "classpath:databaseContextforTest.xml" })
public class GameCRUDServiceTest
{
    @Autowired
    private GameCRUDService gameCRUDService;

    @Test
    public void createGameTest()
    {
        GameInDTO gameInDTO = new GameInDTO();
        gameInDTO.setGameType(1);
        gameInDTO.setNoOfHands(4);
        gameInDTO.setNoOfPlayers(4);

        GameDTO gameDTO = gameCRUDService.createGame(gameInDTO);

        assertNull(gameDTO);
    }

}