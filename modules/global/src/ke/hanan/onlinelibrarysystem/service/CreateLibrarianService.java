package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.entity.Admin;
import ke.hanan.onlinelibrarysystem.entity.Librarians;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface CreateLibrarianService {
    String NAME = "onlinelibrarysystem_CreateLibrarianService";
    ResponseWrapper createLibrarian(Librarians librarians);

}