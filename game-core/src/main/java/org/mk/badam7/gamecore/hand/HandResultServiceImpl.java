package org.mk.badam7.gamecore.hand;

import java.util.List;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.ResultHandDAO;
import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.entity.HandResultEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.mk.badam7.gamecore.common.Badam7Util;
import org.mk.badam7.gamecore.playercurrenthand.PlayerCurrentHandCardService;
import org.mk.badam7.gamedto.hand.HandResultDTO;
import org.mk.badam7.gamedto.playercurrenthand.PlayerCurrentHandCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandResultServiceImpl implements HandResultService
{
    @Autowired
    private ResultHandDAO resultHandDAO;

    @Autowired
    private PlayerCurrentHandCardService playerCurrentHandCardService;

    @Autowired
    private Badam7Util badam7Util;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public HandResultDTO createHandResult(HandResultDTO inputDTO)
    {
        HandResultEntity handResultEntity = new HandResultEntity();
        HandEntity hand = badam7Util.getHandFromId(inputDTO.getHandId());
        handResultEntity.setHand(hand);
        PlayerCurrentGameInstanceEntity player = badam7Util.getPlayerCurrentGameInstanceFromId(inputDTO.getPlayerCurrentGameInstanceId());
        Integer points = getPointsOfPlayer(player, hand);
        handResultEntity.setPlayerCurrentGameInstanceEntity(player);
        handResultEntity.setPoints(points);
        handResultEntity = resultHandDAO.save(handResultEntity);
        return dozerMapper.map(handResultEntity, HandResultDTO.class);
    }

    private Integer getPointsOfPlayer(PlayerCurrentGameInstanceEntity player, HandEntity hand)
    {
        List<PlayerCurrentHandCardDTO> currentCardsOfPlayer = playerCurrentHandCardService.getAllPlayerCurrentHandCards(hand.getId(), player.getId());
        int points = 0;
        for (PlayerCurrentHandCardDTO card : currentCardsOfPlayer)
        {
            points = points + card.getCardValue();
        }
        return points;
    }
}
