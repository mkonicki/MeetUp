package konicki.mateusz.greendaosample.entites;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.Locale;

/**
 * Created by Mateusz on 17.04.2017.
 */
@Entity(active = true)
public class Match {
    @Id(autoincrement = true)
    private Long id;

    private Date begin;

    private Date end;

    public int redGoals;

    public int blueGoals;

    private Long redTeamId;

    private Long blueTeamId;

    @ToOne(joinProperty = "redTeamId")
    private Team redTeam;

    @ToOne(joinProperty = "blueTeamId")
    private Team blueTeam;

    @Convert(converter = MatchTypeConverter.class, columnType = Integer.class)
    private MatchType matchType;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 522467795)
    private transient MatchDao myDao;

    @Generated(hash = 476078068)
    private transient Long redTeam__resolvedKey;

    @Generated(hash = 944270540)
    private transient Long blueTeam__resolvedKey;

    public Match(MatchType matchType) {
        this.matchType = matchType;
    }

    public Match(MatchType matchType, int redGoals, int blueGoals) {
        this.matchType = matchType;
        this.redGoals = redGoals;
        this.blueGoals = blueGoals;
    }

    @Generated(hash = 1834681287)
    public Match() {
    }

    @Generated(hash = 156466388)
    public Match(Long id, Date begin, Date end, int redGoals, int blueGoals, Long redTeamId,
                 Long blueTeamId, MatchType matchType) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.redGoals = redGoals;
        this.blueGoals = blueGoals;
        this.redTeamId = redTeamId;
        this.blueTeamId = blueTeamId;
        this.matchType = matchType;
    }


    public Match(int redGoals, int blueGoals, Team redTeam, Team blueTeam) {
        this.redGoals = redGoals;
        this.blueGoals = blueGoals;
        this.redTeamId = redTeam.getId();
        this.blueTeamId = blueTeam.getId();
    }

    public Match(MatchType matchType, Team blueTeam, Team redTeam) {
        this.matchType = matchType;
        this.blueTeamId = blueTeam.getId();
        this.redTeamId = redTeam.getId();
        this.redGoals = 0;
        this.blueGoals = 0;
    }

    public Match(Date date) {
        begin = date;
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

    public Long getRedTeamId() {
        return this.redTeamId;
    }

    public void setRedTeamId(Long redTeamId) {
        this.redTeamId = redTeamId;
    }

    public Long getBlueTeamId() {
        return this.blueTeamId;
    }

    public void setBlueTeamId(Long blueTeamId) {
        this.blueTeamId = blueTeamId;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1765015403)
    public Team getRedTeam() {
        Long __key = this.redTeamId;
        if (redTeam__resolvedKey == null || !redTeam__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            Team redTeamNew = targetDao.load(__key);
            synchronized (this) {
                redTeam = redTeamNew;
                redTeam__resolvedKey = __key;
            }
        }
        return redTeam;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1353001501)
    public void setRedTeam(Team redTeam) {
        synchronized (this) {
            this.redTeam = redTeam;
            redTeamId = redTeam == null ? null : redTeam.getId();
            redTeam__resolvedKey = redTeamId;
        }
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1052516433)
    public Team getBlueTeam() {
        Long __key = this.blueTeamId;
        if (blueTeam__resolvedKey == null || !blueTeam__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            Team blueTeamNew = targetDao.load(__key);
            synchronized (this) {
                blueTeam = blueTeamNew;
                blueTeam__resolvedKey = __key;
            }
        }
        return blueTeam;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 247157287)
    public void setBlueTeam(Team blueTeam) {
        synchronized (this) {
            this.blueTeam = blueTeam;
            blueTeamId = blueTeam == null ? null : blueTeam.getId();
            blueTeam__resolvedKey = blueTeamId;
        }
    }

    public String getScore() {
        return String.format(Locale.getDefault(), "%s - %s", redGoals, blueGoals);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 88911878)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMatchDao() : null;
    }


}
