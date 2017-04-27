package konicki.mateusz.greendaosample;

import android.os.Build;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.greenrobot.greendao.query.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import konicki.mateusz.greendaosample.entites.DaoSession;
import konicki.mateusz.greendaosample.entites.Match;
import konicki.mateusz.greendaosample.entites.MatchDao;
import konicki.mateusz.greendaosample.entites.Player;
import konicki.mateusz.greendaosample.entites.PlayerDao;
import konicki.mateusz.greendaosample.entites.PlayerTeam;
import konicki.mateusz.greendaosample.entites.PlayerTeamDao;
import konicki.mateusz.greendaosample.entites.Team;
import konicki.mateusz.greendaosample.entites.TeamDao;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 23.04.2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", sdk = Build.VERSION_CODES.LOLLIPOP)
public class PlayerTest extends BaseTest {

    @Test
    public void insertPlayerTest() {
        DaoSession session = master.newSession();

        Player player = new Player("Kaczka");

        Long id = session.insert(player);

        assertThat(id).isNotNull();

        PlayerDao playerDao = session.getPlayerDao();

        List<Player> players = playerDao.loadAll();

        assertThat(players).containsOnlyOnce(player);

    }


    @Test
    public void findPlayerByName() {

        DaoSession session = master.newSession();

        String playerName = "Kaczka";
        Player player = new Player(playerName);
        Player secondPlayer = new Player("Kurczak");
        Player thirdPlayer = new Player("Joey");
        Player fourthPlayer = new Player("Chandler");

        PlayerDao playerDao = session.getPlayerDao();

        //SEED DATA
        assertThat(playerDao.insert(player)).isNotNull();
        assertThat(playerDao.insert(secondPlayer)).isNotNull();
        assertThat(playerDao.insert(thirdPlayer)).isNotNull();
        assertThat(playerDao.insert(fourthPlayer)).isNotNull();

        QueryBuilder queryBuilder = playerDao.queryBuilder()
                .where(PlayerDao.Properties.Nickname.like(playerName));

        assertThat(queryBuilder.count()).isEqualTo(1);

        assertThat(queryBuilder.list()).containsOnly(player);
    }


    @Test
    public void getAllWonMatchesForPlayer() {
        DaoSession session = master.newSession();

        //1. Seed Players
        Player playerToFind = new Player("Kaczka");
        Player secondPlayer = new Player("Kurczak");
        Player thirdPlayer = new Player("Joey");
        Player fourthPlayer = new Player("Chandler");

        assertThat(session.insert(playerToFind)).isNotNull();
        assertThat(session.insert(secondPlayer)).isNotNull();
        assertThat(session.insert(thirdPlayer)).isNotNull();
        assertThat(session.insert(fourthPlayer)).isNotNull();

        //2. Seed Teams
        Team firstTeam = new Team();
        Team secondTeam = new Team();
        Team thirdTeam = new Team();
        Team fourthTeam = new Team();
        Team fifthTeam = new Team();
        Team sixthTeam = new Team();

        assertThat(session.insert(firstTeam)).isNotNull();
        assertThat(session.insert(secondTeam)).isNotNull();
        assertThat(session.insert(thirdTeam)).isNotNull();
        assertThat(session.insert(fourthTeam)).isNotNull();
        assertThat(session.insert(fifthTeam)).isNotNull();
        assertThat(session.insert(sixthTeam)).isNotNull();

        //3. Seed Player membership to teams
        assertThat(session.insert(new PlayerTeam(playerToFind.getId(), firstTeam.getId()))).isNotNull();
        assertThat(session.insert(new PlayerTeam(secondPlayer.getId(), firstTeam.getId()))).isNotNull();

        assertThat(session.insert(new PlayerTeam(thirdPlayer.getId(), secondTeam.getId()))).isNotNull();
        assertThat(session.insert(new PlayerTeam(fourthPlayer.getId(), secondTeam.getId()))).isNotNull();

        assertThat(session.insert(new PlayerTeam(playerToFind.getId(), thirdTeam.getId()))).isNotNull();
        assertThat(session.insert(new PlayerTeam(thirdPlayer.getId(), thirdTeam.getId()))).isNotNull();

        assertThat(session.insert(new PlayerTeam(secondPlayer.getId(), fourthTeam.getId()))).isNotNull();
        assertThat(session.insert(new PlayerTeam(fourthPlayer.getId(), fourthTeam.getId()))).isNotNull();

        assertThat(session.insert(new PlayerTeam(playerToFind.getId(), fifthTeam.getId()))).isNotNull();
        assertThat(session.insert(new PlayerTeam(fourthPlayer.getId(), fifthTeam.getId()))).isNotNull();

        assertThat(session.insert(new PlayerTeam(secondPlayer.getId(), sixthTeam.getId()))).isNotNull();
        assertThat(session.insert(new PlayerTeam(thirdPlayer.getId(), sixthTeam.getId()))).isNotNull();

        //4. Seed Matches
        assertThat(session.insert(new Match(10, 7, firstTeam, secondTeam))).isNotNull();
        assertThat(session.insert(new Match(9, 10, thirdTeam, fourthTeam))).isNotNull();
        assertThat(session.insert(new Match(5, 10, fifthTeam, sixthTeam))).isNotNull();
        assertThat(session.insert(new Match(10, 3, firstTeam, secondTeam))).isNotNull();
        assertThat(session.insert(new Match(8, 10, thirdTeam, fourthTeam))).isNotNull();
        assertThat(session.insert(new Match(10, 4, fifthTeam, sixthTeam))).isNotNull();

        //5. Get all teams where player played
        List<PlayerTeam> teamsWherePlayedPlayer = session.getPlayerTeamDao().queryBuilder()
                .where(PlayerTeamDao.Properties.PlayerId.eq(playerToFind.getId())).list();
        List<Long> teamIds = Stream.of(teamsWherePlayedPlayer)
                .map(PlayerTeam::getTeamId)
                .collect(Collectors.toList());

        //6. Query for all won matches
        QueryBuilder queryAsRedTeam = session.getMatchDao().queryBuilder();
        queryAsRedTeam.join(MatchDao.Properties.RedTeamId, Team.class)
                .where(TeamDao.Properties.Id.in(teamIds));
        queryAsRedTeam.where(MatchDao.Properties.RedGoals.eq(10));

        assertThat(queryAsRedTeam.count()).isEqualTo(3);

        QueryBuilder queryAsBlueTeam = session.getMatchDao().queryBuilder();
        queryAsBlueTeam.join(MatchDao.Properties.BlueTeamId, Team.class)
                .where(TeamDao.Properties.Id.in(teamIds));
        queryAsBlueTeam.where(MatchDao.Properties.BlueGoals.eq(10));

        assertThat(queryAsBlueTeam.count()).isEqualTo(0);

    }

}
