package konicki.mateusz.greendaosample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import konicki.mateusz.greendaosample.entites.Match;
import konicki.mateusz.greendaosample.entites.Player;
import konicki.mateusz.greendaosample.entites.PlayerTeam;
import konicki.mateusz.greendaosample.entites.Team;


/**
 * Created by Mateusz on 11.02.2017.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "GREENDAO_SAMPLE";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        this(context, DATABASE_NAME, DATABASE_VERSION);
    }

    private DBHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Player.class);
            TableUtils.createTable(connectionSource, Team.class);
            TableUtils.createTable(connectionSource, PlayerTeam.class);
            TableUtils.createTable(connectionSource, Match.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

}
