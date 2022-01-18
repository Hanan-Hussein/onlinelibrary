package ke.hanan.onlinelibrarysystem.service;

import ke.hanan.onlinelibrarysystem.entity.Subject;
import ke.hanan.onlinelibrarysystem.wrappers.ResponseWrapper;

public interface SubjectService {
    String NAME = "onlinelibrarysystem_SubjectService";

    ResponseWrapper createSubject (Subject subject);
}