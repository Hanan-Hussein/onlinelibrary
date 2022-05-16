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
);