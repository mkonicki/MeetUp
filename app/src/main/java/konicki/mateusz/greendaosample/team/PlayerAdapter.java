package konicki.mateusz.greendaosample.team;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import konicki.mateusz.greendaosample.BR;
import konicki.mateusz.greendaosample.R;
import konicki.mateusz.greendaosample.entites.Player;
import konicki.mateusz.greendaosample.entites.PlayerTeam;
import konicki.mateusz.greendaosample.matchList.MatchItemObservable;

/**
 * Created by mkonicki on 26.04.2017.
 */
public class PlayerAdapter extends BaseAdapter {
    private List<Player> players;

    public PlayerAdapter(List<Player> players) {
        this.players = players;
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
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), R.layout.match_item, viewGroup, false);
        binding.setVariable(BR.player, getItem(position));
        return null;
    }
}
