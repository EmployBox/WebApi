CREATE TABLE Account (
  accountId serial PRIMARY KEY NOT NULL,
  name VARCHAR(40) not null,
  email VARCHAR(25) UNIQUE NOT NULL,
  rating double precision default(0.0),
  accountType VARCHAR(3) not null,
  password VARCHAR(100) NOT NULL,
  version BIGINT DEFAULT(1),

  CHECK (rating >= 0.0 AND rating <= 10.0),
  CHECK (accountType in ('USR', 'CMP', 'MOD'))
);

CREATE TABLE Company (
  accountId BIGINT primary key references Account,
  yearFounded int,
  specialization VARCHAR(20),
  address VARCHAR(50),
  webPageUrl VARCHAR(200),
  LogoUrl VARCHAR(200),
  description VARCHAR(50),
  version BIGINT DEFAULT(1)
);

CREATE TABLE Moderator (
  accountID BIGINT primary key references Account
);

CREATE TABLE UserAccount (
  accountId BIGINT primary key references Account,
  summary VARCHAR(1500),
  PhotoUrl VARCHAR(200),
  version BIGINT DEFAULT(1)
);

CREATE TABLE Curriculum(
  curriculumId serial primary key,
  accountId BIGINT references UserAccount,
  title varchar(50),
  version BIGINT DEFAULT(1)
);

CREATE TABLE Project (
  projectId serial PRIMARY KEY,
  accountId BIGINT NOT NULL,
  curriculumId BIGINT NOT NULL,
  name VARCHAR(15),
  description VARCHAR(50),
  version BIGINT DEFAULT(1),

  FOREIGN KEY (curriculumId) REFERENCES curriculum,
  FOREIGN KEY (accountId) REFERENCES Account
);

CREATE TABLE AcademicBackground(
  academicBackgroundKey serial PRIMARY KEY,
  accountId BIGINT NOT NULL,
  curriculumId BIGINT NOT NULL,
  beginDate TIMESTAMP(3) DEFAULT(NOW()),
  endDate TIMESTAMP(3),
  studyArea VARCHAR(40),
  institution VARCHAR(40),
  degreeObtained VARCHAR(10),
  version BIGINT DEFAULT(1),

  FOREIGN KEY (accountId) REFERENCES Account,
  FOREIGN KEY (curriculumId) REFERENCES Curriculum(curriculumId),
  check (endDate > beginDate),
  check (degreeObtained in ('basic level 1', 'basic level 2', 'basic level 3', 'secundary', 'bachelor', 'master', 'PHD'))
);

CREATE TABLE PreviousJobs(
  previousJobId serial PRIMARY KEY,
  accountId BIGINT NOT NULL,
  curriculumId BIGINT NOT NULL,
  beginDate TIMESTAMP(3) DEFAULT(NOW()),
  endDate TIMESTAMP(3),
  companyName VARCHAR(20),
  workload VARCHAR(20),
  role VARCHAR(20),
  version BIGINT DEFAULT(1),

  FOREIGN KEY (curriculumId) REFERENCES Curriculum,
  CHECK(workLoad = 'partial' OR workLoad = 'total')
);

CREATE TABLE Job(
  jobId serial primary key,
  title varchar(50) not null,
  accountId BIGINT ,
  schedule VARCHAR(20),
  type VARCHAR(25),
  wage INT check(wage >= 0),
  description VARCHAR(50),
  offerBeginDate TIMESTAMP(3) DEFAULT(NOW()),
  offerEndDate TIMESTAMP(3),
  offerType VARCHAR(30) NOT NULL,
  country VARCHAR(15),
  Address VARCHAR(50),
  district VARCHAR(40),
  longitude real,
  latitude real,
  version BIGINT DEFAULT(1),

  FOREIGN KEY (accountID) REFERENCES Account,
  check(offerType = 'Looking for work' OR offerType = 'Looking for Worker')
);

CREATE TABLE CurriculumExperience(
  curriculumExperienceId serial PRIMARY KEY,
  accountId BIGINT NOT NULL,
  curriculumId BIGINT NOT NULL,
  competence VARCHAR(50),
  years SMALLINT,
  version BIGINT DEFAULT(1),

  FOREIGN KEY (curriculumId) REFERENCES Curriculum,
  FOREIGN KEY (accountId) REFERENCES Account
);

