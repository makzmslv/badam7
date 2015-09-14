package org.mk.badam7.gamecore.game;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mk.badam7.gamecore.dbunit.AbstractDbUnit;
import org.mk.badam7.gamecore.dbunit.SetUpDataSet;
import org.mk.badam7.gamedto.game.GameDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class GameCRUDServiceTest extends AbstractDbUnit
{
    @Autowired
    private GameService gameService;

    @Test
    @SetUpDataSet(xmlPath = "01_FindGameById.xml")
    public void createGameTest()
    {
        GameDetailsDTO gameDTO = gameService.getById(1);

        assertNotNull(gameDTO);
        System.out.println(gameDTO);
    }

}