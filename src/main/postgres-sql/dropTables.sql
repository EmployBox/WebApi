DROP TABLE IF EXISTS Schedule;
DROP TABLE IF EXISTS Project;
DROP TABLE IF EXISTS Follows;
DROP TABLE IF EXISTS Comment;
DROP TABLE IF EXISTS Message;
DROP TABLE IF EXISTS Chat;
DROP TABLE IF EXISTS Rating;
DROP TABLE IF EXISTS Application;
DROP TABLE IF EXISTS JobExperience;
DROP TABLE IF EXISTS CurriculumExperience;
DROP TABLE IF EXISTS Job;
DROP TABLE IF EXISTS PreviousJobs;
DROP TABLE IF EXISTS AcademicBackground;
DROP TABLE IF EXISTS Curriculum;
DROP TABLE IF EXISTS Moderator;
DROP TABLE IF EXISTS UserAccount;
DROP TABLE IF EXISTS Company;
DROP TABLE IF EXISTS Account;

DROP SEQUENCE IF EXISTS academicbackground_seq;
DROP SEQUENCE IF EXISTS account_seq;
DROP SEQUENCE IF EXISTS application_seq;
DROP SEQUENCE IF EXISTS chat_seq;
DROP SEQUENCE IF EXISTS comment_seq;
DROP SEQUENCE IF EXISTS curriculum_seq;
DROP SEQUENCE IF EXISTS curriculumexperience_seq;
DROP SEQUENCE IF EXISTS job_seq;
DROP SEQUENCE IF EXISTS jobexperience_seq;
DROP SEQUENCE IF EXISTS message_seq;
DROP SEQUENCE IF EXISTS previousjobs_seq;
DROP SEQUENCE IF EXISTS project_seq;
DROP SEQUENCE IF EXISTS schedule_seq;

DROP FUNCTION IF EXISTS incrementversion;