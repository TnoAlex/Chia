create definer = root@localhost trigger reg_num_d
    after delete
    on registration
    for each row
    UPDATE csp_info SET csp_info.quantity = csp_info.quantity -1 WHERE csp_info.id = old.csp_id;

