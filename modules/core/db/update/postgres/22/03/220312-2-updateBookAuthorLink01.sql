alter table ONLINELIBRARYSYSTEM_BOOK_AUTHOR_LINK add constraint FK_ONLINELIBRARYSYSTEM_BOOK_AUTHOR_LINK_ON_AUTHOR foreign key (AUTHOR_ID) references ONLINELIBRARYSYSTEM_AUTHOR(ID);