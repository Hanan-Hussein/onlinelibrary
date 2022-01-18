create table ONLINELIBRARYSYSTEM_BOOK (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ISBN varchar(255),
    SUBJECT_ID uuid,
    AUTHORS_ID uuid,
    PUBLISHER_ID uuid,
    DOWNLOADS varchar(255),
    BOOKFILE_ID uuid,
    BOOK_TITLE varchar(255),
    YEAR_PUBLISHED date,
    EDITION_NUMBER integer,
    BOOK_SUMMARY text,
    RECIEVE_DATE date,
    --
    primary key (ID)
);