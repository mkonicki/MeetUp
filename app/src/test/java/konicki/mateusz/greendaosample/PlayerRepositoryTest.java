package konicki.mateusz.greendaosample;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.sql.SQLException;
import java.util.List;

import konicki.mateusz.greendaosample.database.DBHelper;
import konicki.mateusz.greendaosample.database.repositories.PlayerRepository;
import konicki.mateusz.greendaosample.entites.Player;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 23.04.2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", sdk = Build.VERSION_CODES.LOLLIPOP)
public class PlayerRepositoryTest {


    @Test
    public void insertTest() {
        //GIVEN
        Context context = Robolectric.setupActivity(TestActivity.class);
        PlayerRepository repository = new PlayerRepository(context);

        //WHEN
        Player player = new Player("Kaczor");

        Long id = repository.create(player);

        assertThat(id).isNotNull();


        //THEN
        List<Player> players = repository.getAll();

        assertThat(players).containsExactly(player);
    }


    @Test
    public void uniqueNameInsertTest() {
        //GIVEN
        Context context = Robolectric.setupActivity(TestActivity.class);
        PlayerRepository repository = new PlayerRepository(context);

        //WHEN
        Player player = new Player("Kaczor");

        Long id = repository.create(player);

        assertThat(id).isNotNull();

        Player playerWithDuplicatedName = new Player("Kaczor");
        //Throw SQLiteConstraintException when value is not unique
        try {

            repository.create(playerWithDuplicatedName);

        } catch (SQLiteConstraintException exception) {
            //THEN
            assertThat(exception).isNotNull();
        }
    }
}
