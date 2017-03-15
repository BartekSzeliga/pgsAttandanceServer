package pgs.attandance.common.service;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pgs.attandance.common.DTO.UserDTO;
import pgs.attandance.common.api.UserCreateApi;
import pgs.attandance.common.core.Role;
import pgs.attandance.common.core.User;
import pgs.attandance.common.repository.RoleRepository;
import pgs.attandance.common.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public UserDTO convertToDTO(User user) {
        List<Role> roles = user.getRoles();
        List<String> roleList = convertToRoleDTO(roles);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUsername());
        userDTO.setRoles(roleList);

        return userDTO;
    }

    public List<String> convertToRoleDTO(List<Role> roles) {
        List<String> roleList = new ArrayList<>();

        roles.forEach(role -> roleList.add(role.getRoleName()));
        return roleList;
    }

    public UserDTO create(UserCreateApi userCreateApi) {
        User user;

        String hashedPassword = BCrypt.hashpw(userCreateApi.getPassword(), BCrypt.gensalt());
        user = new User();
        user.setUsername(userCreateApi.getUserName());
        user.setPassword(hashedPassword);
        user.setStatus(true);
        userRepository.save(user);

        List<String> roles = userCreateApi.getRoles();
        List<Role> roleList = new ArrayList<>();
        roles.forEach(role -> {
            Role rol = new Role();
            rol.setRoleName(role);
            rol.setUser(user);
            roleRepository.save(rol);
            roleList.add(rol);
        });
        user.setRoles(roleList);
        return convertToDTO(user);
    }


}