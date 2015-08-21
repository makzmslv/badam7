package org.mk.badam7.gamecore.hand;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.GameDAO;
import org.mk.badam7.database.dao.HandDAO;
import org.mk.badam7.database.entity.GameEntity;
import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.enums.HandStatus;
import org.mk.badam7.gamedto.hand.HandDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandCRUDServiceImpl implements HandCRUDService
{
    @Autowired
    private HandDAO handDAO;

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public HandDTO createHand(Integer gameId)
    {
        GameEntity gameEntity = gameDAO.findOne(gameId);
        if (gameEntity == null)
        {
            throw new IllegalArgumentException("Game with given id does not exist");
        }
        HandEntity handEntity = createHandEntity(gameEntity);
        handEntity = handDAO.save(handEntity);
        return dozerMapper.map(handEntity, HandDTO.class);
    }

    private HandEntity createHandEntity(GameEntity gameEntity)
    {
        HandEntity handEntity = new HandEntity();
        handEntity.setGame(gameEntity);
        handEntity.setStatus(HandStatus.IN_PROGRESS.getStatusCode());
        handEntity.setHandNo(getHandNo(gameEntity));
        return handEntity;
    }

    private int getHandNo(GameEntity gameEntity)
    {
        return handDAO.getHandNoForGame(gameEntity) + 1;
    }

}
