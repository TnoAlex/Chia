create definer = root@localhost trigger pre_num_i
    after insert
    on pre_registration
    for each row
    UPDATE csp_info SET csp_info.pre_quantity = csp_info.pre_quantity + 1 WHERE csp_info.id = new.csp_id;

