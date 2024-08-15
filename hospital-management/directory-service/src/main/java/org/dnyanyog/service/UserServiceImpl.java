package org.dnyanyog.service;

import java.util.Optional;
import org.dnyanyog.dto.AddUserRequest;
import org.dnyanyog.dto.UpdateUserRequest;
import org.dnyanyog.dto.UserData;
import org.dnyanyog.dto.UserResponse;
import org.dnyanyog.encryption.EncryptionService;
import org.dnyanyog.entity.Users;
import org.dnyanyog.enums.ErrorCode;
import org.dnyanyog.repo.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired UsersRepository userRepo;

  @Autowired UserResponse userResponse;

  @Autowired Users userTable;

  @Autowired EncryptionService encryptionService;

  @Override
  public UserResponse addUser(AddUserRequest addRequest) {
    UserResponse userResponse = new UserResponse();
    UserData userData = new UserData();

    try {
      userTable =
          Users.getInstance()
              .setUserName(addRequest.getUserName())
              .setEmail(addRequest.getEmail())
              .setMobileNumber(addRequest.getMobileNumber())
              .setPassword(encryptionService.encrypt(addRequest.getPassword()))
              .setStatus(Users.ACTIVE)
              .setUserRole(addRequest.getRole())
              .build();

      System.out.println("Before saving userTable: " + userTable);

      userTable = userRepo.save(userTable);

      System.out.println("After saving userTable: " + userTable);

      userResponse.setStatus(ErrorCode.USER_ADD_SUCCESS.getCode());
      userResponse.setMessage(ErrorCode.USER_ADD_SUCCESS.getMessage());

      userData.setUserId(userTable.getUserId());
      userData.setUserName(userTable.getUserName());
      userData.setMobileNumber(userTable.getMobileNumber());
      userData.setEmail(userTable.getEmail());
      userData.setPassword(userTable.getPassword());
      userData.setRole(userTable.getUserRole());
      userData.setStatus(userTable.getStatus());

      userResponse.setUserData(userData);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return userResponse;
  }

  @Override
  public UserResponse updateUser(UpdateUserRequest updateRequest) {
    UserResponse userResponse = new UserResponse(); // Initialize UserResponse object
    Optional<Users> optionalUser = userRepo.findById(updateRequest.getUserId());
    if (optionalUser.isPresent()) {
      try {
        Users user = optionalUser.get();
        if (updateRequest.getUserName() != null) {
          user.setUserName(updateRequest.getUserName());
        }

        if (updateRequest.getEmail() != null) {
          user.setEmail(updateRequest.getEmail());
        }

        if (updateRequest.getMobileNumber() != null) {
          user.setMobileNumber(updateRequest.getMobileNumber());
        }

        if (updateRequest.getPassword() != null) {
          user.setPassword(encryptionService.encrypt(updateRequest.getPassword()));
        }

        if (updateRequest.getRole() != null) {
          user.setUserRole(updateRequest.getRole());
        }

        if (updateRequest.getStatus() != null) {
          user.setStatus(updateRequest.getStatus());
        }

        user = userRepo.save(user);

        userResponse.setStatus(ErrorCode.USER_UPDATE_SUCCESS.getCode());
        userResponse.setMessage(ErrorCode.USER_UPDATE_SUCCESS.getMessage());

        UserData userData = new UserData();
        userData.setUserId(user.getUserId());
        userData.setUserName(user.getUserName());
        userData.setMobileNumber(user.getMobileNumber());
        userData.setEmail(user.getEmail());
        userData.setPassword(user.getPassword());
        userData.setRole(user.getUserRole());
        userData.setStatus(user.getStatus());

        userResponse.setUserData(userData);

      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      userResponse.setStatus(ErrorCode.USER_NOT_FOUND.getCode());
      userResponse.setMessage(ErrorCode.USER_NOT_FOUND.getMessage());
    }
    return userResponse;
  }

  @Override
  public UserResponse deleteUser(String userId) {
    Optional<Users> optionalUser = userRepo.findById(userId);

    if (optionalUser.isPresent()) {
      Users user = optionalUser.get();
      user.setStatus(Users.INACTIVE);

      userRepo.save(user);

      UserResponse userResponse = new UserResponse();
      userResponse.setStatus(ErrorCode.USER_DELETE_SUCCESS.getCode());
      userResponse.setMessage(ErrorCode.USER_DELETE_SUCCESS.getMessage());

      return (userResponse);
    } else {
      UserResponse userResponse = new UserResponse();
      userResponse.setStatus(ErrorCode.USER_NOT_FOUND.getCode());
      userResponse.setMessage(ErrorCode.USER_NOT_FOUND.getMessage());

      return (userResponse);
    }
  }

  @Override
  public UserResponse getUserById(String userId) {
    Optional<Users> optionalUser = userRepo.findById(userId);

    if (optionalUser != null && optionalUser.isPresent()) {
      Users user = optionalUser.get();

      UserResponse userResponse = new UserResponse();
      userResponse.setStatus(ErrorCode.USER_FOUND.getCode());
      userResponse.setMessage(ErrorCode.USER_FOUND.getMessage());

      UserData userData = new UserData();
      userData.setUserId(user.getUserId());
      userData.setUserName(user.getUserName());
      userData.setEmail(user.getEmail());
      userData.setPassword(user.getPassword());
      userData.setMobileNumber(user.getMobileNumber());
      userData.setRole(user.getUserRole());
      userData.setStatus(user.getStatus());

      userResponse.setUserData(userData);

      return userResponse;
    } else {
      UserResponse userResponse = new UserResponse();
      userResponse.setStatus(ErrorCode.USER_NOT_FOUND.getCode());
      userResponse.setMessage(ErrorCode.USER_NOT_FOUND.getMessage());

      return userResponse;
    }
  }
}
