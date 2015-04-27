use annetteprojekt;

set foreign_key_checks = 0;

alter table calendar
add foreign key(c_massage_id) references massage(m_id) on update cascade on delete cascade,
add foreign key(c_customer_number) references customer(cus_phone) on update cascade on delete cascade,
add foreign key(c_bbq_id) references barbecue(b_id) on update cascade on delete cascade,
engine = innodb;

alter table massage
add foreign key(m_type_id) references massagetype(mt_id) on update cascade,
engine = innodb;

alter table grillToBBQ
add foreign key(g_id) references grill(g_id) on update cascade on delete cascade,
add foreign key(b_id) references barbecue(b_id) on update cascade on delete cascade,
engine = innodb;

alter table meatToBBQ
add foreign key(m_id) references meat(m_id) on update cascade on delete cascade,
add foreign key(b_id) references barbecue(b_id) on update cascade on delete cascade,
engine = innodb;

alter table saladToBBQ
add foreign key(b_id) references barbecue(b_id) on update cascade on delete cascade,
add foreign key(s_id) references salad(s_id) on update cascade on delete cascade,
engine = innodb;

alter table vegetableToSalad
add foreign key(v_id) references vegetable(v_id) on update cascade on delete cascade,
add foreign key(s_id) references salad(s_id) on update cascade on delete cascade,
engine = innodb;

alter table accompanimentToBBQ
add foreign key(a_id) references accompaniment(a_id) on update cascade on delete cascade,
add foreign key(b_id) references barbecue(b_id) on update cascade on delete cascade,
engine = innodb;

alter table grill
add foreign key(c_category_id) references category(c_id) on update cascade on delete cascade,
engine = innodb;

alter table meat
add foreign key(c_category_id) references category(c_id) on update cascade on delete cascade,
engine = innodb;

alter table salad
add foreign key(c_category_id) references category(c_id) on update cascade on delete cascade,
engine = innodb;

alter table accompaniment
add foreign key(c_category_id) references category(c_id) on update cascade on delete cascade,
engine = innodb;
set foreign_key_checks = 1;
