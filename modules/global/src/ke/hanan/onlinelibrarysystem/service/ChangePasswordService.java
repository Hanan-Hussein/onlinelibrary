package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.wrappers.ChangePasswordWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface ChangePasswordService {
    String NAME = "onlinelibrarysystem_ChangePasswordService";
    ResponseWrapper ChangePassword(ChangePasswordWrapper changePasswordWrapper);

}