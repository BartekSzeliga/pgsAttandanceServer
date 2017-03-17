package pgs.attandance.common.core;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "status")
    private boolean status;

    @OneToMany(mappedBy = "user")
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<ActivitiesToUser> activitiesToUser2;


    public User() {
    }

    public User(String username, String password, boolean status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public User(String username, String password, boolean status, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<ActivitiesToUser> getActivitiesToUser2() {
        return activitiesToUser2;
    }

    public void setActivitiesToUser2(List<ActivitiesToUser> activitiesToUser2) {
        this.activitiesToUser2 = activitiesToUser2;
    }
}
