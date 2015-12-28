package fr.arlefebvre.leagueoflegends.domain;

import java.util.List;

/**
 * Created by Arthur on 28/12/2015.
 */
public class ChampionListDto {
    private List<ChampionDto> champions;

    public List<ChampionDto> getChampions() {
        return champions;
    }

    public void setChampions(List<ChampionDto> champions) {
        this.champions = champions;
    }
}
