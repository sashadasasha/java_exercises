--Это из Hibernate лога
create table courses_teachers (teacher_id INT UNSIGNED not null, course_id INT UNSIGNED not null) engine=InnoDB;
alter table courses_teachers add constraint FKdvtrxpe0p1mv99lsl08d8r3rg foreign key (course_id) references Courses (id);
alter table courses_teachers add constraint FK3wl4nefhpvfpymqs82duuy47y foreign key (teacher_id) references teachers (id);

--Это Insert Select запрос
INSERT INTO courses_teachers (teacher_id, course_id)
SELECT courses.teacher_id, courses.id FROM courses;