package jwt_springboot.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class UserInfo implements UserDetails {

    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private String roles;  // Almacena los roles como una cadena, por ejemplo: "ROLE_USER,ROLE_ADMIN"
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    // Constructor, getters y setters
    public UserInfo() {
    }

    public UserInfo(String name, String email, String password, String roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    // Implementación de métodos de UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + roles); // Importante para @PreAuthorize
    }

    @Override
    public String getUsername() {
        return email;  // O 'name' si prefieres usar el nombre en lugar del email
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // Lógica de cuenta no expirada
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // Lógica de cuenta no bloqueada
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // Lógica de credenciales no expiradas
    }

    @Override
    public boolean isEnabled() {
        return true;  // Lógica de usuario habilitado
    }
}
