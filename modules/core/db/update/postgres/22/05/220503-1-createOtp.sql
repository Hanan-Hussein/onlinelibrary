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
    REDEEMED boolean,
    EXPIRY_DATE date,
    OTP_ATTEMPTS integer,
    ADMIN_ID uuid,
    --
    primary key (ID)
);