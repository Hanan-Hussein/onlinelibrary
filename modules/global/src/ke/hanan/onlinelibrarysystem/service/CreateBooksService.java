package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.entity.Book;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface CreateBooksService {
    String NAME = "onlinelibrarysystem_CreateBooksService";
    ResponseWrapper createBooks (Book book);

}