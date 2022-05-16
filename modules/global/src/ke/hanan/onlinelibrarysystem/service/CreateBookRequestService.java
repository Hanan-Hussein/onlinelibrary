package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.entity.BookRequest;
import ke.hanan.onlinelibrarysystem.entity.Students;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface CreateBookRequestService {
    String NAME = "onlinelibrarysystem_CreateBookRequestService";

    ResponseWrapper createBookRequest (BookRequest request);

}