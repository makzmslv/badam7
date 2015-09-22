package org.mk.badam7.gamecore.hand;

import java.util.List;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.HandCurrentCardDAO;
import org.mk.badam7.database.entity.HandCurrentCardEntity;
import org.mk.badam7.database.entity.HandEntity;
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
    public List<HandCurrentCardDTO> getAllHandCurrentCards(Integer handId)
    {
        HandEntity handEntity = badam7Util.getHandFromId(handId);
        List<HandCurrentCardEntity> currentHandCards = handCurrentCardDAO.findByHandEntity(handEntity);
        return badam7Util.mapListOfEnitiesToDTOs(currentHandCards, HandCurrentCardDTO.class);
    }

}
