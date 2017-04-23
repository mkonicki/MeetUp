package konicki.mateusz.greendaosample.entites;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Mateusz on 17.04.2017.
 */
@Entity
public class Goal {

    @Id(autoincrement = true)
    private Long id;

    private Long teamId;

    private Long matchId;

    private Date goalDate;

    @ToOne(joinProperty = "teamId")
    private Team team;


    @ToOne(joinProperty = "matchId")
    private Match match;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1457320297)
    private transient GoalDao myDao;


    @Generated(hash = 1757017466)
    public Goal(Long id, Long teamId, Long matchId, Date goalDate) {
        this.id = id;
        this.teamId = teamId;
        this.matchId = matchId;
        this.goalDate = goalDate;
    }


    @Generated(hash = 1149104271)
    public Goal() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getTeamId() {
        return this.teamId;
    }


    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }


    public Long getMatchId() {
        return this.matchId;
    }


    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }


    public Date getGoalDate() {
        return this.goalDate;
    }


    public void setGoalDate(Date goalDate) {
        this.goalDate = goalDate;
    }


    @Generated(hash = 1834174654)
    private transient Long team__resolvedKey;


    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1932727645)
    public Team getTeam() {
        Long __key = this.teamId;
        if (team__resolvedKey == null || !team__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            Team teamNew = targetDao.load(__key);
            synchronized (this) {
                team = teamNew;
                team__resolvedKey = __key;
            }
        }
        return team;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2066406844)
    public void setTeam(Team team) {
        synchronized (this) {
            this.team = team;
            teamId = team == null ? null : team.getId();
            team__resolvedKey = teamId;
        }
    }


    @Generated(hash = 74816300)
    private transient Long match__resolvedKey;


    /** To-one relationship, resolved on first access. */
    @Generated(hash = 720517776)
    public Match getMatch() {
        Long __key = this.matchId;
        if (match__resolvedKey == null || !match__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MatchDao targetDao = daoSession.getMatchDao();
            Match matchNew = targetDao.load(__key);
            synchronized (this) {
                match = matchNew;
                match__resolvedKey = __key;
            }
        }
        return match;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 181590624)
    public void setMatch(Match match) {
        synchronized (this) {
            this.match = match;
            matchId = match == null ? null : match.getId();
            match__resolvedKey = matchId;
        }
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }


    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1503432607)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGoalDao() : null;
    }
}
