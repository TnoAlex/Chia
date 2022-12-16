create definer = root@localhost trigger student_auto_max_u
    after update
    on registration
    for each row
    UPDATE student as s
    SET s.max_score = IF((SELECT score FROM registration as r WHERE r.student_id = s.id) > s.max_score,
                         (SELECT score FROM registration as r WHERE r.student_id = s.id), s.max_score);

