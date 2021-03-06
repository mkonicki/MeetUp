package konicki.mateusz.greendaosample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.DatabaseOpenHelper;

import konicki.mateusz.greendaosample.entites.DaoMaster;
import konicki.mateusz.greendaosample.entites.DaoSession;

/**
 * Created by Mateusz on 11.02.2017.
 */

public class DBHelper extends DatabaseOpenHelper implements IDBHelper {

    private static final String DATABASE_NAME = "GREENDAO_SAMPLE";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        this(context, DATABASE_NAME, DATABASE_VERSION);
    }

    private DBHelper(Context context, String name, int version) {
        super(context, name, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        DaoMaster daoMaster = new DaoMaster(db);
        DaoMaster.createAllTables(daoMaster.getDatabase(), true);
    }

    @Override
    public DaoSession getSession(){
        DaoMaster master = new DaoMaster(this.getWritableDb());
        return master.newSession();
    }


}
