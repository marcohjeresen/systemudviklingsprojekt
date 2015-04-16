drop database if exists annetteprojekt; 
create database annetteprojekt; 

use annetteprojekt; 

drop table if exists calendar; 
create table calendar
(
c_date 				datetime not null,
c_customer_number	char(8) not null, -- foreign key
c_massage_id 		int(10), -- foreign key
c_bbq_id			int(10),
primary key (c_date)
);

drop table if exists massage; 
create table massage
(
m_id 				int not null, 
m_comment 			text,
m_startTime 		varchar(5), 
m_type_id 			int(1), -- foreign key
primary key(m_id)
); 

drop table if exists massagetype;
create table massagetype
(
mt_id 				int(1) not null, 
mt_price 			int(3), 
mt_type 			varchar(20),
mt_duration 		int(4),
primary key (mt_id)
);

drop table if exists customer;
create table customer
(
cus_phone			char(8) not null,
cus_name 			varchar(50) not null,
cus_address			varchar(100),
cus_email			varchar(50),
primary key(cus_phone)
);

drop table if exists barbecue;
create table barbecue
(
b_id				int not null,
b_settings			int,
b_address			varchar(50),
b_foodReady			varchar(5),
primary key(b_id)
);

drop table if exists grillToBBQ;
create table grillToBBQ
(
g_id				int not null,
b_id				int not null,
primary key(g_id, b_id)
);

drop table if exists grill;
create table grill
(
g_id				int not null,
g_type				varchar(50),
g_coalPrice			int,
c_category_id		int,
primary key(g_id)
);

drop table if exists meatToBBQ;
create table meatToBBQ
(
m_id				int not null,
b_id				int not null,
mtb_kilo			int not null,
primary key(m_id, b_id)
);

drop table if exists meat;
create table meat
(
m_id				int not null,
m_type				varchar(50),
m_pricePrKilo		int,
c_category_id		int,
primary key(m_id)
);

drop table if exists saladToBBQ; 
create table saladToBBQ
(
b_id				int not null,
s_id				int not null,
primary key (b_id, s_id)
);

drop table if exists salad;
create table salad
(
s_id				int not null,
s_type				varchar(50),
s_pricePerHead		int,
c_category_id		int,
primary key (s_id)
);


drop table if exists vegetableToSalad;
create table vegetableToSalad
(
s_id				int not null,
v_id				int not null,
primary key (s_id, v_id)
);

drop table if exists vegetable;
create table vegetable
(
v_id				int not null,
v_type				varchar(50),
v_pricePerHead		int,
primary key (v_id)
);

drop table if exists accompanimentToBBQ;
create table accompanimentToBBQ
(
a_id				int not null,
b_id				int not null,
primary key(a_id, b_id)
);

drop table if exists accompaniment;
create table accompaniment
(
a_id				int not null,
a_type				varchar(50),
a_pricePerHead		int,
c_category_id		int,
primary key(a_id)
);

drop table if exists category;
create table category
(
c_id				int not null,
c_name				varchar(50),
primary key(c_id)
);

drop table if exists errorMsgs;
create table errorMsgs
(
errorDate	datetime,
message		text,
primary key(errorDate)
);