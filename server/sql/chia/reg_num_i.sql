create definer = root@localhost trigger reg_num_i
    after insert
    on registration
    for each row
    UPDATE csp_info SET csp_info.quantity = csp_info.quantity + 1 WHERE csp_info.id = new.csp_id;

