create table company
(
company_ID long,
company_name varchar(255),
primary key(company_ID)
);

create table job_title
(
job_title_ID long,
job_title varchar(255),
primary key(job_title_ID)
);

create table job_location
(
job_location_ID long,
job_location varchar(255),
primary key(job_location_ID)
);

create table job(
job_ID long,
job_name varchar(255),
is_active varchar(255),
expiry_date varchar(255),
primary key(job_ID)
);

create table swot_template(
student_ID varchar(255),
student_email varchar(255),
version long,
strength varchar(255),
weakness varchar(255),
opportunity varchar(255),
threat varchar(255),
student_name varchar(255),
student_degree varchar(255),
goal varchar(255),
primary key(student_ID)
)