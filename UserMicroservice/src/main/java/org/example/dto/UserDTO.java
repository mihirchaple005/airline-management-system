package org.example.dto;  // Add this package declaration

import jakarta.validation.constraints.*;

public class UserDTO {
    @NotBlank private String name;
    @Email @NotBlank private String email;
    @Pattern(regexp = "^[+]?[0-9]{10,13}$") private String phone;
    @NotBlank private String password;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}