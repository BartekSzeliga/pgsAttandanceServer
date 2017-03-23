package pgs.attandance.common.service;


import com.google.common.base.Preconditions;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pgs.attandance.common.DTO.UserDTO;
import pgs.attandance.common.api.UserApi.UserCreateApi;
import pgs.attandance.common.api.UserApi.UserUpdateApi;
import pgs.attandance.common.core.ActivitiesToUser;
import pgs.attandance.common.core.Activity;
import pgs.attandance.common.core.Role;
import pgs.attandance.common.core.User;
import pgs.attandance.common.repository.ActivitiesToUserRepository;
import pgs.attandance.common.repository.ActivityRepository;
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

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivitiesToUserRepository activitiesToUserRepository;

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
        User user = new User();
        String hashedPassword = BCrypt.hashpw(userCreateApi.getPassword(), BCrypt.gensalt());

        user.setEmail(userCreateApi.getEmail());
        user.setFirstName(userCreateApi.getFirstName());
        user.setLastName(userCreateApi.getLastName());
        user.setPassword(hashedPassword);
        userRepository.save(user);

        Role studentRole = new Role("Student", user);
        roleRepository.save(studentRole);
        List<Role> roleList = new ArrayList<>();
        roleList.add(studentRole);
        user.setRoles(roleList);

        List<Activity> activities = activityRepository.findAll();
        addActivitiesToUser(user, activities);

        return convertToDTO(user);
    }

    private void addActivitiesToUser(User user, List<Activity> activities) {
        activities.forEach(activity -> {
            ActivitiesToUser activitiesToUser = new ActivitiesToUser();
            activitiesToUser.setUser(user);
            activitiesToUser.setActivity(activity);
            activitiesToUserRepository.save(activitiesToUser);
        });
    }

    public UserDTO update(Long id, UserUpdateApi userUpdateApi) {
        User user = userRepository.findById(id);
        Preconditions.checkNotNull(user, "Podaj poprawne id użytkownika");

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