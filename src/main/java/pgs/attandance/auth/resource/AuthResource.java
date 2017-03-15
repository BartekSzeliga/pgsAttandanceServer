package pgs.attandance.auth.resource;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
        User user = userRepository.findByUsername(login.userName);
        UserDTO userDTO = userService.convertToDTO(user);

        if (login.userName == null || user == null) {
            throw new ServletException("Invalid login ");
        }
        Boolean checkpw = BCrypt.checkpw(login.password, user.getPassword());
        if (login.password == null || !checkpw) {
            throw new ServletException("Invalid password");
        }
        return new LoginResponse(Jwts.builder().setSubject(userDTO.getUserName())
                .claim("roles", userDTO.getRoles())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
    }

    private static class UserLogin {
        public String userName;
        public String password;
    }

    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}
