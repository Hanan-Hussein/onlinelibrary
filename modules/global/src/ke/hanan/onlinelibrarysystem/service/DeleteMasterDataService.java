package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.entity.Publisher;
import ke.hanan.onlinelibrarysystem.entity.Subject;
import ke.hanan.onlinelibrarysystem.wrappers.ApproveWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface DeleteMasterDataService {
    String NAME = "onlinelibrarysystem_DeleteMasterDataService";

    ResponseWrapper DeleteSubject(ApproveWrapper disable);
    ResponseWrapper DeletePublisher(ApproveWrapper disable);
}