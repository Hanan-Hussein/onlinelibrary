alter table ONLINELIBRARYSYSTEM_OTP add constraint FK_ONLINELIBRARYSYSTEM_OTP_ON_LIBRARIAN foreign key (LIBRARIAN_ID) references SEC_USER(ID);
create index IDX_ONLINELIBRARYSYSTEM_OTP_ON_LIBRARIAN on ONLINELIBRARYSYSTEM_OTP (LIBRARIAN_ID);
