package pgs.attandance.common.api;



import pgs.attandance.common.DTO.UserDTO;

import java.util.List;


public class UserResponse {
    private List<UserDTO> users;

    public UserResponse() {
    }

    public UserResponse(List<UserDTO> users) {
        this.users = users;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}

