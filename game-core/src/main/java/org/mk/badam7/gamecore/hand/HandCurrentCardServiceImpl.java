package org.mk.badam7.gamecore.hand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.HandCurrentCardDAO;
import org.mk.badam7.database.entity.HandCurrentCardEntity;
import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.enums.Suite;
import org.mk.badam7.gamecore.common.Badam7Util;
import org.mk.badam7.gamedto.hand.HandCurrentCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandCurrentCardServiceImpl implements HandCurrentCardService
{
    @Autowired
    private HandCurrentCardDAO handCurrentCardDAO;

    @Autowired
    private Badam7Util badam7Util;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public HandCurrentCardDTO createHandCurrentCard(HandCurrentCardDTO inputDTO)
    {
        HandCurrentCardEntity handCurrentCardEntity = new HandCurrentCardEntity();
        handCurrentCardEntity.setCard(badam7Util.getCardFromId(inputDTO.getCardId()));
        handCurrentCardEntity.setHand(badam7Util.getHandFromId(inputDTO.getHandId()));
        handCurrentCardEntity.setPlayerCurrentGameInstanceEntity(badam7Util.getPlayerCurrentGameInstanceFromId(inputDTO.getPlayerCurrentGameInstanceId()));
        handCurrentCardEntity = handCurrentCardDAO.save(handCurrentCardEntity);
        return dozerMapper.map(handCurrentCardEntity, HandCurrentCardDTO.class);
    }

    @Override
    public Map<Integer, List<HandCurrentCardDTO>> getAllHandCurrentCards(Integer handId)
    {
        try
        {
            HandEntity handEntity = badam7Util.getHandFromId(handId);
            List<HandCurrentCardEntity> heartCards = handCurrentCardDAO.getByHandAndSuiteAndOrderByValue(handEntity, Suite.HEARTS.getSuiteValue());
            List<HandCurrentCardEntity> spadeCards = handCurrentCardDAO.getByHandAndSuiteAndOrderByValue(handEntity, Suite.SPADES.getSuiteValue());
            List<HandCurrentCardEntity> clubCards = handCurrentCardDAO.getByHandAndSuiteAndOrderByValue(handEntity, Suite.CLUBS.getSuiteValue());
            List<HandCurrentCardEntity> diamondCards = handCurrentCardDAO.getByHandAndSuiteAndOrderByValue(handEntity, Suite.DIAMONDS.getSuiteValue());
            Map<Integer, List<HandCurrentCardDTO>> handCurrentCards = new HashMap<Integer, List<HandCurrentCardDTO>>();
            handCurrentCards.put(Suite.HEARTS.getType(), badam7Util.mapListOfEnitiesToDTOs(heartCards, HandCurrentCardDTO.class));
            handCurrentCards.put(Suite.SPADES.getType(), badam7Util.mapListOfEnitiesToDTOs(spadeCards, HandCurrentCardDTO.class));
            handCurrentCards.put(Suite.CLUBS.getType(), badam7Util.mapListOfEnitiesToDTOs(clubCards, HandCurrentCardDTO.class));
            handCurrentCards.put(Suite.DIAMONDS.getType(), badam7Util.mapListOfEnitiesToDTOs(diamondCards, HandCurrentCardDTO.class));
            return handCurrentCards;
        }
        catch (Exception e)
        {
            return new HashMap<Integer, List<HandCurrentCardDTO>>();
        }
    }
}
