package konicki.mateusz.greendaosample.matchList;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import konicki.mateusz.greendaosample.BR;
import konicki.mateusz.greendaosample.R;
import konicki.mateusz.greendaosample.entites.Match;

/**
 * Created by Mateusz on 25.04.2017.
 */

class MatchListAdapter extends BaseAdapter {
    private List<Match> matches;

    MatchListAdapter(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public int getCount() {
        return matches.size();
    }

    @Override
    public Match getItem(int position) {
        return matches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return matches.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.match_item, parent, false);
        MatchItemObservable observable = new MatchItemObservable(getItem(position));
        binding.setVariable(BR.matchItem, observable);
        return binding.getRoot();
    }
}
