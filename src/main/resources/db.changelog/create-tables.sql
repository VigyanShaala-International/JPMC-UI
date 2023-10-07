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
job_description BYTEA,
is_active varchar(1),
posting_date date,
expiry_date date,
company_id varchar(50),
job_title_id varchar(50),
job_location_id varchar(50),
education_level_id varchar(50),
industry_id varchar(50),
work_mode_id varchar(50),
hr_email varchar(50),
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
strength BYTEA,
weakness BYTEA,
opportunity BYTEA,
threat BYTEA,
student_name varchar(255),
student_degree varchar(255),
goal BYTEA,
primary key(student_id)
);

create table ria_template(
student_id varchar(50),
student_email varchar(255),
version long,
realistic BYTEA,
investigative BYTEA,
artistic BYTEA,
social BYTEA,
enterprising BYTEA,
conventional BYTEA,
hollandCode BYTEA,
primary key(student_id)
);

create table smart_goals_template(
student_id varchar(50),
student_email varchar(255),
version long,
goal BYTEA,
specific BYTEA,
measurable BYTEA,
achievable BYTEA,
relevant BYTEA,
timeBound BYTEA,
primary key(student_id)
);

create table collaboration_template(
student_id varchar(50),
student_email varchar(255),
version long,
answerA BYTEA,
answerB BYTEA,
answerC BYTEA,
primary key(student_id)
);

create table creativemindset_template(
student_id varchar(50),
student_email varchar(255),
version long,
answerA BYTEA,
answerB BYTEA,
answerC BYTEA,
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
 cohort varchar(50),
 primary key (email_id));


create table job_application(
job_application_id varchar(50),
student_id varchar(50),
job_id varchar(50),
answer1 BYTEA,
answer2 BYTEA,
answer3 BYTEA,
answer4 BYTEA,
answer5 BYTEA,
is_job_application_posted_to_hr varchar(1),
student_name varchar(50),
student_email varchar(50),
primary key(job_application_id),
constraint job_id foreign key (job_id) references job(job_id)
);

create table student_document(
student_document_id varchar(50),
job_application_id varchar(50),
document_type varchar(50),
blob_data BLOB,
primary key(student_document_id),
constraint job_application_id foreign key (job_application_id) references job_application(job_application_id)
);

create table document_type(
document_type_id varchar(50),
document_type varchar(50),
description varchar(50),
primary key(document_type_id)
);


create table idp_template(
student_id varchar(50),
student_email varchar(255),
version long,
student_name varchar(255),
goala_goal1 varchar(255),
goala_name varchar(255),
goala_education1 varchar(255),
goala_education2 varchar(255),
goala_proudMoments varchar(255),
goala_raisec varchar(255),
goala_match_strength varchar(255),
goala_decrease_threat varchar(255),
goala_aquired_hard_skills varchar(255),
goala_aquired_soft_skills varchar(255),
goala_goal2 varchar(255),
goala_career_options varchar(255),
goala_hard_skills_to_aquire varchar(255),
goala_soft_skills_to_aquire varchar(255),
goala_employers varchar(255),
goala_mentor1 varchar(255),
goala_mentor2 varchar(255),
goala_mentor3 varchar(255),
goala_mentor4 varchar(255),
goalamile_stone1 varchar(255),
goalamile_stone2 varchar(255),
goalamile_stone3 varchar(255),
goala_m1_step1 varchar(255),
goala_m1_step2 varchar(255),
goala_m1_step3 varchar(255),
goala_m2_step1 varchar(255),
goala_m2_step2 varchar(255),
goala_m2_step3 varchar(255),
goala_m3_step1 varchar(255),
goala_m3_step2 varchar(255),
goala_m3_step3 varchar(255),
goalaadjustment varchar(255),
goalareview varchar(255),
goalb_goal1 varchar(255),
goalb_name varchar(255),
goalb_education1 varchar(255),
goalb_education2 varchar(255),
goalb_proudMoments varchar(255),
goalb_raisec varchar(255),
goalb_match_strength varchar(255),
goalb_decrease_threat varchar(255),
goalb_aquired_hard_skills varchar(255),
goalb_aquired_soft_skills varchar(255),
goalb_goal2 varchar(255),
goalb_career_options varchar(255),
goalb_hard_skills_to_aquire varchar(255),
goalb_soft_skills_to_aquire varchar(255),
goalb_employers varchar(255),
goalb_mentor1 varchar(255),
goalb_mentor2 varchar(255),
goalb_mentor3 varchar(255),
goalb_mentor4 varchar(255),
goalbmile_stone1 varchar(255),
goalbmile_stone2 varchar(255),
goalbmile_stone3 varchar(255),
goalb_m1_step1 varchar(255),
goalb_m1_step2 varchar(255),
goalb_m1_step3 varchar(255),
goalb_m2_step1 varchar(255),
goalb_m2_step2 varchar(255),
goalb_m2_step3 varchar(255),
goalb_m3_step1 varchar(255),
goalb_m3_step2 varchar(255),
goalb_m3_step3 varchar(255),
goalbadjustment varchar(255),
goalbreview varchar(255),
primary key(student_id)
);