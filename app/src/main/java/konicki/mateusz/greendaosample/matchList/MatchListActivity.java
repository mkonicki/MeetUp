package konicki.mateusz.greendaosample.matchList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import konicki.mateusz.greendaosample.R;
import konicki.mateusz.greendaosample.database.DBHelper;
import konicki.mateusz.greendaosample.match.MatchActivity;

public class MatchListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.match).setOnClickListener(
                v -> startActivity(new Intent(this, MatchActivity.class)));

        ((ListView) findViewById(R.id.match_history)).setAdapter(
                new MatchListAdapter(
                        new DBHelper(this).getSession().getMatchDao().loadAll()));
    }

}
