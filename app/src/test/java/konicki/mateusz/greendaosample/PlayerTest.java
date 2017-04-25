package konicki.mateusz.greendaosample;

import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.sql.SQLException;
import java.util.List;

import konicki.mateusz.greendaosample.entites.Player;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 23.04.2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", sdk = Build.VERSION_CODES.LOLLIPOP)
public class PlayerTest extends BaseTest {

    @Test
    public void insertPlayerTest() {
            Player player = new Player("Kaczka");

            player.save();

            List<Player> players = Player.listAll(Player.class);

            assertThat(players).containsOnlyOnce(player);

    }

//
//    @Test
//    public void findPlayerByName() {
//
//        Dao<Player, Long> dao;
//        try {
//            dao = dbHelper.getDao(Player.class);
//
//            String playerName = "Kaczka";
//            Player player = new Player(playerName);
//            Player secondPlayer = new Player("Kurczak");
//            Player thirdPlayer = new Player("Joey");
//            Player fourthPlayer = new Player("Chandler");
//
//            //SEED DATA
//            dao.create(player);
//            dao.create(secondPlayer);
//            dao.create(thirdPlayer);
//            dao.create(fourthPlayer);
//
//
//            List<Player> players = dao.queryBuilder()
//                    .where().eq("nickname", playerName).query();
//
//            assertThat(players.size()).isEqualTo(1);
//
//            assertThat(players).containsOnly(player);
//        } catch (SQLException e) {
//            assertThat((Object) e).isNull();
//        }
//    }


}
