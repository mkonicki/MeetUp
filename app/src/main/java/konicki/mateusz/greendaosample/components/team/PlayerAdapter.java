package konicki.mateusz.greendaosample.components.team;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.annimon.stream.Stream;

import java.util.List;

import konicki.mateusz.greendaosample.BR;
import konicki.mateusz.greendaosample.R;
import konicki.mateusz.greendaosample.entites.Player;

/**
 * Created by mkonicki on 26.04.2017.
 */
public class PlayerAdapter extends BaseAdapter {
    private List<Player> players;
    private Context context;

    public PlayerAdapter(List<Player> players, Context context) {
        this.players = players;
        this.context = context;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int i) {
        return players.get(i);
    }

    @Override
    public long getItemId(int i) {
        return players.get(i).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.team_player_item, null, false);
        binding.setVariable(BR.player, getItem(position));
        return binding.getRoot();
    }

    public List<Player> getAllSelectedPlayers() {
        return Stream.of(players)
                .filter(player -> player.isSelected())
                .toList();
    }
}
