//package com.example.sport2.dto;
//
//import com.example.sport2.enums.Role;
//
//
//public class UserDTO {
//
//    private Long id;
//    private String username;
//    private String email;
//    private Role role;
//    private boolean active;
//
//    public UserDTO() {}
//
//    public UserDTO(Long id, String username, String email, Role role, boolean active) {
//        this.id = id;
//        this.username = username;
//        this.email = email;
//        this.role = role;
//        this.active = active;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//}
package com.example.sport2.dto;

import com.example.sport2.enums.Role;

public class UserDTO {

    private String username;
    private String email;
    private String password;
    private Role role;

    public UserDTO() {}

    public UserDTO(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
