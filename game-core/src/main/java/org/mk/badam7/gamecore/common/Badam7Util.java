package org.mk.badam7.gamecore.common;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.mk.badam7.database.dao.CardDAO;
import org.mk.badam7.database.dao.GameDAO;
import org.mk.badam7.database.dao.GameDetailsDAO;
import org.mk.badam7.database.dao.HandDAO;
import org.mk.badam7.database.dao.PlayerCurrentGameInstanceDAO;
import org.mk.badam7.database.dao.PlayerDAO;
import org.mk.badam7.database.entity.CardEntity;
import org.mk.badam7.database.entity.GameDetailsEntity;
import org.mk.badam7.database.entity.GameEntity;
import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.mk.badam7.database.entity.PlayerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Badam7Util
{
    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private PlayerCurrentGameInstanceDAO playerCurrentGameInstanceDAO;

    @Autowired
    private HandDAO handDAO;

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private GameDetailsDAO gameDetailsDAO;

    @Autowired
    private DozerBeanMapper mapper;

    public GameEntity getGameFromId(Integer gameId)
    {
        GameEntity gameEntity = gameDAO.findOne(gameId);
        if (gameEntity == null)
        {
            throw new IllegalArgumentException("Game with given id does not exist");
        }
        return gameEntity;
    }

    public GameDetailsEntity getGameDetailsFromGameId(Integer gameId)
    {
        GameEntity gameEntity = gameDAO.findOne(gameId);
        if (gameEntity == null)
        {
            throw new IllegalArgumentException("Game with given id does not exist");
        }
        GameDetailsEntity gameDetailsEntity = gameDetailsDAO.findByGameEntity(gameEntity);
        return gameDetailsEntity;
    }

    public PlayerEntity getPlayerFromId(Integer playerId)
    {
        PlayerEntity playerEntity = playerDAO.findOne(playerId);
        if (playerEntity == null)
        {
            throw new IllegalArgumentException("Player does not exist");
        }
        return playerEntity;
    }

    public CardEntity getCardFromId(Integer cardId)
    {
        CardEntity cardEntity = cardDAO.findOne(cardId);
        if (cardEntity == null)
        {
            throw new IllegalArgumentException("Card does not exist");
        }
        return cardEntity;
    }

    public HandEntity getHandFromId(Integer handId)
    {
        HandEntity handEntity = handDAO.findOne(handId);
        if (handEntity == null)
        {
            throw new IllegalArgumentException("handEntity does not exist");
        }
        return handEntity;
    }

    public PlayerCurrentGameInstanceEntity getPlayerCurrentGameInstanceFromId(Integer playerCurrentGameInstanceId)
    {
        PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity = playerCurrentGameInstanceDAO.findOne(playerCurrentGameInstanceId);
        if (playerCurrentGameInstanceEntity == null)
        {
            throw new IllegalArgumentException("PlayerCurrentGameInstanceEntity does not exist");
        }
        return playerCurrentGameInstanceEntity;
    }

    public <FromBean, ToBean> List<ToBean> mapListOfEnitiesToDTOs(List<FromBean> beans, Class<ToBean> clazz)
    {
        List<ToBean> list = new ArrayList<ToBean>();
        for (FromBean bean : beans)
        {
            if (bean != null)
            {
                list.add(mapper.map(bean, clazz));
            }
        }
        return list;
    }
}
