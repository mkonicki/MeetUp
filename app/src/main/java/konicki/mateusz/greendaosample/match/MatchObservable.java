package konicki.mateusz.greendaosample.match;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import konicki.mateusz.greendaosample.database.DBHelper;
import konicki.mateusz.greendaosample.entites.Match;
import konicki.mateusz.greendaosample.matchList.MatchListActivity;

/**
 * Created by Mateusz on 25.04.2017.
 */

public class MatchObservable extends BaseObservable {
    private Match match;
    private Context context;

    public MatchObservable(Match match, Context context) {
        new DBHelper(context).getSession().insert(match);
        this.match = match;
        this.context = context;
    }

    @Bindable
    public String getScore() {
        return match.getScore();
    }

    public void onBlueTeamGoal(View view) {
        match.blueGoals++;
        match.update();
        finishMatch();
        synchronized (this) {
            notifyChange();
        }
    }

    public void onRedTeamGoal(View view) {
        match.redGoals++;
        match.update();
        finishMatch();
        synchronized (this) {
            notifyChange();
        }
    }

    private boolean matchIsFinished() {
        return match.blueGoals < 10 && match.redGoals < 10;
    }

    private void finishMatch() {
        if (matchIsFinished()) {
            return;
        }
        new AlertDialog.Builder(context)
                .setTitle("Koniec meczu")
                .setMessage("Wynik " + getScore())
                .setCancelable(true)
                .setOnCancelListener(dialog ->
                        context.startActivity(
                                new Intent(context, MatchListActivity.class))).show();

    }
}
