package fr.arlefebvre.leagueoflegends.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Arthur on 28/12/2015.
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome Summoner to the LoLTeam Project";
    }
}
