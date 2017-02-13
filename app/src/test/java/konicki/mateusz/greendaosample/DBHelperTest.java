package konicki.mateusz.greendaosample;

import android.content.Context;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import konicki.mateusz.greendaosample.entites.Task;
import konicki.mateusz.greendaosample.entites.TaskDao;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Mateusz on 11.02.2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", sdk = Build.VERSION_CODES.LOLLIPOP)
public class DBHelperTest {
    @Test
    public void initializationTest() {
        //GIVEN
        Context context = Robolectric.setupActivity(TestActivity.class);
        DBHelper dbHelper = new DBHelper(context);

        assertThat(dbHelper.getSession()).isNotNull();

    }

    @Test
    public void insertTest() {
        //GIVEN
        Context context = Robolectric.setupActivity(TestActivity.class);
        DBHelper dbHelper = new DBHelper(context);
        TaskDao taskDao = dbHelper.getSession().getTaskDao();

        assertThat(taskDao).isNotNull();
        //WHEN
        Task task = new Task("Cook chicken");

        Long id = taskDao.insert(task);

        assertThat(id).isNotNull();


        //THEN
        List<Task> tasks = taskDao.loadAll();

        assertThat(tasks).containsExactly(task);
    }

}
