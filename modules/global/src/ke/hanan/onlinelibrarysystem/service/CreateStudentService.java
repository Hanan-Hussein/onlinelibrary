package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.entity.Students;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface CreateStudentService {
    String NAME = "onlinelibrarysystem_CreateStudentService";

    ResponseWrapper createStudent (Students students);
}