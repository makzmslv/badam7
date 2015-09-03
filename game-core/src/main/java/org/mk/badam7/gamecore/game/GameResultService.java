package org.mk.badam7.gamecore.game;

import java.util.List;

import org.mk.badam7.database.entity.GameEntity;
import org.mk.badam7.gamedto.game.GameResultDTO;

public interface GameResultService
{
    public List<GameResultDTO> createGameResults(GameEntity gameEntity);
}
