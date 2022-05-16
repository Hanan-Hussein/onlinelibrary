package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.wrappers.ApproveWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface DeleteWorkGroupService {
    String NAME = "onlinelibrarysystem_DeleteWorkGroupService";
    ResponseWrapper WorkGroups (ApproveWrapper disable);

}