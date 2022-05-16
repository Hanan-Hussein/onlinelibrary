package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.wrappers.ApproveWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface DisableUserService {
    String NAME = "onlinelibrarysystem_DisableUserService";
    ResponseWrapper disableUser(ApproveWrapper disable);

}