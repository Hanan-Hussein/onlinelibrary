package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.wrappers.ApproveWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface DeleteBooksService {
    String NAME = "onlinelibrarysystem_DeleteBooksService";

    ResponseWrapper DeleteBooks(ApproveWrapper disable);

}