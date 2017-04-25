package konicki.mateusz.greendaosample.matchList;

import android.databinding.BaseObservable;

import konicki.mateusz.greendaosample.entites.Match;
import konicki.mateusz.greendaosample.entites.Player;

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
        String players;
        players = "Czerwoni: ";
        if (match.getRedTeam() != null)
            for (Player player : match.getRedTeam().getPlayers()) {
                if (player != null)
                    players += player.getNickname();
            }
        players += "\n Niebiescy: ";
        if (match.getBlueTeam() != null)
            for (Player player : match.getBlueTeam().getPlayers()) {
                if (player != null)
                    players += player.getNickname();
            }
        return players;
    }
}
