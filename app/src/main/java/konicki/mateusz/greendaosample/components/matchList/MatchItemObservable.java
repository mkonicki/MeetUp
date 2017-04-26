package konicki.mateusz.greendaosample.components.matchList;

import android.databinding.BaseObservable;

import java.util.Locale;

import konicki.mateusz.greendaosample.entites.Match;

/**
 * Created by Mateusz on 25.04.2017.
 */

public class MatchItemObservable extends BaseObservable {
    private Match match;

    MatchItemObservable(Match match) {
        this.match = match;
    }

    public String getScore() {
        return match.getScore();
    }

    public String getPlayers() {
        return String.format(Locale.getDefault(), "Czerwoni: %s\nNiebiescy: %s", match.getRedTeam().toString(), match.getBlueTeam().toString());
    }
}
