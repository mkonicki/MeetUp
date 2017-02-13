package konicki.mateusz.greendaosample.entites;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by Mateusz on 11.02.2017.
 */

@Entity
public class Task {
    @Id(autoincrement = true)
    private Long id;

    private String toDo;

    @Generated(hash = 1164913563)
    public Task(Long id, String toDo) {
        this.id = id;
        this.toDo = toDo;
    }

    public Task(String toDo) {
        this.toDo = toDo;
    }

    @Generated(hash = 733837707)
    public Task() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getToDo() {
        return this.toDo;
    }


    public void setToDo(String toDo) {
        this.toDo = toDo;
    }
}
