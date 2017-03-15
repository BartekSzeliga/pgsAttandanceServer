package pgs.attandance.common.core;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @OneToMany(mappedBy = "activities")
    private List<ActivitiesToUser> activitiesToUser;

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

    public List<ActivitiesToUser> getActivitiesToUser() {
        return activitiesToUser;
    }

    public void setActivitiesToUser(List<ActivitiesToUser> activitiesToUser) {
        this.activitiesToUser = activitiesToUser;
    }
}
