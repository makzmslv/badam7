package org.mk.badam7.gamedto.hand;

import org.mk.badam7.gamedto.game.GameDTO;

public class HandDTO
{
    private Integer id;

    private Integer status;

    private Integer handNo;

    private GameDTO gameDTO;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getHandNo()
    {
        return handNo;
    }

    public void setHandNo(Integer handNo)
    {
        this.handNo = handNo;
    }

    public GameDTO getGameDTO()
    {
        return gameDTO;
    }

    public void setGameDTO(GameDTO gameDTO)
    {
        this.gameDTO = gameDTO;
    }
}
