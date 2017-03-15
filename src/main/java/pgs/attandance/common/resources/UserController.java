package pgs.attandance.common.resources;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pgs.attandance.common.DTO.UserDTO;
import pgs.attandance.common.api.UserCreateApi;
import pgs.attandance.common.api.UserResponse;
import pgs.attandance.common.core.Role;
import pgs.attandance.common.core.User;
import pgs.attandance.common.repository.RoleRepository;
import pgs.attandance.common.repository.UserRepository;
import pgs.attandance.common.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "api/getAll", method = RequestMethod.GET)
    public UserResponse getAll() {
        List<User> users = userRepository.findAll();
        return new UserResponse(users.stream()
                .map(obj -> userService.convertToDTO(obj))
                .collect(Collectors.toList()));
    }


    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "api/create", method = RequestMethod.POST)
    public UserDTO create(@Valid @RequestBody UserCreateApi userCreateApi) {
        return userService.create(userCreateApi);
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer", dataType = "string", paramType = "header")
    @ResponseBody
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public UserDTO get(@PathVariable Long id) {
        User user = userRepository.findById(id);
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