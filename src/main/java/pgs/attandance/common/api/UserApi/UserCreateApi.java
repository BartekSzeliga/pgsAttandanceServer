package pgs.attandance.common.api.UserApi;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;


public class UserCreateApi {

    @ApiModelProperty(value = "Email", required = true)
    private String email;

    @ApiModelProperty(value = "First Name", required = true)
    private String firstName;

    @ApiModelProperty(value = "Last Name", required = true)
    private String lastName;

    @ApiModelProperty(value = "password", required = true)
    private String password;

    @ApiModelProperty(value = "Roles", required = true)
    private List<String> roles;


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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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
}

