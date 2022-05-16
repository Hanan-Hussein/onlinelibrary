package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.entity.Author;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface CreateAuthorService {
    String NAME = "onlinelibrarysystem_CreateAuthorService";
    ResponseWrapper createAuthor(Author author);
}