package org.dnyanyog.service;

import java.util.List;
import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.encryption.EncryptionService;
import org.dnyanyog.entity.Users;
import org.dnyanyog.enums.ErrorCode;
import org.dnyanyog.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements LoginService {

  @Autowired UsersRepository userRepo;

  @Autowired EncryptionService encryptionService;

  @Override
  public LoginResponse validateUser(LoginRequest loginRequest) {
    LoginResponse response = new LoginResponse();

    try {
      List<Users> receivedData = userRepo.findByMobileNumber(loginRequest.getMobileNumber());

      if (receivedData.size() == 1) {
        Users userData = receivedData.get(0);

        if (!Users.ACTIVE.equals(userData.getStatus())) {
          response.setStatus(ErrorCode.USER_INACTIVE.getCode());
          response.setMessage(ErrorCode.USER_INACTIVE.getMessage());
          return response;
        }

        String encryptedPassword = userData.getPassword();

        String encryptedRequestPassword = encryptionService.encrypt(loginRequest.getPassword());

        if (encryptedRequestPassword.equals(encryptedPassword)) {
          response.setStatus(ErrorCode.VALIDATION_SUCCESS.getCode());
          response.setMessage(ErrorCode.VALIDATION_SUCCESS.getMessage());
        } else {
          response.setStatus(ErrorCode.VALIDATION_FAIL.getCode());
          response.setMessage(ErrorCode.VALIDATION_FAIL.getMessage());
        }
      } else {
        response.setStatus(ErrorCode.VALIDATION_MOBILENO.getCode());
        response.setMessage(ErrorCode.VALIDATION_MOBILENO.getMessage());
      }
    } catch (Exception e) {

      e.printStackTrace();
      response.setStatus(ErrorCode.LOGIN_ERROR.getCode());
      response.setMessage(ErrorCode.LOGIN_ERROR.getMessage());
    }

    return response;
  }
}
