package konicki.mateusz.greendaosample;

import android.content.Context;
import android.os.Build;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import konicki.mateusz.greendaosample.database.DBHelper;
import konicki.mateusz.greendaosample.entites.DaoMaster;

/**
 * Created by Mateusz on 23.04.2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", sdk = Build.VERSION_CODES.LOLLIPOP)
public class BaseTest {

    protected DaoMaster master;

    public BaseTest() {
        Context context = Robolectric.setupActivity(TestActivity.class);
        DBHelper dbHelper = new DBHelper(context);
        this.master = new DaoMaster(dbHelper.getWritableDb());
    }
}
