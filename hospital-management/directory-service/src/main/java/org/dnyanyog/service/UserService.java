package org.dnyanyog.service;

import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.UpdateUserRequest;
import org.dnyanyog.dto.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserService {

  public UserResponse addUser(AddUserRequest addRequest);

  public UserResponse updateUser(UpdateUserRequest updateRequest);

  public UserResponse deleteUser(String userId);

  public UserResponse getUserById(@PathVariable String userId);
}
