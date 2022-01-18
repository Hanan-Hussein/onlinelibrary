create table ONLINELIBRARYSYSTEM_JOURNAL_ARTICLE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TITLE varchar(255),
    VOLUME varchar(255),
    ISSUE varchar(255),
    PAGES varchar(255),
    DATE_ date,
    SERIES varchar(255),
    DOI varchar(255),
    URL varchar(255),
    DOWNLOADS varchar(255),
    SUBJECT_ID uuid,
    --
    primary key (ID)
);