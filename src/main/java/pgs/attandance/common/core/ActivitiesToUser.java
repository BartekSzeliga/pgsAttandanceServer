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
    @JoinColumn(name = "activitiesId", nullable = false)
    private Activity activities;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToMany(mappedBy = "activitiesToUser")
    private List<Attendance> attendances;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activity getActivities() {
        return activities;
    }

    public void setActivities(Activity activities) {
        this.activities = activities;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }
}
