package pgs.attandance.common.core;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "Roles_id_seq", sequenceName = "Roless_id_seq", allocationSize = 1)
    private long id;

    @NotNull
    @Column(name = "rolename")
    private String roleName;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public Role() {
    }

    public Role(String roleName, User user) {
        this.roleName = roleName;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
