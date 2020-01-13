insert into om_user (user_name, password, mobile, status, remark, create_time) values ('super_admin', 'e10adc3949ba59abbe56e057f20f883e', '10000000000', 1, '超级管理员', now());
insert into om_user (user_name, password, mobile, status, remark, create_time) values ('middle_admin', 'e10adc3949ba59abbe56e057f20f883e', '20000000000', 1, '中级管理员', now());
insert into om_user (user_name, password, mobile, status, remark, create_time) values ('common_admin', 'e10adc3949ba59abbe56e057f20f883e', '30000000000', 1, '普通管理员', now());


insert into om_role (role_code, role_name, remark, type, create_time) values ('super_admin_role', '超级管理员权限', '超级管理员权限', 1, now());
insert into om_role (role_code, role_name, remark, type, create_time) values ('middle_admin_role', '中级管理员权限', '中级管理员权限', 1, now());
insert into om_role (role_code, role_name, remark, type, create_time) values ('common_admin_role', '普通管理员权限', '普通管理员权限', 1, now());


insert into om_permis (parent_permis_id, permis_name, menu_permis_flag, permis_code, remark, permis_order, create_time) values (0, '人事部', 1, 'personnel_department', '', 1, now());
insert into om_permis (parent_permis_id, permis_name, menu_permis_flag, permis_code, remark, permis_order, create_time) values (0, '财务部', 1, 'accounting_department', '', 2, now());
insert into om_permis (parent_permis_id, permis_name, menu_permis_flag, permis_code, remark, permis_order, create_time) values (0, '行政部', 1, 'administrative_department', '', 3, now());
insert into om_permis (parent_permis_id, permis_name, menu_permis_flag, permis_code, remark, permis_order, create_time) values (0, '运维部', 1, 'operation_department', '', 4, now());
insert into om_permis (parent_permis_id, permis_name, menu_permis_flag, permis_code, remark, permis_order, create_time) values (0, '测试部', 1, 'test_department', '', 5, now());
insert into om_permis (parent_permis_id, permis_name, menu_permis_flag, permis_code, remark, permis_order, create_time) values (0, '市场部', 1, 'marketing_department', '', 6, now());


--超级管理员拥有所有的权限
insert into role_permis (role_id, permis_id, create_time) values (1, 1, now());
insert into role_permis (role_id, permis_id, create_time) values (1, 2, now());
insert into role_permis (role_id, permis_id, create_time) values (1, 3, now());
insert into role_permis (role_id, permis_id, create_time) values (1, 4, now());
insert into role_permis (role_id, permis_id, create_time) values (1, 5, now());
insert into role_permis (role_id, permis_id, create_time) values (1, 6, now());

--中级管理员拥有的权限
insert into role_permis (role_id, permis_id, create_time) values (2, 3, now());
insert into role_permis (role_id, permis_id, create_time) values (2, 4, now());
insert into role_permis (role_id, permis_id, create_time) values (2, 5, now());
insert into role_permis (role_id, permis_id, create_time) values (2, 6, now());

--普通管理员拥有的权限
insert into role_permis (role_id, permis_id, create_time) values (3, 4, now());
insert into role_permis (role_id, permis_id, create_time) values (3, 5, now());


--给用户分配角色
insert into user_role (role_id, user_id, create_time) values (1, 1, now());
insert into user_role (role_id, user_id, create_time) values (2, 2, now());
insert into user_role (role_id, user_id, create_time) values (3, 3, now());


insert into om_menu (parent_id, menu_text, menu_url, permis_id, enable_flag, menu_order, create_time) values (0, '人事部', 'menu/personnel_department', 1, 1, 1, now());
insert into om_menu (parent_id, menu_text, menu_url, permis_id, enable_flag, menu_order, create_time) values (0, '财务部', 'menu/accounting_department', 2, 1, 1, now());
insert into om_menu (parent_id, menu_text, menu_url, permis_id, enable_flag, menu_order, create_time) values (0, '行政部', 'menu/administrative_department', 3, 1, 1, now());
insert into om_menu (parent_id, menu_text, menu_url, permis_id, enable_flag, menu_order, create_time) values (0, '运维部', 'menu/operation_department', 4, 1, 1, now());
insert into om_menu (parent_id, menu_text, menu_url, permis_id, enable_flag, menu_order, create_time) values (0, '测试部', 'menu/test_department', 5, 1, 1, now());
insert into om_menu (parent_id, menu_text, menu_url, permis_id, enable_flag, menu_order, create_time) values (0, '市场部', 'menu/marketing_department', 6, 1, 1, now());