CREATE TABLE JobExperience(
  jobExperienceId serial PRIMARY KEY,
  jobId BIGINT NOT NULL,
  competence VARCHAR(50),
  years SMALLINT,
  version BIGINT DEFAULT(1),

  FOREIGN KEY (jobId) REFERENCES Job
);

CREATE TABLE Application(
  applicationId serial PRIMARY KEY,
  accountId BIGINT,
  curriculumId BIGINT,
  jobId BIGINT,
  datetime timestamp(3) default(NOW()),
  version BIGINT DEFAULT(1),

  FOREIGN KEY (accountId) REFERENCES Account,
  FOREIGN KEY (curriculumId) REFERENCES curriculum,
  FOREIGN KEY (jobId) REFERENCES Job
);

CREATE TABLE Rating(
  accountIdFrom BIGINT,
  accountIdTo BIGINT,
  moderatorId BIGINT references Moderator,
  workLoad real DEFAULT 0.0  check (workLoad >= 0.0 AND workLoad <= 10.0),
  wage real DEFAULT 0.0 check (wage >= 0.0 AND wage <= 10.0),
  workEnviroment real DEFAULT 0.0 check (workEnviroment >= 0.0 AND workEnviroment <= 10.0),
  competence real DEFAULT 0.0 check (competence >= 0.0 AND competence <= 10.0),
  ponctuality real DEFAULT 0.0 check (ponctuality >= 0.0 AND ponctuality <= 10.0),
  assiduity real DEFAULT 0.0 check (assiduity>= 0.0 AND assiduity <= 10.0),
  demeanor real DEFAULT 0.0 check (demeanor >= 0.0 AND demeanor <= 10.0),
  version BIGINT DEFAULT(1),

  FOREIGN KEY (accountIdFrom) REFERENCES Account(accountID),
  FOREIGN KEY (accountIdTo) REFERENCES Account(accountID),

  PRIMARY KEY(AccountIdFrom , AccountIdTo)
);

CREATE TABLE Chat(
  chatId serial PRIMARY KEY,
  accountIdFirst BIGINT,
  accountIdSecond BIGINT,
  version BIGINT DEFAULT(1),

  FOREIGN KEY (accountIdFirst) REFERENCES Account(accountID),
  FOREIGN KEY (accountIdSecond) REFERENCES Account(accountID),
  UNIQUE (AccountIdFirst, AccountIdSecond)
);

CREATE TABLE Message(
  messageId serial primary key,
  accountId BIGINT,
  chatId BIGINT,
  text VARCHAR(200),
  datetime timestamp(3) default(now()),
  version BIGINT DEFAULT(1),

  FOREIGN KEY (chatId) REFERENCES Chat(chatId) ON DELETE CASCADE,
  FOREIGN KEY (accountId) REFERENCES Account ON DELETE CASCADE
);

CREATE TABLE Schedule(
  scheduleId serial primary key,
  jobId BIGINT,
  repeats VARCHAR(25),
  date TIMESTAMP(3),
  starthour TIMESTAMP(3),
  endhour TIMESTAMP(3),
  version BIGINT DEFAULT(1),

  FOREIGN KEY (jobId) REFERENCES Job
);

CREATE TABLE Comment (
  commentId serial primary key,
  accountIdFrom BIGINT,
  accountIdDest BIGINT,
  datetime TIMESTAMP(3) default(now()),
  text VARCHAR(300),
  mainCommentId BIGINT,
  status boolean,
  version BIGINT DEFAULT(1),

  FOREIGN KEY (accountIdFrom) REFERENCES Account(accountID),
  FOREIGN KEY (accountIdDest) REFERENCES Account(accountID),
  FOREIGN KEY (MainCommentId) REFERENCES Comment(commentId)
);

CREATE TABLE Follows (
  accountIdFollowed BIGINT references Account,
  accountIdFollower BIGINT references Account,
  version BIGINT DEFAULT(1),

  primary key (accountIdFollowed, accountIdFollower)
);
