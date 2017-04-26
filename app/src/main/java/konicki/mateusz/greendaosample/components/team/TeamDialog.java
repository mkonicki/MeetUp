package konicki.mateusz.greendaosample.components.team;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

import com.annimon.stream.Stream;

import konicki.mateusz.greendaosample.R;
import konicki.mateusz.greendaosample.database.DBHelper;
import konicki.mateusz.greendaosample.entites.PlayerTeam;
import konicki.mateusz.greendaosample.entites.Team;

/**
 * Created by mkonicki on 26.04.2017.
 */
public class TeamDialog extends DialogFragment {

    private PlayerAdapter adapter;
    private Team team = new Team();


    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        DBHelper dbHelper = new DBHelper(this.getActivity());
        this.adapter = new PlayerAdapter(dbHelper.getSession().getPlayerDao().loadAll(), this.getActivity());
        View view = initializeView(adapter);
        builder.setView(view);
        builder.setPositiveButton("Zapisz", (dialogInterface, i) -> {
            dbHelper.getSession().insert(team);
            Stream.of(adapter.getAllSelectedPlayers()).forEach(player ->
                    dbHelper.getSession().insert(new PlayerTeam(player.getId(), team.getId()))
            );
        });
        return builder.create();
    }

    private View initializeView(PlayerAdapter adapter) {
        View view = this.getActivity().getLayoutInflater().inflate(R.layout.team_dialog, null);
        ((ListView) view.findViewById(R.id.players_check_list)).setAdapter(adapter);
        return view;
    }


}
