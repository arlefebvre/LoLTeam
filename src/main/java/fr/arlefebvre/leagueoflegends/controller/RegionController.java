package fr.arlefebvre.leagueoflegends.controller;

import fr.arlefebvre.leagueoflegends.domain.Region;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by alefebvre on 29/01/2016.
 */
@RestController
public class RegionController {

    @RequestMapping(path = "/regions",method = RequestMethod.GET)
    public Region[] getAll(){
        return Region.values();
    }
}
