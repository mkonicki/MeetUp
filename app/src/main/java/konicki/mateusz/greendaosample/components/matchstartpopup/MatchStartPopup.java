package konicki.mateusz.greendaosample.components.matchstartpopup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Date;
import java.util.List;

import konicki.mateusz.greendaosample.R;
import konicki.mateusz.greendaosample.components.match.MatchActivity;
import konicki.mateusz.greendaosample.database.DBHelper;
import konicki.mateusz.greendaosample.entites.Match;
import konicki.mateusz.greendaosample.entites.MatchType;
import konicki.mateusz.greendaosample.entites.Team;

/**
 * Created by Mateusz on 26.04.2017.
 */

public class MatchStartPopup extends DialogFragment {

    private final String MATCH_ID = "MATCH_ID";

    private Match match = new Match(new Date());

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        DBHelper dbHelper = new DBHelper(this.getActivity());
        View view = initializeView(dbHelper.getSession().getTeamDao().loadAll());
        builder.setView(view);
        builder.setPositiveButton("Zapisz", (dialogInterface, i) -> {
                    onSaveClick(view);
                }
        );
        return builder.create();
    }

    private View initializeView(List<Team> teams) {
        View view = this.getActivity().getLayoutInflater().inflate(R.layout.match_start_popup, null);

        ((Spinner) view.findViewById(R.id.match_type)).setAdapter(new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_list_item_1, MatchType.values()));

        ((Spinner) view.findViewById(R.id.red_team)).setAdapter(new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_list_item_1, teams));

        ((Spinner) view.findViewById(R.id.blue_team)).setAdapter(new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_list_item_1, teams));
        return view;
    }


    private void onSaveClick(View view) {
        setMatch(view);
        new DBHelper(this.getActivity()).getSession().insert(match);
        Intent in = new Intent(getActivity(), MatchActivity.class);
        in.putExtra("MATCH_ID", match.getId());
        getActivity().startActivity(in);
    }

    private void setMatch(View view) {
        match.setMatchType((MatchType) ((Spinner) view.findViewById(R.id.match_type)).getSelectedItem());
        match.setRedTeam((Team) ((Spinner) view.findViewById(R.id.red_team)).getSelectedItem());
        match.setBlueTeam((Team) ((Spinner) view.findViewById(R.id.blue_team)).getSelectedItem());
    }
}
