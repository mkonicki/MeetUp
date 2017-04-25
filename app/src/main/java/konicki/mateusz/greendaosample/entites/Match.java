package konicki.mateusz.greendaosample.entites;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Mateusz on 17.04.2017.
 */
public class Match extends SugarRecord<Match> {

    private Date begin;
    private Date end;
    public int redGoals;
    public int blueGoals;

    private Team redTeam;

    private Team blueTeam;

    private MatchType matchType;

    public Match(MatchType matchType) {
        this.matchType = matchType;
    }

    public Match(MatchType matchType, int redGoals, int blueGoals) {
        this.matchType = matchType;
        this.redGoals = redGoals;
        this.blueGoals = blueGoals;
    }

    public Match() {
    }


    public Match(MatchType matchType, Team blueTeam, Team redTeam) {
        this.matchType = matchType;
        this.blueTeam = blueTeam;
        this.redTeam = redTeam;
        this.redGoals = 0;
        this.blueGoals = 0;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBegin() {
        return this.begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return this.end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public MatchType getMatchType() {
        return this.matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public int getRedGoals() {
        return this.redGoals;
    }

    public void setRedGoals(int redGoals) {
        this.redGoals = redGoals;
    }

    public int getBlueGoals() {
        return this.blueGoals;
    }

    public void setBlueGoals(int blueGoals) {
        this.blueGoals = blueGoals;
    }

}
