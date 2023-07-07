create table company
(
company_id varchar(50),
company_name varchar(255),
primary key(company_id)
);

create table job_title
(
job_title_id varchar(50),
job_title varchar(255),
primary key(job_title_id)
);

create table job_location
(
job_location_id varchar(50),
job_location varchar(255),
primary key(job_location_id)
);

create table job(
job_id varchar(50),
job_description varchar(255),
is_active varchar(1),
posting_date date,
expiry_date date,
company_id varchar(50),
job_title_id varchar(50),
job_location_id varchar(50),
primary key(job_id),
constraint company_id foreign key (company_id) references company(company_id),
constraint job_title_id foreign key (job_title_id) references job_title(job_title_id),
constraint job_location_id foreign key (job_location_id) references job_location(job_location_id)
);


create table swot_template(
student_id varchar(50),
student_email varchar(255),
version long,
strength varchar(255),
weakness varchar(255),
opportunity varchar(255),
threat varchar(255),
student_name varchar(255),
student_degree varchar(255),
goal varchar(255),
primary key(student_id)
);

create table questionnaire(
questionnaire_id varchar(50),
question1 varchar(255),
question2 varchar(255),
question3 varchar(255),
question4 varchar(255),
question5 varchar(255),
primary key(questionnaire_id)
);

create table admin(
admin_id varchar(50),
admin_email varchar(50),
primary key (admin_id));