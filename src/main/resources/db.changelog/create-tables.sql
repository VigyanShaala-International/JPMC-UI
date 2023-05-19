create table company
(
company_Id varchar(50),
company_name varchar(255),
primary key(company_Id)
);

create table job_title
(
job_title_Id varchar(50),
job_title varchar(255),
primary key(job_title_Id)
);

create table job_location
(
job_location_Id varchar(50),
job_location varchar(255),
primary key(job_location_Id)
);

create table job(
job_Id varchar(255),
job_description varchar(255),
is_active varchar(255),
posting_date varchar(255),
expiry_date varchar(255),
company_Id varchar(255),
job_title_Id varchar(255),
job_location_Id varchar(255),
primary key(job_Id),
constraint company_Id foreign key (company_Id) references company(company_Id),
constraint job_title_Id foreign key (job_title_Id) references job_title(job_title_Id),
constraint job_location_Id foreign key (job_location_Id) references job_location(job_location_Id)
);


create table swot_template(
student_Id varchar(255),
student_email varchar(255),
version long,
strength varchar(255),
weakness varchar(255),
opportunity varchar(255),
threat varchar(255),
student_name varchar(255),
student_degree varchar(255),
goal varchar(255),
primary key(student_Id)
);

create table questionnaire(
questionnaire_Id varchar(50),
question1 varchar(255),
question2 varchar(255),
question3 varchar(255),
question4 varchar(255),
question5 varchar(255),
primary key(questionnaire_Id)
);