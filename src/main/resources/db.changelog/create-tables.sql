create table company
(
company_ID varchar(50),
company_name varchar(255),
primary key(company_ID)
);

create table job_title
(
job_title_ID varchar(50),
job_title varchar(255),
primary key(job_title_ID)
);

create table job_location
(
job_location_ID varchar(50),
job_location varchar(255),
primary key(job_location_ID)
);

create table job(
job_ID varchar(255),
job_description varchar(255),
is_active varchar(255),
posting_date varchar(255),
expiry_date varchar(255),
company_ID varchar(255),
job_title_ID varchar(255),
job_location_ID varchar(255),
primary key(job_ID),
constraint company_ID foreign key (company_ID) references company(company_ID),
constraint job_title_ID foreign key (job_title_ID) references job_title(job_title_ID),
constraint job_location_ID foreign key (job_location_ID) references job_location(job_location_ID)
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
);