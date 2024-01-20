INSERT INTO sys_user (id, create_user, update_user,  name, workspaces, status)
    VALUES (0, 0, 0, 'admin', '{"admin":{"types":["admin","user"],"lock":false}}', 1);
INSERT INTO sys_user_account (id, create_user, update_user, user_id, type, name, password)
    VALUES (0, 0, 0, 0, 'name', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
INSERT INTO sys_workspace (id, create_user, update_user, type, code, name, icon, status)
    VALUES (0, 0, 0, 'pc', 'admin', '系统管理', 'admin', 1);
INSERT INTO sys_workspace (id, create_user, update_user, type, code, name, icon, status)
    VALUES (1, 0, 0, 'pc', 'home', '首页', 'home', 1);
