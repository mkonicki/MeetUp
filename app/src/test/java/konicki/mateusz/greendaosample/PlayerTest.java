package konicki.mateusz.greendaosample;

import android.os.Build;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.sql.SQLException;
import java.util.List;

import konicki.mateusz.greendaosample.entites.Match;
import konicki.mateusz.greendaosample.entites.Player;
import konicki.mateusz.greendaosample.entites.PlayerTeam;
import konicki.mateusz.greendaosample.entites.Team;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 23.04.2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", sdk = Build.VERSION_CODES.LOLLIPOP)
public class PlayerTest extends BaseTest {

    @Test
    public void insertPlayerTest() {
        Dao<Player, Long> dao;
        try {
            dao = dbHelper.getDao(Player.class);

            Player player = new Player("Kaczka");

            int id = dao.create(player);

            assertThat(id).isNotNull();

            List<Player> players = dao.queryForAll();

            assertThat(players).containsOnlyOnce(player);
        } catch (SQLException e) {
            assertThat((Object) e).isNull();
        }
    }


    @Test
    public void findPlayerByName() {

        Dao<Player, Long> dao;
        try {
            dao = dbHelper.getDao(Player.class);

            String playerName = "Kaczka";
            Player player = new Player(playerName);
            Player secondPlayer = new Player("Kurczak");
            Player thirdPlayer = new Player("Joey");
            Player fourthPlayer = new Player("Chandler");

            //SEED DATA
            dao.create(player);
            dao.create(secondPlayer);
            dao.create(thirdPlayer);
            dao.create(fourthPlayer);


            List<Player> players = dao.queryBuilder()
                    .where().eq("nickname", playerName).query();

            assertThat(players.size()).isEqualTo(1);

            assertThat(players).containsOnly(player);
        } catch (SQLException e) {
            assertThat((Object) e).isNull();
        }
    }

    @Test
    public void getAllWonMatchesForPlayer() {
        try {

            //1. Seed Players
            Dao<Player, Long> playerDao = dbHelper.getDao(Player.class);

            Player playerToFind = new Player("Kaczka");
            Player secondPlayer = new Player("Kurczak");
            Player thirdPlayer = new Player("Joey");
            Player fourthPlayer = new Player("Chandler");

            assertThat(playerDao.create(playerToFind)).isNotNull();
            assertThat(playerDao.create(secondPlayer)).isNotNull();
            assertThat(playerDao.create(thirdPlayer)).isNotNull();
            assertThat(playerDao.create(fourthPlayer)).isNotNull();

            //2. Seed Teams
            Dao<Team, Long> teamDao = dbHelper.getDao(Team.class);

            Team firstTeam = new Team();
            Team secondTeam = new Team();
            Team thirdTeam = new Team();
            Team fourthTeam = new Team();
            Team fifthTeam = new Team();
            Team sixthTeam = new Team();

            assertThat(teamDao.create(firstTeam)).isNotNull();
            assertThat(teamDao.create(secondTeam)).isNotNull();
            assertThat(teamDao.create(thirdTeam)).isNotNull();
            assertThat(teamDao.create(fourthTeam)).isNotNull();
            assertThat(teamDao.create(fifthTeam)).isNotNull();
            assertThat(teamDao.create(sixthTeam)).isNotNull();

            //3. Seed Player membership to teams
            Dao<PlayerTeam, Long> playerTeamDao = dbHelper.getDao(PlayerTeam.class);

            assertThat(playerTeamDao.create(new PlayerTeam(playerToFind, firstTeam))).isNotNull();
            assertThat(playerTeamDao.create(new PlayerTeam(secondPlayer, firstTeam))).isNotNull();

            assertThat(playerTeamDao.create(new PlayerTeam(thirdPlayer, secondTeam))).isNotNull();
            assertThat(playerTeamDao.create(new PlayerTeam(fourthPlayer, secondTeam))).isNotNull();

            assertThat(playerTeamDao.create(new PlayerTeam(playerToFind, thirdTeam))).isNotNull();
            assertThat(playerTeamDao.create(new PlayerTeam(thirdPlayer, thirdTeam))).isNotNull();

            assertThat(playerTeamDao.create(new PlayerTeam(secondPlayer, fourthTeam))).isNotNull();
            assertThat(playerTeamDao.create(new PlayerTeam(fourthPlayer, fourthTeam))).isNotNull();

            assertThat(playerTeamDao.create(new PlayerTeam(playerToFind, fifthTeam))).isNotNull();
            assertThat(playerTeamDao.create(new PlayerTeam(fourthPlayer, fifthTeam))).isNotNull();

            assertThat(playerTeamDao.create(new PlayerTeam(secondPlayer, sixthTeam))).isNotNull();
            assertThat(playerTeamDao.create(new PlayerTeam(thirdPlayer, sixthTeam))).isNotNull();

            //4. Seed Matches
            Dao<Match, Long> matchDao = dbHelper.getDao(Match.class);

            assertThat(matchDao.create(new Match(10, 7, firstTeam, secondTeam))).isNotNull();
            assertThat(matchDao.create(new Match(9, 10, thirdTeam, fourthTeam))).isNotNull();
            assertThat(matchDao.create(new Match(5, 10, fifthTeam, sixthTeam))).isNotNull();
            assertThat(matchDao.create(new Match(10, 3, firstTeam, secondTeam))).isNotNull();
            assertThat(matchDao.create(new Match(8, 10, thirdTeam, fourthTeam))).isNotNull();
            assertThat(matchDao.create(new Match(10, 4, fifthTeam, sixthTeam))).isNotNull();

            //5. Get all teams where player played
            QueryBuilder teamsWherePlayedPlayer = playerTeamDao.queryBuilder();
            teamsWherePlayedPlayer.where().eq("player_id", playerToFind.getId());

            //6. Query for all won matches

            QueryBuilder queryAsRedTeam = matchDao.queryBuilder();

            queryAsRedTeam.join("redTeam_id", "team_id", teamsWherePlayedPlayer);
            queryAsRedTeam.where().eq("redGoals", 10);

            assertThat(queryAsRedTeam.countOf()).isEqualTo(3);

            QueryBuilder queryAsBlueTeam = matchDao.queryBuilder();

            queryAsBlueTeam.join("blueTeam_id", "team_id", teamsWherePlayedPlayer);
            queryAsBlueTeam.where().eq("blueGoals", 10);

            assertThat(queryAsBlueTeam.countOf()).isEqualTo(0);
        } catch (SQLException e) {
            assertThat((Object) e).isNull();
        }
    }

}
