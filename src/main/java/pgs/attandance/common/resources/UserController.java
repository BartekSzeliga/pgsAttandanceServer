package pgs.attandance.common.resources;


import com.google.common.base.Preconditions;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pgs.attandance.common.DTO.UserDTO;
import pgs.attandance.common.api.UserApi.UserCreateApi;
import pgs.attandance.common.api.UserApi.UserResponse;
import pgs.attandance.common.api.UserApi.UserUpdateApi;
import pgs.attandance.common.core.Role;
import pgs.attandance.common.core.User;
import pgs.attandance.common.repository.RoleRepository;
import pgs.attandance.common.repository.UserRepository;
import pgs.attandance.common.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/user/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public UserResponse getAll() {
        List<User> users = userRepository.findAll();
        return new UserResponse(users.stream()
                .map(obj -> userService.convertToDTO(obj))
                .collect(Collectors.toList()));
    }


    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public UserDTO create(@Valid @RequestBody UserCreateApi userCreateApi) {
        return userService.create(userCreateApi);
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "update{id}", method = RequestMethod.PUT)
    public UserDTO update(@PathVariable Long id ,
                          @Valid @RequestBody UserUpdateApi userUpdateApi) {
        return userService.update(id, userUpdateApi);
    }


    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public UserDTO get(@PathVariable Long id) {
        User user = userRepository.findById(id);
        Preconditions.checkNotNull(user,"Podaj poprawne id");
        UserDTO userDTO = userService.convertToDTO(user);
        return userDTO;
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Long id) {
        List<Role> roles = roleRepository.findAllByUserId(id);

        roles.forEach(role -> roleRepository.delete(role));
        userRepository.delete(id);
        return "User deleted" ;
    }


}