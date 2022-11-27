create definer = root@localhost event captcha_auto_lapse on schedule
    every '20' SECOND
        starts '2022-11-27 20:22:52'
    enable
    do
    DELETE FROM captcha
WHERE TIMESTAMPDIFF(SECOND,captcha.create_time,NOW())>300;

