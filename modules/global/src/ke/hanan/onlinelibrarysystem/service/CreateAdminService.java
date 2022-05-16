package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.entity.Admin;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface CreateAdminService {
    String NAME = "onlinelibrarysystem_CreateAdminService";

    ResponseWrapper createAdmin(Admin admin);
}