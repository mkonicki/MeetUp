package konicki.mateusz.greendaosample;

import android.content.Context;
import android.os.Build;

import org.greenrobot.greendao.query.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import konicki.mateusz.greendaosample.database.DBHelper;
import konicki.mateusz.greendaosample.entites.DaoMaster;
import konicki.mateusz.greendaosample.entites.DaoSession;
import konicki.mateusz.greendaosample.entites.Player;
import konicki.mateusz.greendaosample.entites.PlayerDao;

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
        playerDao.insert(player);
        playerDao.insert(secondPlayer);
        playerDao.insert(thirdPlayer);
        playerDao.insert(fourthPlayer);


        QueryBuilder queryBuilder = playerDao.queryBuilder()
                .where(PlayerDao.Properties.Nickname.like(playerName));

        assertThat(queryBuilder.count()).isEqualTo(1);

        assertThat(queryBuilder.list()).containsOnly(player);

    }


    @Test
    public void findPlayerByNameWithSeeder() {

        DaoSession session = master.newSession();

        String playerName = "Kaczka";
        Player player = new Player(playerName);


        PlayerDao playerDao = session.getPlayerDao();

        //SEED DATA
        playerDao.insert(player);

        seedData(session);

        QueryBuilder queryBuilder = playerDao.queryBuilder()
                .where(PlayerDao.Properties.Nickname.like(playerName));

        assertThat(queryBuilder.count()).isEqualTo(1);

        assertThat(queryBuilder.list()).containsOnly(player);

    }


    private void seedData(DaoSession session){
        PlayerDao playerDao = session.getPlayerDao();

        Player secondPlayer = new Player("Kurczak");
        Player thirdPlayer = new Player("Joey");
        Player fourthPlayer = new Player("Chandler");

        playerDao.insert(secondPlayer);
        playerDao.insert(thirdPlayer);
        playerDao.insert(fourthPlayer);
    }

}
