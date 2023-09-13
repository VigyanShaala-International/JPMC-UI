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


create table education_level(
education_level_id varchar(50),
education_level varchar(50),
primary key(education_level_id)
);

create table industry(
industry_id varchar(50),
industry varchar(50),
primary key(industry_id)
);

create table work_mode(
work_mode_id varchar(50),
work_mode varchar(50),
primary key(work_mode_id)
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
education_level_id varchar(50),
industry_id varchar(50),
work_mode_id varchar(50),
primary key(job_id),
constraint company_id foreign key (company_id) references company(company_id),
constraint job_title_id foreign key (job_title_id) references job_title(job_title_id),
constraint job_location_id foreign key (job_location_id) references job_location(job_location_id),
constraint education_level_id foreign key (company_id) references education_level(education_level_id),
constraint industry_id foreign key (company_id) references industry(industry_id),
constraint work_mode_id foreign key (company_id) references work_mode(work_mode_id)
);


create table swot_template(
student_id varchar(50),
student_email varchar(255),
version long,
strength varchar(1255),
weakness varchar(1255),
opportunity varchar(12255),
threat varchar(1255),
student_name varchar(255),
student_degree varchar(255),
goal varchar(1255),
primary key(student_id)
);

create table ria_template(
student_id varchar(50),
student_email varchar(255),
version long,
realistic varchar(255),
investigative varchar(255),
artistic varchar(255),
social varchar(255),
enterprising varchar(255),
conventional varchar(255),
hollandCode varchar(255),
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

create table user_role(
 email_id varchar(50),
 role varchar(50),
 primary key (email_id));



