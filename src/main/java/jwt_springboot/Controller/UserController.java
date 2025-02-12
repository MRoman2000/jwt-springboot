package jwt_springboot.Controller;

import jwt_springboot.DTO.AuthRequest;
import jwt_springboot.DTO.AuthResponse;
import jwt_springboot.Model.UserInfo;
import jwt_springboot.Service.JwtService;
import jwt_springboot.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, this endpoint is not secure";
    }

    @GetMapping("/userProfile")
    @PreAuthorize("hasRole('USER')")  // Cambiar a hasRole('USER')
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/adminProfile")
    @PreAuthorize("hasRole('ADMIN')")  // Cambiar a hasRole('ADMIN')
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @GetMapping("/users")
    public List<UserInfo> getUsers() {
        return userInfoService.getUsers();
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfo> getUserInfo(@AuthenticationPrincipal UserInfo user) {
        return ResponseEntity.ok(user);
    }

}
