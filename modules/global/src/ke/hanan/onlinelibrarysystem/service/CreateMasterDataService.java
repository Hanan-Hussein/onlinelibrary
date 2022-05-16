package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.entity.Publisher;
import ke.hanan.onlinelibrarysystem.entity.Subject;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface CreateMasterDataService {
    String NAME = "onlinelibrarysystem_CreateMasterDataService";

    ResponseWrapper createPublisher(Publisher publisher);
    ResponseWrapper createSubject (Subject subject);
}