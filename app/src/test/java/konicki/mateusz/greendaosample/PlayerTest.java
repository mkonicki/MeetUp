package konicki.mateusz.greendaosample;

import android.content.Context;
import android.os.Build;

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
}
