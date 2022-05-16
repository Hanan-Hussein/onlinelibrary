create table ONLINELIBRARYSYSTEM_BOOK_REQUEST (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    EMAIL varchar(255),
    BOOK_NAME varchar(255),
    AUTHOR varchar(255),
    --
    primary key (ID)
);