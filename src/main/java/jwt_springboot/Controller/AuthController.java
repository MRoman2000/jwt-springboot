package jwt_springboot.Controller;


import jwt_springboot.DTO.AuthRequest;
import jwt_springboot.DTO.AuthResponse;
import jwt_springboot.Model.UserInfo;
import jwt_springboot.Service.JwtService;
import jwt_springboot.Service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            UserInfo user = (UserInfo) authentication.getPrincipal();
            String token = jwtService.generateToken(user);
            return ResponseEntity.ok(new AuthResponse(token)); // Devuelve el token en JSON
        } else {
            throw new UsernameNotFoundException("Invalid user credentials!");
        }
    }

    @PostMapping("register")
    public String register(@RequestBody UserInfo userInfo) {
        return userInfoService.register(userInfo);
    }
}
