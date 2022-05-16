create table ONLINELIBRARYSYSTEM_AUTHOR (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    PHONE_NUMBER varchar(255),
    ADDRESS varchar(255),
    JOURNAL_ARTICLE_ID uuid,
    --
    primary key (ID)
);