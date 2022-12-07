create definer = root@localhost event captcha_auto_delete on schedule
    every '20' SECOND
        starts '2022-11-30 11:41:44'
    enable
    do
    DELETE FROM captcha WHERE TIMESTAMPDIFF(SECOND,captcha.create_time,NOW())>420;

