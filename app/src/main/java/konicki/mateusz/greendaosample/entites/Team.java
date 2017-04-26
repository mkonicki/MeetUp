package konicki.mateusz.greendaosample.entites;


import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Mateusz on 23.04.2017.
 */
public class Team extends SugarRecord<Team> {

    private Long id;

    public Team(Long id) {
        this.id = id;
    }

    public Team() {
    }

    public List<PlayerTeam> getPlayerTeam() {
        return PlayerTeam.find(PlayerTeam.class, "team = ?", String.valueOf(getId()));
    }

    public List<Match> getMatchesAsRedTeam() {
        return Match.find(Match.class
                , "redTeam = ?", String.valueOf(getId()));
    }

    public List<Match> getMatchesAsBlueTeam() {
        return Match.find(Match.class
                , "blueTeam = ?", String.valueOf(getId()));
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
