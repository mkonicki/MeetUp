package konicki.mateusz.greendaosample.entites;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mateusz on 23.04.2017.
 */
@DatabaseTable
public class PlayerTeam {
    @DatabaseField(generatedId = true)
    private Long id;

    @DatabaseField(foreign = true)
    private Player player;

    @DatabaseField(foreign = true)
    private Team team;


    public PlayerTeam() {
    }

    public PlayerTeam(Player player, Team team) {
        this.player = player;
        this.team = team;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
