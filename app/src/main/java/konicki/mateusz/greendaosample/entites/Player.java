package konicki.mateusz.greendaosample.entites;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Mateusz on 17.04.2017.
 */
public class Player extends SugarRecord<Player> {

    private long id;

    private String nickname;

    public Player(String nickname) {
        super();
        this.nickname = nickname;
    }

    public Player() {
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<PlayerTeam> getPlayerTeam() {
        return  PlayerTeam.find(PlayerTeam.class, "player = ?", String.valueOf(getId()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return id == player.id && (nickname != null ? nickname.equals(player.nickname) : player.nickname == null);

    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        return result;
    }

}
