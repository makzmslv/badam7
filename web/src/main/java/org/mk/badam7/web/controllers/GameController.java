package org.mk.badam7.web.controllers;

import java.util.List;

import org.mk.badam7.gamecore.game.GameService;
import org.mk.badam7.gamecore.hand.HandCurrentCardService;
import org.mk.badam7.gamedto.game.GameDTO;
import org.mk.badam7.gamedto.game.GameDetailsDTO;
import org.mk.badam7.gamedto.game.GameInDTO;
import org.mk.badam7.gamedto.hand.HandCurrentCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;

@Api(value = "games", description = "games")
@Controller
@RequestMapping(value = "/games")
public class GameController
{
    @Autowired
    private GameService gameService;

    @Autowired
    private HandCurrentCardService handCurrentCardService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public GameDTO createGame(@RequestBody GameInDTO gameInDTO)
    {
        return gameService.createGame(gameInDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public GameDetailsDTO startHand(@PathVariable Integer id)
    {
        return gameService.startHand(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public GameDetailsDTO getById(@PathVariable Integer id)
    {
        return gameService.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<GameDTO> getGamesToJoin()
    {
        return gameService.getGamesToJoin();
    }

    @RequestMapping(value = "/hand", method = RequestMethod.GET)
    @ResponseBody
    public List<HandCurrentCardDTO> getAllHandCurrentCards(@RequestParam Integer handId)
    {
        return handCurrentCardService.getAllHandCurrentCards(handId);
    }
}
