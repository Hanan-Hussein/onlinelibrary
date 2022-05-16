package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.wrappers.ApproveWrapper;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface DeleteStudentService {
    String NAME = "onlinelibrarysystem_DeleteStudentService";
    ResponseWrapper Student(ApproveWrapper disable);

}