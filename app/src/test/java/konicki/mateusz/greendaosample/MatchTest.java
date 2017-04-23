package konicki.mateusz.greendaosample;

import android.content.pm.PackageInstaller;

import org.greenrobot.greendao.query.QueryBuilder;
import org.junit.Test;

import konicki.mateusz.greendaosample.entites.DaoSession;
import konicki.mateusz.greendaosample.entites.Match;
import konicki.mateusz.greendaosample.entites.MatchDao;
import konicki.mateusz.greendaosample.entites.MatchType;
import konicki.mateusz.greendaosample.entites.Team;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 23.04.2017.
 */

public class MatchTest extends BaseTest {


    @Test
    public void matchTypeTest() {
        DaoSession session = master.newSession();

        Match match = new Match(MatchType.OneVsOne, null, null);

        assertThat(session.insert(match)).isNotNull();

        session.clear();

        Match matchFromDB = master.newSession().getMatchDao().load(match.getId());

        assertThat(matchFromDB.getMatchType()).isEqualTo(MatchType.OneVsOne);
    }

    @Test
    public void matchTypeSearchQueryTest() {
        DaoSession session = master.newSession();

        Match match = new Match(MatchType.OneVsOne, null, null);
        assertThat(session.insert(match)).isNotNull();

        Match match2 = new Match(MatchType.OneVsTwo, null, null);
        assertThat(session.insert(match2)).isNotNull();

        Match match3 = new Match(MatchType.TwoVsTwo, null, null);
        assertThat(session.insert(match3)).isNotNull();

        Match match4 = new Match(MatchType.OneVsOne, null, null);
        assertThat(session.insert(match4)).isNotNull();

        session.clear();

        DaoSession querySession = master.newSession();
        QueryBuilder queryBuilder = querySession.getMatchDao().queryBuilder();

        queryBuilder.where(MatchDao.Properties.MatchType.eq(MatchType.OneVsOne));


    }


    @Test
    public void matchUpdateWhenEntityIsActive() {
        DaoSession session = master.newSession();

        final int blueGoals = 4;

        Team teamA = new Team();
        Team teamB = new Team();

        session.insert(teamA);
        session.insert(teamB);

        Match match = new Match(MatchType.OneVsOne, teamA, teamB);

        assertThat(session.insert(match)).isNotNull();

        for (int i = 0; i < blueGoals; i++)
            match.blueGoals++;

        //ACTIVE UPDATE
        match.update();

        session.clear();

        Match matchFromDB = master.newSession().getMatchDao().load(match.getId());

        assertThat(matchFromDB.getBlueGoals()).isEqualTo(blueGoals);
    }

    @Test
    public void matchUpdateWhenEntityIsPasive() {
        DaoSession session = master.newSession();
        final int blueGoals = 4;

        Team blueTeam = new Team();
        Team redTeam = new Team();

        session.insert(blueTeam);
        session.insert(redTeam);

        Match match = new Match(MatchType.OneVsOne, blueTeam, redTeam);

        assertThat(session.insert(match)).isNotNull();

        for (int i = 0; i < blueGoals; i++)
            match.blueGoals++;

        //PASSIVE UPDATE
        session.getMatchDao().update(match);

        session.clear();

        Match matchFromDB = master.newSession().getMatchDao().load(match.getId());

        assertThat(matchFromDB.getBlueGoals()).isEqualTo(blueGoals);
    }

}
