create table ONLINELIBRARYSYSTEM_SUBJECT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SUBJECT_NAME varchar(255),
    DESCRIPTION varchar(255),
    --
    primary key (ID)
);