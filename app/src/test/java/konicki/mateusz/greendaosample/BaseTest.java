package konicki.mateusz.greendaosample;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestLifecycleApplication;
import org.robolectric.annotation.Config;

import java.lang.reflect.Method;


/**
 * Created by Mateusz on 23.04.2017.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", sdk = Build.VERSION_CODES.LOLLIPOP)
public class BaseTest extends Application  implements TestLifecycleApplication {

    public BaseTest() {
        Context context = Robolectric.setupActivity(TestActivity.class);

    }

    @Override
    public void beforeTest(Method method) {

    }

    @Override
    public void prepareTest(Object test) {

    }

    @Override
    public void afterTest(Method method) {

    }
}
