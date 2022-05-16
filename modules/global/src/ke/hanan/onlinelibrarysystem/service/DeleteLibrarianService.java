package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.wrappers.ApproveWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface DeleteLibrarianService {
    String NAME = "onlinelibrarysystem_DeleteLibrarianService";
    ResponseWrapper DeleteLibrarian(ApproveWrapper disable);

}