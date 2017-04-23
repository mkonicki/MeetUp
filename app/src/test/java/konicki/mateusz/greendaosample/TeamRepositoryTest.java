package konicki.mateusz.greendaosample;

import android.content.Context;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Collections;
import java.util.List;

import edu.emory.mathcs.backport.java.util.Arrays;
import konicki.mateusz.greendaosample.database.repositories.PlayerRepository;
import konicki.mateusz.greendaosample.database.repositories.TeamRepository;
import konicki.mateusz.greendaosample.entites.Player;
import konicki.mateusz.greendaosample.entites.Team;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 23.04.2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", sdk = Build.VERSION_CODES.LOLLIPOP)
public class TeamRepositoryTest {

    @Test
    public void insertTest() {
        //GIVEN
        Context context = Robolectric.setupActivity(TestActivity.class);
        PlayerRepository repository = new PlayerRepository(context);
        TeamRepository teamRepository = new TeamRepository(context);
        //WHEN
        Player player = new Player("Kaczor");

        Long id = repository.create(player);

        assertThat(id).isNotNull();

        Team team = new Team(Collections.singletonList(player));

        Long teamId = teamRepository.create(team);

        //THEN
        assertThat(teamId).isNotNull();

        List<Team> teams = teamRepository.getAll();

        assertThat(teams).containsOnlyOnce(team);

    }


}
