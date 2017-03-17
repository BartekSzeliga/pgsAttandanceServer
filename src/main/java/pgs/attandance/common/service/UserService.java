package pgs.attandance.common.service;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pgs.attandance.common.DTO.UserDTO;
import pgs.attandance.common.api.UserCreateApi;
import pgs.attandance.common.api.UserUpdateApi;
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
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                roleList
        );

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
        user.setEmail(userCreateApi.getEmail());
        user.setFirstName(userCreateApi.getFirstName());
        user.setLastName(userCreateApi.getLastName());
        user.setPassword(hashedPassword);
        userRepository.save(user);

        List<String> roles = userCreateApi.getRoles();
        List<Role> roleList = getUserRoles(roles, user);

        user.setRoles(roleList);
        return convertToDTO(user);
    }

    public List<Role> getUserRoles(List<String> roles, User user) {
        List<Role> roleList = new ArrayList<>();
        roles.forEach(role -> {
            Role rol = new Role();
            rol.setRoleName(role);
            rol.setUser(user);
            roleRepository.save(rol);
            roleList.add(rol);
        });
        return roleList;

    }


    public UserDTO update(Long id, UserUpdateApi userUpdateApi) {
        User user = userRepository.findById(id);

        user.setEmail(userUpdateApi.getEmail());
        user.setFirstName(userUpdateApi.getFirstName());
        user.setLastName(userUpdateApi.getLastName());
        if (userUpdateApi.getPassword() != null) {
            String hashedPassword = BCrypt.hashpw(userUpdateApi.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
        }
        userRepository.save(user);

        return convertToDTO(user);
    }
}