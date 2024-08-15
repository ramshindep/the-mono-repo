package org.dnyanyog.controller;

import jakarta.validation.Valid;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.UpdateUserRequest;
import org.dnyanyog.dto.UserResponse;
import org.dnyanyog.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceController {

  @Autowired UserServiceImpl userService;

  @PostMapping(
      path = "/api/v1/directory/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public UserResponse addUser(@Valid @RequestBody AddUserRequest userRequest) {
    return userService.addUser(userRequest);
  }

  @PostMapping(
      path = "/api/v1/directory/update",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public UserResponse updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {

    return userService.updateUser(updateUserRequest);
  }

  @DeleteMapping(path = "/api/v1/directory/{userId}")
  public UserResponse deleteUser(@PathVariable String userId) {
    return userService.deleteUser(userId);
  }

  @GetMapping(path = "/api/v1/directory/{userId}")
  public UserResponse getUserById(@PathVariable String userId) {

    return userService.getUserById(userId);
  }
}
