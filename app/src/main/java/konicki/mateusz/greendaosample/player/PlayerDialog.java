package konicki.mateusz.greendaosample.player;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;

import konicki.mateusz.greendaosample.BR;
import konicki.mateusz.greendaosample.R;
import konicki.mateusz.greendaosample.database.DBHelper;
import konicki.mateusz.greendaosample.entites.Player;

/**
 * Created by mkonicki on 26.04.2017.
 */
public class PlayerDialog extends DialogFragment {

    private Player player = new Player();

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this.getActivity()), R.layout.player_dialog, null, false);
        binding.setVariable(BR.player, player);
        builder.setView(binding.getRoot());
        DBHelper dbHelper = new DBHelper(this.getActivity());
        builder.setPositiveButton("Zapisz", (dialogInterface, i) ->
            dbHelper.getSession().insert(player)
        );
        return builder.create();
    }
}
