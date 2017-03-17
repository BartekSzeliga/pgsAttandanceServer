package pgs.attandance.common.core;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "activitiesToUser")
public class ActivitiesToUser {

    @Id
    @SequenceGenerator(schema = "public", name = "id_generator",
            sequenceName = "ActivitiesToUser_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    protected Long id;


    @NotNull
    @ManyToOne()
    private Activity activity;

    @NotNull
    @ManyToOne()
    private User user;

    @NotNull
    private Boolean isPresent;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }
}
