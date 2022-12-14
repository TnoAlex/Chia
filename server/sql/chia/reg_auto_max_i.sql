create definer = root@localhost trigger reg_auto_max_i
    after insert
    on registration
    for each row
    UPDATE csp_info
    SET csp_info.score_h = (SELECT MAX(registration.score) FROM registration)
    WHERE csp_info.id = new.csp_id;

