package pgs.attandance.auth.resource;


import com.google.common.base.Preconditions;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiModelProperty;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pgs.attandance.common.DTO.UserDTO;
import pgs.attandance.common.core.User;
import pgs.attandance.common.repository.UserRepository;
import pgs.attandance.common.service.UserService;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class AuthResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserLogin login)
            throws ServletException {
        User user = userRepository.findByEmail(login.email);
        Preconditions.checkNotNull(user, "Nie poprawny email");
        UserDTO userDTO = userService.convertToDTO(user);

        Boolean checkpw = BCrypt.checkpw(login.password, user.getPassword());
        Preconditions.checkArgument(checkpw, "Nie poprawne hasło");

        Date currentDate = new Date();
        long currentDateTime = currentDate.getTime();
        Date expiration;
        if (login.rememberMe) {
            expiration = new Date(currentDateTime + 1000 * 60 * 30);
        } else {
            expiration = new Date(currentDateTime + 1000 * 60 * 60 * 24 * 14);
        }

        return new LoginResponse(Jwts.builder().setSubject(userDTO.getEmail())
                .claim("roles", userDTO.getRoles())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact(),
                userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getRoles());
    }

    private static class UserLogin {
        @ApiModelProperty(value = "email", required = true)
        public String email;

        @ApiModelProperty(value = "pasword", required = true)
        public String password;

        @ApiModelProperty(value = "rememberMe", required = true)
        public Boolean rememberMe;
    }

    private static class LoginResponse {
        public String token;
        public Long id;
        public String firstName;
        public String lastName;
        public List<String> roles;

        public LoginResponse(String token, Long id, String firstName, String lastName, List<String> roles) {
            this.token = token;
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.roles = roles;
        }
    }
}
