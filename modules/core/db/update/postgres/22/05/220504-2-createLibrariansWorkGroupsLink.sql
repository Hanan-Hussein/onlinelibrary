alter table ONLINELIBRARYSYSTEM_LIBRARIANS_WORK_GROUPS_LINK add constraint FK_LIBWORGRO_ON_LIBRARIANS foreign key (LIBRARIANS_ID) references SEC_USER(ID);
alter table ONLINELIBRARYSYSTEM_LIBRARIANS_WORK_GROUPS_LINK add constraint FK_LIBWORGRO_ON_WORK_GROUPS foreign key (WORK_GROUPS_ID) references ONLINELIBRARYSYSTEM_WORK_GROUPS(ID);