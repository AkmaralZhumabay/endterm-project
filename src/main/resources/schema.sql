DROP TABLE IF EXISTS enrollments;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS courses;

CREATE TABLE students (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          email VARCHAR(120) UNIQUE NOT NULL
);

CREATE TABLE addresses (
                           student_id INT PRIMARY KEY,
                           city VARCHAR(80) NOT NULL,
                           street VARCHAR(120) NOT NULL,
                           FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

CREATE TABLE courses (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(120) NOT NULL,
                         credits INT NOT NULL CHECK (credits > 0)
);

CREATE TABLE enrollments (
                             id SERIAL PRIMARY KEY,
                             student_id INT NOT NULL,
                             course_id INT NOT NULL,
                             FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
                             FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE RESTRICT,
                             UNIQUE(student_id, course_id)
);
