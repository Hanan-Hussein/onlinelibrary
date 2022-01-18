create table ONLINELIBRARYSYSTEM_BOOK_AUTHOR_LINK (
    AUTHOR_ID uuid,
    BOOK_ID uuid,
    primary key (AUTHOR_ID, BOOK_ID)
);
