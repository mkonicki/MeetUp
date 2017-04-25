package konicki.mateusz.greendaosample.entites;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Mateusz on 17.04.2017.
 */
@DatabaseTable
public class Player {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(unique = true)
    private String nickname;

     private ForeignCollection<PlayerTeam> team;

    public Player(String nickname) {
        this.nickname = nickname;
    }

    public Player() {
    }

    public Long getId() {
        return this.id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id!=player.id) return false;
        return nickname != null ? nickname.equals(player.nickname) : player.nickname == null;

    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        return result;
    }

}
