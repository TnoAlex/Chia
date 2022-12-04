create definer = root@localhost event csp_auto_end on schedule
    every '1' MINUTE
        starts '2022-12-04 17:41:58'
    enable
    do
    UPDATE csp_info SET csp_info.state=-1 WHERE TIMESTAMPDIFF(SECOND,csp_info.end_time,NOW())<=0;

