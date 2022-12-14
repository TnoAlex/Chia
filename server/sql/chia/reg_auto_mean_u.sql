create definer = root@localhost trigger reg_auto_mean_u
    after update
    on registration
    for each row
    UPDATE csp_info
    SET csp_info.score_a = (SELECT AVG(score) FROM registration as r WHERE r.csp_id = new.csp_id)
    WHERE csp_info.id = new.csp_id;

