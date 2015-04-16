use annetteprojekt;
set foreign_key_checks = 0;

delete from customer;
insert into customer (cus_name, cus_phone, cus_address, cus_email) values
('Twix', '31668691', '',''), 
('Lisbeth', '28933037','',''),
('Charlotte', '25667609','',''),
('Henrik Jess', '60214417', '', '');

delete from massagetype;
insert into massagetype (mt_id, mt_price, mt_type, mt_duration) values
(1,175,'Halv krops massage', 30),
(2,350,'Hel krops massage', 60); 

delete from massage;
insert into massage (m_id, m_comment, m_startTime, m_type_id) values
(1,'har meget ondt i ryggen', '14:30',2);

delete from calendar;
insert into calendar (c_date, c_customer_number, c_massage_id) values
('2014-09-30 16:02:00','31668691',1);

delete from category;
insert into category(c_id, c_name) values
(1, 'Kød'), (2, 'Salater'), (3, 'Tilbehør'), (4, 'Grille');

delete from meat;
insert into meat(m_id, m_type, m_pricePrKilo, c_category_id) values
(1, 'Hel gris 0-35 kg', 40, 1),
(2, 'Hel gris 35-50 kg', 35, 1),
(3, 'Hel gris 50-90 kg', 30, 1),
(4, 'Oksefilet', 175, 1),
(5, 'Okse striploin', 150, 1),
(6, 'Kyllingefilet', 30, 1),
(7, 'Kamsteg', 35, 1),
(8, 'Skinkesteg', 32, 1),
(9, 'Saltede svineskanke', 0, 1),
(10, '"Pip" på dåse', 0, 1),
(11, 'Fisk - Laks', 0, 1),
(13, 'Pølser - Frankfurter', 0, 1),
(14, 'Oksehøjreb', 0, 1);

delete from accompaniment;
insert into accompaniment(a_id, a_type, a_pricePerHead, c_category_id) values
(1, 'Varm Kartoffelsalat 1-20 pers.', 10, 3),
(2, 'Varm Kartoffelsalat 20+ pers.', 7, 3),
(3, 'Flødekartofler 1-20 pers.', 10, 3),
(4, 'Flødekartofler 20+ pers.', 7, 3),
(5, 'Brasede Kartofler', 5, 3),
(6, 'Kold Kartoffelsalat 1-20 pers.', 20, 3),
(7, 'Kold Kartoffelsalat 20+ pers.', 14, 3),
(8, 'Flütes m/ smør & hvidløgsmsør', 0, 3);

delete from salad;
insert into salad(s_id, s_type, s_pricePerHead, c_category_id) values
(1, 'Salatbar', 0, 2),
(2, 'Coleslaw', 0, 2),
(3, 'Broccolisalat', 0, 2),
(4, 'Tzatziki', 0, 2),
(5, 'Æblesalat', 0, 2),
(6, 'Tomatsalat', 0, 2),
(7, 'Dressing', 0, 2);

delete from vegetable;
insert into vegetable(v_id, v_type, v_pricePerHead) values
(1, 'Iceberg', 0),
(2, 'Agurk', 0),
(3, 'Tomat', 0),
(4, 'Pebefrugt', 0),
(5, 'Ærter', 0),
(6, 'Majs', 0),
(7, 'Feta', 0),
(8, 'Syltede Hvidløg', 0),
(9, 'Oliven', 0),
(10, 'Croutoner', 0),
(11, 'Løg', 0),
(12, 'Peanuts', 0),
(13, 'Jalapeños', 0);

delete from vegetabletosalad;
insert into vegetabletosalad(s_id, v_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1,5), (1, 6);

set foreign_key_checks = 1;