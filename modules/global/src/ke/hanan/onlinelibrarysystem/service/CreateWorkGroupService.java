package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.entity.WorkGroups;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface CreateWorkGroupService {
    String NAME = "onlinelibrarysystem_CreateWorkGroupService";
    ResponseWrapper createWorkGroup(WorkGroups workGroups);

}