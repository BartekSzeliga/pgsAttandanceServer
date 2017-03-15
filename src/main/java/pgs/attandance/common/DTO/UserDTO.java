package pgs.attandance.common.DTO;


import java.util.List;

public class UserDTO {
    private String userName;
    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String userName, List<String> roles) {
        this.userName = userName;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
