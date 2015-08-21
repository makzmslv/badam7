package org.mk.badam7.gamecore.playercurrenthand;

import org.mk.badam7.database.dao.PlayerCurrentHandCardDAO;
import org.mk.badam7.gamedto.playercurrenthand.PlayerCurrentHandCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCurrentHandCardCRUDServiceImpl implements PlayerCurrentHandCardCRUDService
{
    @Autowired
    private PlayerCurrentHandCardDAO playerCurrentHandCardDAO;

    @Override
    public void createPlayerCurrentHandCard(PlayerCurrentHandCardDTO playerCurrentHandCardDTO)
    {
        // TODO Auto-generated method stub

    }

}
