create table company
(
companyId long,
companyName varchar(255),
primary key(companyId)
);

create table job_title
(
jobTitleId long,
jobTitle varchar(255),
primary key(jobTitleId)
);

create table job_location
(
jobLocationId long,
jobLocation varchar(255),
primary key(jobLocationId)
);

create table job(
jobId long,
jobName varchar(255),
primary key(jobId)
);