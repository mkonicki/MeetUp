package konicki.mateusz.greendaosample.components.matchList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import konicki.mateusz.greendaosample.R;
import konicki.mateusz.greendaosample.components.matchstartpopup.MatchStartPopup;
import konicki.mateusz.greendaosample.components.player.PlayerDialog;
import konicki.mateusz.greendaosample.components.team.TeamDialog;
import konicki.mateusz.greendaosample.database.DBHelper;

public class MatchListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         findViewById(R.id.match).setOnClickListener(
                 v -> new MatchStartPopup().show(this.getFragmentManager(), "Rozpocznij mecz"));
        findViewById(R.id.player).setOnClickListener(
                v -> new PlayerDialog().show(this.getFragmentManager(), "Dodaj nowego gracza"));
        findViewById(R.id.team).setOnClickListener(
                v -> new TeamDialog().show(this.getFragmentManager(), "Dodaj nowy zespół"));

        ((ListView) findViewById(R.id.match_history)).setAdapter(
                new MatchListAdapter(
                        new DBHelper(this).getSession().getMatchDao().loadAll()));
    }

}
