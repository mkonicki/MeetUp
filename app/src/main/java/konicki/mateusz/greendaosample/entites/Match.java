package konicki.mateusz.greendaosample.entites;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Mateusz on 17.04.2017.
 */
@Entity
public class Match {
    @Id(autoincrement = true)
    private Long id;

    private Date begin;

    private Date end;

    @ToMany
    @JoinEntity(
            entity = TeamMatch.class,
            targetProperty = "teamId",
            sourceProperty = "matchId"
    )
    private List<Team> teams;


    @ToMany(referencedJoinProperty = "matchId")
    private List<Goal> goals;

    @Convert(converter = MatchTypeConverter.class, columnType = Integer.class)
    private MatchType matchType;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 522467795)
    private transient MatchDao myDao;

    @Generated(hash = 1284434006)
    public Match(Long id, Date begin, Date end, MatchType matchType) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.matchType = matchType;
    }

    @Generated(hash = 1834681287)
    public Match() {
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1090154)
    public List<Team> getTeams() {
        if (teams == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            List<Team> teamsNew = targetDao._queryMatch_Teams(id);
            synchronized (this) {
                if (teams == null) {
                    teams = teamsNew;
                }
            }
        }
        return teams;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 755494824)
    public synchronized void resetTeams() {
        teams = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1433953387)
    public List<Goal> getGoals() {
        if (goals == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GoalDao targetDao = daoSession.getGoalDao();
            List<Goal> goalsNew = targetDao._queryMatch_Goals(id);
            synchronized (this) {
                if (goals == null) {
                    goals = goalsNew;
                }
            }
        }
        return goals;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1654856844)
    public synchronized void resetGoals() {
        goals = null;
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
    @Generated(hash = 88911878)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMatchDao() : null;
    }

}
