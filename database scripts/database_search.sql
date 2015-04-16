select c_date, c_customer_number, c_massage_id, m_id, m_type_id, m_startTime, m_comment, 
mt_id, mt_price, mt_type, mt_duration, cus_name, cus_phone, cus_homeAddress, cus_address
from calendar, massage, massagetype, customer
where c_date > '2014-09-20 00:00:00'
and c_date < '2014-09-30 23:59:59'
and c_massage_id = m_id
and m_type_id = mt_id
and c_customer_number = cus_phone
