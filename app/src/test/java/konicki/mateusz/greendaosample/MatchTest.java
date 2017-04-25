package konicki.mateusz.greendaosample;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import konicki.mateusz.greendaosample.entites.Match;
import konicki.mateusz.greendaosample.entites.MatchType;
import konicki.mateusz.greendaosample.entites.Team;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 23.04.2017.
 */

public class MatchTest extends BaseTest {


    @Test
    public void matchTypeTest() {
        Dao<Match, Long> dao;
        try {
            dao = dbHelper.getDao(Match.class);

            Match match = new Match(MatchType.OneVsOne);

            assertThat(dao.create(match)).isNotNull();

            dao.clearObjectCache();

            Match matchFromDB = dao.queryForId(match.getId());

            assertThat(matchFromDB.getMatchType()).isEqualTo(MatchType.OneVsOne);
        } catch (SQLException e) {
            assertThat((Object) e).isNull();
        }
    }

    @Test
    public void matchTypeSearchQueryTest() {
        Dao<Match, Long> dao;
        try {
            dao = dbHelper.getDao(Match.class);

            Match match = new Match(MatchType.OneVsOne);
            assertThat(dao.create(match)).isNotNull();

            Match match2 = new Match(MatchType.OneVsTwo);
            assertThat(dao.create(match2)).isNotNull();

            Match match3 = new Match(MatchType.TwoVsTwo);
            assertThat(dao.create(match3)).isNotNull();

            Match match4 = new Match(MatchType.OneVsOne);
            assertThat(dao.create(match4)).isNotNull();

            dao.clearObjectCache();

            Dao<Match, Long> querySession = dbHelper.getDao(Match.class);

            QueryBuilder queryBuilder = querySession.queryBuilder();

            queryBuilder.where().eq("matchType", MatchType.OneVsOne);

            assertThat(queryBuilder.countOf()).isEqualTo(2);
        } catch (SQLException e) {
            assertThat((Object) e).isNull();
        }
    }

    @Test
    public void matchCombinedQueryTest() {
        Dao<Match, Long> dao;
        try {
            dao = dbHelper.getDao(Match.class);

            Match match = new Match(MatchType.OneVsOne, 10, 7);
            assertThat(dao.create(match)).isNotNull();

            Match match2 = new Match(MatchType.OneVsTwo, 10, 5);
            assertThat(dao.create(match2)).isNotNull();

            Match match3 = new Match(MatchType.OneVsOne, 10, 9);
            assertThat(dao.create(match3)).isNotNull();

            Match match4 = new Match(MatchType.OneVsOne, 4, 10);
            assertThat(dao.create(match4)).isNotNull();

            dao.clearObjectCache();

            Dao<Match, Long> querySession = dbHelper.getDao(Match.class);

            QueryBuilder queryBuilder = querySession.queryBuilder();

            queryBuilder.where().eq("matchType", MatchType.OneVsOne).and().eq("redGoals", 10);
            queryBuilder.orderBy("blueGoals", false);

            List<Match> matches = queryBuilder.query();

            assertThat(matches.size()).isEqualTo(2);
            assertThat(matches.get(0).getBlueGoals())
                    .isGreaterThan(matches.get(1).getBlueGoals());
        } catch (SQLException e) {
            assertThat((Object) e).isNull();
        }
    }


    @Test
    public void matchUpdateWhenEntityIsActive() {
        Dao<Team, Long> teamDao;
        Dao<Match, Long> matchDao;
        try {
            teamDao = dbHelper.getDao(Team.class);
            matchDao = dbHelper.getDao(Match.class);
            final int blueGoals = 4;

            Team teamA = new Team();
            Team teamB = new Team();

            teamDao.create(teamA);
            teamDao.create(teamB);

            Match match = new Match(MatchType.OneVsOne, teamA, teamB);

            assertThat(matchDao.create(match)).isNotNull();

            for (int i = 0; i < blueGoals; i++)
                match.blueGoals++;

            matchDao.update(match);

            matchDao.clearObjectCache();
            teamDao.clearObjectCache();

            Match matchFromDB = matchDao.queryForId(match.getId());

            assertThat(matchFromDB.getBlueGoals()).isEqualTo(blueGoals);
        } catch (SQLException e) {
            assertThat((Object) e).isNull();
        }
    }

    @Test
    public void matchRemoveWhenActive() {
        Dao<Match, Long> matchDao;
        try {
            matchDao = dbHelper.getDao(Match.class);
            Match match = new Match(MatchType.OneVsOne);

            assertThat(matchDao.create(match)).isNotNull();

            matchDao.delete(match);

            matchDao.clearObjectCache();

            Match matchFromDB = matchDao.queryForId(match.getId());

            assertThat(matchFromDB).isNull();
        } catch (SQLException e) {
            assertThat((Object) e).isNull();
        }
    }

}
