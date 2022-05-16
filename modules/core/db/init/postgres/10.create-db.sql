-- begin ONLINELIBRARYSYSTEM_SUBJECT
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
    CODE varchar(255),
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end ONLINELIBRARYSYSTEM_SUBJECT
-- begin ONLINELIBRARYSYSTEM_PUBLISHER
create table ONLINELIBRARYSYSTEM_PUBLISHER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    PUBLISHER_NAME varchar(255),
    CODE varchar(255),
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end ONLINELIBRARYSYSTEM_PUBLISHER
-- begin ONLINELIBRARYSYSTEM_JOURNAL_ARTICLE
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
)^
-- end ONLINELIBRARYSYSTEM_JOURNAL_ARTICLE
-- begin ONLINELIBRARYSYSTEM_AUTHOR
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
)^
-- end ONLINELIBRARYSYSTEM_AUTHOR
-- begin ONLINELIBRARYSYSTEM_BOOK
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
    AUTHOR_NAME varchar(255),
    SUBJECT_ID uuid,
    AUTHORS_ID uuid,
    PUBLISHER_ID uuid,
    DOWNLOADS varchar(255),
    BOOKFILE_ID uuid,
    BOOK_COVER_ID uuid,
    BOOK_TITLE varchar(255),
    YEAR_PUBLISHED date,
    EDITION_NUMBER varchar(255),
    BOOK_SUMMARY text,
    RECIEVE_DATE date,
    --
    primary key (ID)
)^
-- end ONLINELIBRARYSYSTEM_BOOK
-- begin ONLINELIBRARYSYSTEM_BOOK_AUTHOR_LINK
create table ONLINELIBRARYSYSTEM_BOOK_AUTHOR_LINK (
    AUTHOR_ID uuid,
    BOOK_ID uuid,
    primary key (AUTHOR_ID, BOOK_ID)
)^
-- end ONLINELIBRARYSYSTEM_BOOK_AUTHOR_LINK
-- begin SEC_USER
alter table SEC_USER add column PHONE_NUMBER varchar(255) ^
alter table SEC_USER add column NATIONAL_ID varchar(255) ^
alter table SEC_USER add column PHONE_NUMBER varchar(255) ^
alter table SEC_USER add column NATIONAL_ID varchar(255) ^
alter table SEC_USER add column PHONE_NUMBER varchar(255) ^
alter table SEC_USER add column ADDRESS varchar(255) ^
alter table SEC_USER add column SCHOOL varchar(255) ^
alter table SEC_USER add column DTYPE varchar(31) ^
update SEC_USER set DTYPE = 'sec$User' where DTYPE is null ^
-- end SEC_USER
-- begin ONLINELIBRARYSYSTEM_OTP
create table ONLINELIBRARYSYSTEM_OTP (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    OTP varchar(255),
    LIBRARIAN_ID uuid,
    REDEEMED boolean,
    EXPIRY_DATE date,
    OTP_ATTEMPTS integer,
    ADMIN_ID uuid,
    --
    primary key (ID)
)^
-- end ONLINELIBRARYSYSTEM_OTP
-- begin ONLINELIBRARYSYSTEM_WORK_GROUPS
create table ONLINELIBRARYSYSTEM_WORK_GROUPS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end ONLINELIBRARYSYSTEM_WORK_GROUPS
-- begin ONLINELIBRARYSYSTEM_WORK_GROUP_ROLES
create table ONLINELIBRARYSYSTEM_WORK_GROUP_ROLES (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    WORK_GROUP_ID uuid,
    ROLE varchar(255),
    --
    primary key (ID)
)^
-- end ONLINELIBRARYSYSTEM_WORK_GROUP_ROLES
-- begin ONLINELIBRARYSYSTEM_LIBRARIANS_WORK_GROUPS_LINK
create table ONLINELIBRARYSYSTEM_LIBRARIANS_WORK_GROUPS_LINK (
    LIBRARIANS_ID uuid,
    WORK_GROUPS_ID uuid,
    primary key (LIBRARIANS_ID, WORK_GROUPS_ID)
)^
-- end ONLINELIBRARYSYSTEM_LIBRARIANS_WORK_GROUPS_LINK
-- begin ONLINELIBRARYSYSTEM_WORK_GROUPS_LIBRARIANS_LINK
create table ONLINELIBRARYSYSTEM_WORK_GROUPS_LIBRARIANS_LINK (
    WORK_GROUPS_ID uuid,
    LIBRARIANS_ID uuid,
    primary key (WORK_GROUPS_ID, LIBRARIANS_ID)
)^
-- end ONLINELIBRARYSYSTEM_WORK_GROUPS_LIBRARIANS_LINK
-- begin ONLINELIBRARYSYSTEM_WORK_GROUPS_ADMIN_LINK
create table ONLINELIBRARYSYSTEM_WORK_GROUPS_ADMIN_LINK (
    ADMIN_ID uuid,
    WORK_GROUPS_ID uuid,
    primary key (ADMIN_ID, WORK_GROUPS_ID)
)^
-- end ONLINELIBRARYSYSTEM_WORK_GROUPS_ADMIN_LINK
-- begin ONLINELIBRARYSYSTEM_BOOK_REQUEST
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
)^
-- end ONLINELIBRARYSYSTEM_BOOK_REQUEST
