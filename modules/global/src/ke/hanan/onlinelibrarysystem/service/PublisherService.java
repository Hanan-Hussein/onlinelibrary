package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.entity.Publisher;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface PublisherService {
    String NAME = "onlinelibrarysystem_PublisherService";
    ResponseWrapper createPublisher(Publisher publisher);
}