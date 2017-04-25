package konicki.mateusz.greendaosample.entites;

import com.orm.SugarRecord;

/**
 * Created by Mateusz on 23.04.2017.
 */
public class PlayerTeam extends SugarRecord<PlayerTeam> {
    private Long id;

    private Player player;

    private Team team;


    public PlayerTeam() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
