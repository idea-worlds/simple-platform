-- 系统用户
create table sys_user
(
    id                bigint                                              not null
        constraint sys_user_pk primary key,
    create_time       timestamp with time zone default CURRENT_TIMESTAMP  not null,
    create_user       bigint                                              not null,
    update_time       timestamp with time zone default CURRENT_TIMESTAMP  not null,
    update_user       bigint                                              not null,
    name              text                     default ''::text           not null,
    mobile            text                     default ''::text           not null,
    email             text                     default ''::text           not null,
    sex               text                     default ''::text           not null,
    avatar            bigint,
    workspaces        jsonb                    default '{}'::jsonb        not null,
    orgs              text[]                   default ARRAY []::text[]   not null,
    sort_no           smallint                 default 0                  not null,
    status            smallint                 default 0                  not null
);
comment on table sys_user is '系统用户';
comment on column sys_user.create_time is '创建时间';
comment on column sys_user.create_user is '创建人';
comment on column sys_user.update_time is '修改时间';
comment on column sys_user.update_user is '修改人';
comment on column sys_user.name is '用户名';
comment on column sys_user.mobile is '手机号';
comment on column sys_user.email is '邮箱';
comment on column sys_user.sex is '性别';
comment on column sys_user.avatar is '头像';
comment on column sys_user.workspaces is '工作空间';
comment on column sys_user.orgs is '组织';
comment on column sys_user.sort_no is '序号';
comment on column sys_user.status is '状态';
create index idx_su_sort on sys_user (sort_no desc, status asc);


-- 系统用户账号
create table sys_user_account
(
    id               bigint                                             not null
        constraint sys_user_account_pk primary key,
    create_time      timestamp with time zone default CURRENT_TIMESTAMP not null,
    create_user      bigint                                             not null,
    update_time      timestamp with time zone default CURRENT_TIMESTAMP not null,
    update_user      bigint                                             not null,
    user_id          bigint                                             not null,
    type             text                                               not null,
    name             text                                               not null,
    password         text                                               not null,
    last_login_ip    text,
    last_login_agent text,
    last_login_time  bigint
);
comment on table sys_user_account is '系统用户账号';
comment on column sys_user_account.create_time is '创建时间';
comment on column sys_user_account.create_user is '创建人';
comment on column sys_user_account.update_time is '修改时间';
comment on column sys_user_account.update_user is '修改人';
comment on column sys_user_account.user_id is '用户 id';
comment on column sys_user_account.type is '账号类型';
comment on column sys_user_account.name is '账号名';
comment on column sys_user_account.password is '密码';
comment on column sys_user_account.last_login_ip is '最后登录 ip';
comment on column sys_user_account.last_login_agent is '最后登录浏览器';
comment on column sys_user_account.last_login_time is '最后登录时间';
create index idx_sua_uid on sys_user_account (user_id);
create unique index idx_sua_name on sys_user_account (type, name);


-- 工作空间
create table sys_workspace
(
    id          bigint                                             not null
        constraint sys_workspace_pk primary key,
    create_time timestamp with time zone default CURRENT_TIMESTAMP not null,
    create_user bigint                                             not null,
    update_time timestamp with time zone default CURRENT_TIMESTAMP not null,
    update_user bigint                                             not null,
    code        text                                               not null,
    type        text                                               not null,
    name        text                                               not null,
    icon        text,
    description text,
    ext_flag    smallint                 default 0                 not null,
    ext_url     text,
    ext_info    jsonb,
    sort_no     smallint                 default 0                 not null,
    status      smallint                                           not null
);
comment on table sys_workspace is '工作空间';
comment on column sys_workspace.create_time is '创建时间';
comment on column sys_workspace.create_user is '创建人';
comment on column sys_workspace.update_time is '修改时间';
comment on column sys_workspace.update_user is '修改人';
comment on column sys_workspace.code is '编码';
comment on column sys_workspace.type is '类型';
comment on column sys_workspace.name is '名称';
comment on column sys_workspace.icon is '图标';
comment on column sys_workspace.description is '描述';
comment on column sys_workspace.ext_flag is '是否外部应用';
comment on column sys_workspace.ext_url is '外部链接';
comment on column sys_workspace.ext_info is '外部信息';
comment on column sys_workspace.sort_no is '序号';
comment on column sys_workspace.status is '状态';
create unique index idx_sw_code on sys_workspace (code);
create index idx_sw_sort on sys_workspace (sort_no desc);


-- 数据字典
create table sys_dict
(
    id          bigint                                             not null
        constraint sys_dict_pk primary key,
    create_time timestamp with time zone default CURRENT_TIMESTAMP not null,
    create_user bigint                                             not null,
    update_time timestamp with time zone default CURRENT_TIMESTAMP not null,
    update_user bigint                                             not null,
    code        text                                               not null,
    name        text                                               not null,
    description text,
    items       jsonb                    default '{}'::jsonb       not null,
    sort_no     smallint                 default 0                 not null
);
comment on table sys_dict is '数据字典';
comment on column sys_dict.create_time is '创建时间';
comment on column sys_dict.create_user is '创建人';
comment on column sys_dict.update_time is '修改时间';
comment on column sys_dict.update_user is '修改人';
comment on column sys_dict.code is '编码';
comment on column sys_dict.name is '名称';
comment on column sys_dict.description is '描述';
comment on column sys_dict.items is '字典项';
comment on column sys_dict.sort_no is '序号';
create unique index idx_sd_code on sys_dict (code);
create index idx_sd_sort on sys_dict (sort_no desc);
