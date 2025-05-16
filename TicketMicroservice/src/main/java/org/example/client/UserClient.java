package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.example.dto.UserDTO;  // Create a simple DTO class

@FeignClient(name = "user-microservice", url = "http://localhost:8081")
public interface UserClient {
    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

    @GetMapping("/users/exists/{userId}")
    Boolean doesUserExist(@PathVariable("userId") Long userId);
}

