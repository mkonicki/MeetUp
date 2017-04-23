package konicki.mateusz.greendaosample.database.repositories;

import android.content.Context;

import java.util.List;

import konicki.mateusz.greendaosample.entites.Player;
import konicki.mateusz.greendaosample.entites.PlayerDao;

/**
 * Created by Mateusz on 23.04.2017.
 */

public class PlayerRepository extends BaseRepository {

    public PlayerRepository(Context context) {
        super(context);
    }

    public long create(Player player) {
        return getDao().insert(player);
    }

    public List<Player> getAll(){
        return getDao().loadAll();
    }

    private PlayerDao getDao() {
        return idbHelper.getSession().getPlayerDao();
    }
}
