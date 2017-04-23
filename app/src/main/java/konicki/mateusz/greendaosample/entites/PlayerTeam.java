package konicki.mateusz.greendaosample.entites;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Mateusz on 23.04.2017.
 */
@Entity
public class PlayerTeam {
    @Id(autoincrement = true)
    private Long id;

    private Long playerId;

    private Long teamId;

    @ToOne(joinProperty = "playerId")
    private Player player;

    @ToOne(joinProperty = "teamId")
    private Team team;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 684852848)
    private transient PlayerTeamDao myDao;

    @Generated(hash = 714533041)
    public PlayerTeam(Long id, Long playerId, Long teamId) {
        this.id = id;
        this.playerId = playerId;
        this.teamId = teamId;
    }

    @Generated(hash = 317941793)
    public PlayerTeam() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getTeamId() {
        return this.teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Generated(hash = 570499689)
    private transient Long player__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1928466470)
    public Player getPlayer() {
        Long __key = this.playerId;
        if (player__resolvedKey == null || !player__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlayerDao targetDao = daoSession.getPlayerDao();
            Player playerNew = targetDao.load(__key);
            synchronized (this) {
                player = playerNew;
                player__resolvedKey = __key;
            }
        }
        return player;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1850710237)
    public void setPlayer(Player player) {
        synchronized (this) {
            this.player = player;
            playerId = player == null ? null : player.getId();
            player__resolvedKey = playerId;
        }
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
    @Generated(hash = 1715416673)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPlayerTeamDao() : null;
    }

}
