package pgs.attandance.common.core;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "Users_id_seq", sequenceName = "Users_id_seq", allocationSize = 1)
    private long id;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<ActivitiesToUser> activitiesToUser2;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
