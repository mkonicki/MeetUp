package konicki.mateusz.greendaosample;

import org.junit.Test;

import konicki.mateusz.greendaosample.entites.DaoSession;
import konicki.mateusz.greendaosample.entites.Match;
import konicki.mateusz.greendaosample.entites.MatchType;
import konicki.mateusz.greendaosample.entites.Team;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 23.04.2017.
 */

public class MatchTest extends BaseTest {

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
            match.blueTeamGoal();

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
            match.blueTeamGoal();

        //PASSIVE UPDATE
        session.getMatchDao().update(match);

        session.clear();

        Match matchFromDB = master.newSession().getMatchDao().load(match.getId());

        assertThat(matchFromDB.getBlueGoals()).isEqualTo(blueGoals);
    }

}
