package konicki.mateusz.greendaosample.database.repositories;

import android.content.Context;

import konicki.mateusz.greendaosample.database.DBHelper;
import konicki.mateusz.greendaosample.database.IDBHelper;

/**
 * Created by Mateusz on 23.04.2017.
 */

public class BaseRepository {
    protected IDBHelper idbHelper;

    private BaseRepository(IDBHelper idbHelper) {
        this.idbHelper = idbHelper;
    }

    public BaseRepository(Context context){
        this(new DBHelper(context));
    }
}
