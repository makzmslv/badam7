package org.mk.badam7.gamecore.hand;

import java.util.List;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.GameDAO;
import org.mk.badam7.database.dao.HandDAO;
import org.mk.badam7.database.dao.PlayerCurrentGameInstanceDAO;
import org.mk.badam7.database.entity.GameEntity;
import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.mk.badam7.database.enums.HandStatus;
import org.mk.badam7.gamecore.common.Badam7Util;
import org.mk.badam7.gamecore.game.GameService;
import org.mk.badam7.gamedto.hand.HandDTO;
import org.mk.badam7.gamedto.hand.HandResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandServiceImpl implements HandService
{
    @Autowired
    private HandDAO handDAO;

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private PlayerCurrentGameInstanceDAO playerCurrentGameInstanceDAO;

    @Autowired
    private GameService gameService;

    @Autowired
    private HandResultService handResultService;

    @Autowired
    private Badam7Util badam7Util;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public HandDTO createHand(Integer gameId)
    {
        GameEntity gameEntity = badam7Util.getGameFromId(gameId);
        HandEntity handEntity = createHandEntity(gameEntity);
        handEntity = handDAO.save(handEntity);
        return dozerMapper.map(handEntity, HandDTO.class);
    }

    @Override
    public HandDTO endHand(Integer handId)
    {
        HandEntity handEntity = badam7Util.getHandFromId(handId);
        List<PlayerCurrentGameInstanceEntity> players = playerCurrentGameInstanceDAO.findByGameEntity(handEntity.getGameEntity());
        for (PlayerCurrentGameInstanceEntity player : players)
        {
            createHandResult(handId, player);
        }
        handEntity.setStatus(HandStatus.COMPLETED.getStatusCode());
        handEntity = handDAO.save(handEntity);
        if (isGameOver(handEntity.getGameEntity(), handEntity.getHandNo()))
        {
            gameService.endGame(handEntity.getGameEntity().getId());
        }
        return dozerMapper.map(handEntity, HandDTO.class);
    }

    private boolean isGameOver(GameEntity game, Integer handNo)
    {
        if (game.getNoOfHands().equals(handNo))
        {
            return true;
        }
        return false;
    }

    private void createHandResult(Integer handId, PlayerCurrentGameInstanceEntity player)
    {
        HandResultDTO inputDTO = new HandResultDTO();
        inputDTO.setHandId(handId);
        inputDTO.setPlayerCurrentGameInstanceId(player.getId());
        handResultService.createHandResult(inputDTO);
    }

    private HandEntity createHandEntity(GameEntity gameEntity)
    {
        HandEntity handEntity = new HandEntity();
        handEntity.setGameEntity(gameEntity);
        handEntity.setStatus(HandStatus.IN_PROGRESS.getStatusCode());
        handEntity.setHandNo(getHandNo(gameEntity));
        return handEntity;
    }

    private int getHandNo(GameEntity gameEntity)
    {
        return handDAO.getHandNoForGame(gameEntity) + 1;
    }

}
