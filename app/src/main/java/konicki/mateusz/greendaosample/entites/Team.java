package konicki.mateusz.greendaosample.entites;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Mateusz on 23.04.2017.
 */
@Entity
public class Team {

    @Id(autoincrement = true)
    private Long id;

    @ToMany
    @JoinEntity(
            entity = PlayerTeam.class,
            targetProperty = "playerId",
            sourceProperty = "teamId"
    )
    private List<Player> players;

    @ToMany
    @JoinEntity(
            entity = TeamMatch.class,
            sourceProperty = "teamId",
            targetProperty = "matchId"
    )
    private List<Match> matches;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1539804063)
    private transient TeamDao myDao;

    public Team(List<Player> players) {
        this.players = players;
    }

    @Generated(hash = 770497656)
    public Team(Long id) {
        this.id = id;
    }

    @Generated(hash = 882286361)
    public Team() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2039288919)
    public List<Match> getMatches() {
        if (matches == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MatchDao targetDao = daoSession.getMatchDao();
            List<Match> matchesNew = targetDao._queryTeam_Matches(id);
            synchronized (this) {
                if (matches == null) {
                    matches = matchesNew;
                }
            }
        }
        return matches;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 892778351)
    public synchronized void resetMatches() {
        matches = null;
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 675549608)
    public List<Player> getPlayers() {
        if (players == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PlayerDao targetDao = daoSession.getPlayerDao();
            List<Player> playersNew = targetDao._queryTeam_Players(id);
            synchronized (this) {
                if (players == null) {
                    players = playersNew;
                }
            }
        }
        return players;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1686450619)
    public synchronized void resetPlayers() {
        players = null;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 256592523)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTeamDao() : null;
    }
}
