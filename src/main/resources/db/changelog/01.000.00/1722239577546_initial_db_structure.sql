-- liquibase formatted sql

--changeset vagapov-vr:add_user-table
-- Пользователи
create table USERS
(
    ID                  uuid                    not null primary key,
    VERSION             smallint default 0      not null,
    USER_GUID           uuid                    not null,
    CREATE_DATE         timestamp(0)            null,
    CHANGE_DATE         timestamp(0)            null,
    DELETED             boolean default false   not null,
    LAST_NAME           varchar(50)             null,
    FIRST_NAME          varchar(50)             null,
    MIDDLE_NAME         varchar(50)             null,
    FULL_NAME           varchar(255)            null,
    BIRTH_DATE          timestamp(0)            null,
    BIRTH_PLACE         varchar(500)            null,
    SEX                 varchar(1)              null,
    CITIZENSHIP_CODE    varchar(3)              null,

    constraint UC_USER_GUID unique (USER_GUID)
);
--rollback drop table USERS

--changeset vagapov-vr:add_user-table_user_guid_index
create index I_USER_GUID on USERS(USER_GUID);
--rollback drop index I_USER_GUID