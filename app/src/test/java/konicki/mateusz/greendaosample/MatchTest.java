package konicki.mateusz.greendaosample;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.rx.RxQuery;
import org.junit.Test;

import java.util.List;

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

        Match match = new Match(MatchType.OneVsOne);

        assertThat(session.insert(match)).isNotNull();

        session.clear();

        Match matchFromDB = master.newSession().getMatchDao().load(match.getId());

        assertThat(matchFromDB.getMatchType()).isEqualTo(MatchType.OneVsOne);
    }

    @Test
    public void matchTypeSearchQueryTest() {
        DaoSession session = master.newSession();

        Match match = new Match(MatchType.OneVsOne);
        assertThat(session.insert(match)).isNotNull();

        Match match2 = new Match(MatchType.OneVsTwo);
        assertThat(session.insert(match2)).isNotNull();

        Match match3 = new Match(MatchType.TwoVsTwo);
        assertThat(session.insert(match3)).isNotNull();

        Match match4 = new Match(MatchType.OneVsOne);
        assertThat(session.insert(match4)).isNotNull();

        session.clear();

        DaoSession querySession = master.newSession();

        QueryBuilder queryBuilder = querySession.getMatchDao().queryBuilder();

        queryBuilder.where(MatchDao.Properties.MatchType.eq(MatchType.OneVsOne.getId()));

        assertThat(queryBuilder.count()).isEqualTo(2);
    }

    @Test
    public void matchCombinedQueryTest() {
        DaoSession session = master.newSession();

        Match match = new Match(MatchType.OneVsOne, 10, 7);
        assertThat(session.insert(match)).isNotNull();

        Match match2 = new Match(MatchType.OneVsTwo, 10, 5);
        assertThat(session.insert(match2)).isNotNull();

        Match match3 = new Match(MatchType.OneVsOne, 10, 9);
        assertThat(session.insert(match3)).isNotNull();

        Match match4 = new Match(MatchType.OneVsOne, 4, 10);
        assertThat(session.insert(match4)).isNotNull();

        session.clear();

        DaoSession querySession = master.newSession();

        QueryBuilder queryBuilder = querySession.getMatchDao().queryBuilder();

        Query query = queryBuilder.where(
                MatchDao.Properties.MatchType.eq(MatchType.OneVsOne.getId())
                , MatchDao.Properties.RedGoals.eq(10))
                .orderDesc(MatchDao.Properties.BlueGoals).build();

        List<Match> matches = query.list();

        assertThat(matches.size()).isEqualTo(2);
        assertThat(matches.get(0).getBlueGoals())
                .isGreaterThan(matches.get(1).getBlueGoals());

        query.setParameter(0, MatchType.OneVsTwo.getId());

        assertThat(query.list().size()).isEqualTo(1);
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
    public void matchUpdateWhenEntityIsPassive() {
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

    @Test
    public void matchRemoveWhenActive() {
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
        match.delete();

        session.clear();

        Match matchFromDB = master.newSession().getMatchDao().load(match.getId());

        assertThat(matchFromDB).isNull();
    }

}
