CREATE TABLE user
(
  user_id bigint(20) not null auto_increment comment '�û�id',
  name varchar(50) not null comment '�û�����',
  gender tinyint not null comment '�û��Ա�',
  age smallint not null comment '�û�����',
  telphone varchar(20) not null comment '�û��ֻ���',
  register_mode varchar(20) not null comment 'ע�᷽ʽ',
  primary key (user_id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='�û���Ϣ��';

CREATE TABLE user_passwd
(
  user_id bigint(20) not null comment '�û�id',
  name varchar(50) not null comment '�û�����',
  telphone varchar(20) not null comment '�û��ֻ���',
  encrpt_passwd varchar(50) not null comment '�û�����',
  primary key (user_id)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='�û������';