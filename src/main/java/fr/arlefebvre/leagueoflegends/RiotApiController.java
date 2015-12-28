package fr.arlefebvre.leagueoflegends;

import fr.arlefebvre.leagueoflegends.domain.ChampionDto;
import fr.arlefebvre.leagueoflegends.domain.ChampionListDto;
import fr.arlefebvre.leagueoflegends.domain.ChampionStaticListDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Arthur on 21/12/2015.
 */
@PropertySource("classpath:dont.commit.this.properties")
@RestController
public class RiotApiController {

    @Value("${api.key}")
    private String key;

    @Value("${api.region}")
    private String region;

    private String getBaseUrl(){
        return "https://"+region+".api.pvp.net/api/lol/"+region+"/";
    }

    private String getStaticDataBaseUrl(){
        return "https://global.api.pvp.net/api/lol/static-data/"+region+"/";
    }

    @RequestMapping("/me")
    public String me(){
        String uri = getBaseUrl()+"v1.4/summoner/by-name/Kohzal?api_key="+key;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                uri,
                String.class);

        return response.getBody();
    }

    @RequestMapping("/games")
    public String games(){
        String uri = getBaseUrl()+"v1.3/game/by-summoner/19521268/recent?api_key="+key;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                uri,
                String.class);

        return response.getBody();
    }

    @RequestMapping("/rotation")
    public List<String> rotation() {
        String uri = getBaseUrl()+"v1.2/champion?freeToPlay=true&api_key="+key;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ChampionListDto> response = restTemplate.getForEntity(
                uri,
                ChampionListDto.class);

        List<ChampionDto> champions = response.getBody().getChampions();
        return champions.stream().map(c -> getChampionById(c.getId()).getName()).collect(Collectors.toList());
    }

    private ChampionStaticListDto championsCache;

    private ChampionDto getChampionById(long id){
        if(championsCache == null)
        {
            String uri = getStaticDataBaseUrl()+"v1.2/champion?api_key="+key;
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ChampionStaticListDto> response = restTemplate.getForEntity(
                    uri,
                    ChampionStaticListDto.class);
           championsCache =  response.getBody();
        }

        for (ChampionDto c:championsCache.getData().values()){
            if(c.getId() == id)
                return c;
        }

        return null;
    }
}
