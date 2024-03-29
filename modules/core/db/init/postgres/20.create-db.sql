-- begin ONLINELIBRARYSYSTEM_JOURNAL_ARTICLE
alter table ONLINELIBRARYSYSTEM_JOURNAL_ARTICLE add constraint FK_ONLINELIBRARYSYSTEM_JOURNAL_ARTICLE_ON_SUBJECT foreign key (SUBJECT_ID) references ONLINELIBRARYSYSTEM_SUBJECT(ID)^
create index IDX_ONLINELIBRARYSYSTEM_JOURNAL_ARTICLE_ON_SUBJECT on ONLINELIBRARYSYSTEM_JOURNAL_ARTICLE (SUBJECT_ID)^
-- end ONLINELIBRARYSYSTEM_JOURNAL_ARTICLE
-- begin ONLINELIBRARYSYSTEM_AUTHOR
alter table ONLINELIBRARYSYSTEM_AUTHOR add constraint FK_ONLINELIBRARYSYSTEM_AUTHOR_ON_JOURNAL_ARTICLE foreign key (JOURNAL_ARTICLE_ID) references ONLINELIBRARYSYSTEM_JOURNAL_ARTICLE(ID)^
create index IDX_ONLINELIBRARYSYSTEM_AUTHOR_ON_JOURNAL_ARTICLE on ONLINELIBRARYSYSTEM_AUTHOR (JOURNAL_ARTICLE_ID)^
-- end ONLINELIBRARYSYSTEM_AUTHOR
-- begin ONLINELIBRARYSYSTEM_BOOK
alter table ONLINELIBRARYSYSTEM_BOOK add constraint FK_ONLINELIBRARYSYSTEM_BOOK_ON_SUBJECT foreign key (SUBJECT_ID) references ONLINELIBRARYSYSTEM_SUBJECT(ID)^
alter table ONLINELIBRARYSYSTEM_BOOK add constraint FK_ONLINELIBRARYSYSTEM_BOOK_ON_AUTHORS foreign key (AUTHORS_ID) references ONLINELIBRARYSYSTEM_AUTHOR(ID)^
alter table ONLINELIBRARYSYSTEM_BOOK add constraint FK_ONLINELIBRARYSYSTEM_BOOK_ON_PUBLISHER foreign key (PUBLISHER_ID) references ONLINELIBRARYSYSTEM_PUBLISHER(ID)^
alter table ONLINELIBRARYSYSTEM_BOOK add constraint FK_ONLINELIBRARYSYSTEM_BOOK_ON_BOOKFILE foreign key (BOOKFILE_ID) references SYS_FILE(ID)^
alter table ONLINELIBRARYSYSTEM_BOOK add constraint FK_ONLINELIBRARYSYSTEM_BOOK_ON_BOOK_COVER foreign key (BOOK_COVER_ID) references SYS_FILE(ID)^
create index IDX_ONLINELIBRARYSYSTEM_BOOK_ON_SUBJECT on ONLINELIBRARYSYSTEM_BOOK (SUBJECT_ID)^
create index IDX_ONLINELIBRARYSYSTEM_BOOK_ON_AUTHORS on ONLINELIBRARYSYSTEM_BOOK (AUTHORS_ID)^
create index IDX_ONLINELIBRARYSYSTEM_BOOK_ON_PUBLISHER on ONLINELIBRARYSYSTEM_BOOK (PUBLISHER_ID)^
create index IDX_ONLINELIBRARYSYSTEM_BOOK_ON_BOOKFILE on ONLINELIBRARYSYSTEM_BOOK (BOOKFILE_ID)^
create index IDX_ONLINELIBRARYSYSTEM_BOOK_ON_BOOK_COVER on ONLINELIBRARYSYSTEM_BOOK (BOOK_COVER_ID)^
-- end ONLINELIBRARYSYSTEM_BOOK
-- begin ONLINELIBRARYSYSTEM_BOOK_AUTHOR_LINK
alter table ONLINELIBRARYSYSTEM_BOOK_AUTHOR_LINK add constraint FK_BOOAUT_ON_AUTHOR foreign key (AUTHOR_ID) references ONLINELIBRARYSYSTEM_AUTHOR(ID)^
alter table ONLINELIBRARYSYSTEM_BOOK_AUTHOR_LINK add constraint FK_BOOAUT_ON_BOOK foreign key (BOOK_ID) references ONLINELIBRARYSYSTEM_BOOK(ID)^
-- end ONLINELIBRARYSYSTEM_BOOK_AUTHOR_LINK
-- begin ONLINELIBRARYSYSTEM_OTP
alter table ONLINELIBRARYSYSTEM_OTP add constraint FK_ONLINELIBRARYSYSTEM_OTP_ON_LIBRARIAN foreign key (LIBRARIAN_ID) references SEC_USER(ID)^
alter table ONLINELIBRARYSYSTEM_OTP add constraint FK_ONLINELIBRARYSYSTEM_OTP_ON_ADMIN foreign key (ADMIN_ID) references SEC_USER(ID)^
create index IDX_ONLINELIBRARYSYSTEM_OTP_ON_LIBRARIAN on ONLINELIBRARYSYSTEM_OTP (LIBRARIAN_ID)^
create index IDX_ONLINELIBRARYSYSTEM_OTP_ON_ADMIN on ONLINELIBRARYSYSTEM_OTP (ADMIN_ID)^
-- end ONLINELIBRARYSYSTEM_OTP
-- begin ONLINELIBRARYSYSTEM_WORK_GROUP_ROLES
alter table ONLINELIBRARYSYSTEM_WORK_GROUP_ROLES add constraint FK_ONLINELIBRARYSYSTEM_WORK_GROUP_ROLES_ON_WORK_GROUP foreign key (WORK_GROUP_ID) references ONLINELIBRARYSYSTEM_WORK_GROUPS(ID)^
create index IDX_ONLINELIBRARYSYSTEM_WORK_GROUP_ROLES_ON_WORK_GROUP on ONLINELIBRARYSYSTEM_WORK_GROUP_ROLES (WORK_GROUP_ID)^
-- end ONLINELIBRARYSYSTEM_WORK_GROUP_ROLES
-- begin ONLINELIBRARYSYSTEM_LIBRARIANS_WORK_GROUPS_LINK
alter table ONLINELIBRARYSYSTEM_LIBRARIANS_WORK_GROUPS_LINK add constraint FK_LIBWORGRO_ON_LIBRARIANS foreign key (LIBRARIANS_ID) references SEC_USER(ID)^
alter table ONLINELIBRARYSYSTEM_LIBRARIANS_WORK_GROUPS_LINK add constraint FK_LIBWORGRO_ON_WORK_GROUPS foreign key (WORK_GROUPS_ID) references ONLINELIBRARYSYSTEM_WORK_GROUPS(ID)^
-- end ONLINELIBRARYSYSTEM_LIBRARIANS_WORK_GROUPS_LINK
-- begin ONLINELIBRARYSYSTEM_WORK_GROUPS_LIBRARIANS_LINK
alter table ONLINELIBRARYSYSTEM_WORK_GROUPS_LIBRARIANS_LINK add constraint FK_WORGROLIB_ON_WORK_GROUPS foreign key (WORK_GROUPS_ID) references ONLINELIBRARYSYSTEM_WORK_GROUPS(ID)^
alter table ONLINELIBRARYSYSTEM_WORK_GROUPS_LIBRARIANS_LINK add constraint FK_WORGROLIB_ON_LIBRARIANS foreign key (LIBRARIANS_ID) references SEC_USER(ID)^
-- end ONLINELIBRARYSYSTEM_WORK_GROUPS_LIBRARIANS_LINK
-- begin ONLINELIBRARYSYSTEM_WORK_GROUPS_ADMIN_LINK
alter table ONLINELIBRARYSYSTEM_WORK_GROUPS_ADMIN_LINK add constraint FK_WORGROADM_ON_ADMIN foreign key (ADMIN_ID) references SEC_USER(ID)^
alter table ONLINELIBRARYSYSTEM_WORK_GROUPS_ADMIN_LINK add constraint FK_WORGROADM_ON_WORK_GROUPS foreign key (WORK_GROUPS_ID) references ONLINELIBRARYSYSTEM_WORK_GROUPS(ID)^
-- end ONLINELIBRARYSYSTEM_WORK_GROUPS_ADMIN_LINK
