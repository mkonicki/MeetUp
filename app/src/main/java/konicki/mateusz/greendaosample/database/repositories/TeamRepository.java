package konicki.mateusz.greendaosample.database.repositories;

import android.content.Context;

import org.greenrobot.greendao.async.AsyncSession;

import java.util.List;

import konicki.mateusz.greendaosample.entites.Player;
import konicki.mateusz.greendaosample.entites.Team;
import konicki.mateusz.greendaosample.entites.TeamDao;

/**
 * Created by Mateusz on 23.04.2017.
 */

public class TeamRepository extends BaseRepository {


    public TeamRepository(Context context) {
        super(context);
    }

    public Long create(Team team) {
        for (Player player:team.getPlayers()) {
//            idbHelper.getSession().insert(new Pla)
        }
        return getDao().insert(team);
    }

    public List<Team> getAll(){
        return getDao().loadAll();
    }

    private TeamDao getDao() {
        return idbHelper.getSession().getTeamDao();
    }
}
