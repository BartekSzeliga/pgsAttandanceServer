package pgs.attandance.common.core;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Attendance")
public class Attendance {

    @Id
    @SequenceGenerator(schema = "public", name = "id_generator",
            sequenceName = "Calendar_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    protected Long id;

    @NotNull
    private Boolean attendance;

    @NotNull
    @ManyToOne
    private ActivitiesToUser activitiesToUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    public ActivitiesToUser getActivitiesToUser() {
        return activitiesToUser;
    }

    public void setActivitiesToUser(ActivitiesToUser activitiesToUser) {
        this.activitiesToUser = activitiesToUser;
    }
}
