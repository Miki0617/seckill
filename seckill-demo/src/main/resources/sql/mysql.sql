CREATE TABLE user
(
  user_id bigint(20) not null auto_increment comment '用户id',
  name varchar(50) not null comment '用户名称',
  gender tinyint not null comment '用户性别',
  age smallint not null comment '用户年龄',
  telphone varchar(20) not null comment '用户手机号',
  register_mode varchar(20) not null comment '注册方式',
  primary key (user_id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE user_passwd
(
  user_id bigint(20) not null comment '用户id',
  name varchar(50) not null comment '用户名称',
  telphone varchar(20) not null comment '用户手机号',
  encrpt_passwd varchar(50) not null comment '用户密码',
  primary key (user_id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='用户密码表';