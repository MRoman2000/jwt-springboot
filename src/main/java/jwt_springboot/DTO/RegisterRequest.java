package jwt_springboot.DTO;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}