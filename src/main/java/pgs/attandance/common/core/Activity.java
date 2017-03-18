package pgs.attandance.common.core;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "Activities_id_seq", sequenceName = "Activities_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Size(min = 4, max = 30)
    private String name;

    @OneToMany(mappedBy = "activity")
    private List<ActivitiesToUser> activitiesToUser3;

    @NotNull
    private String activityPlace;

    @NotNull
    private LocalDateTime activityDate;

    private String activityDescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ActivitiesToUser> getActivitiesToUser3() {
        return activitiesToUser3;
    }

    public void setActivitiesToUser3(List<ActivitiesToUser> activitiesToUser3) {
        this.activitiesToUser3 = activitiesToUser3;
    }

    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace;
    }

    public LocalDateTime getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDateTime activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }
}
