package konicki.mateusz.greendaosample.entites;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mateusz on 23.04.2017.
 */
@DatabaseTable
public class Team {

    @DatabaseField(generatedId = true)
    private Long id;

    private ForeignCollection<PlayerTeam> players;

    private ForeignCollection<Match> matchesAsRedTeam;


    private ForeignCollection<Match> matchesAsBlueTeam;

    public Team(Long id) {
        this.id = id;
    }

    public Team() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
