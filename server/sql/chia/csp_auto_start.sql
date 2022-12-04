create definer = root@localhost event csp_auto_start on schedule
    every '1' MINUTE
        starts '2022-12-04 17:40:55'
    enable
    do
    UPDATE csp_info
    SET csp_info.state = 1
    WHERE TIMESTAMPDIFF(SECOND, csp_info.start_time, NOW()) <= 0;

