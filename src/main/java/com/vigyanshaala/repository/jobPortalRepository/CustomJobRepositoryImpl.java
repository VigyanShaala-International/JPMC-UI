package com.vigyanshaala.repository.jobPortalRepository;

import com.vigyanshaala.entity.jobPortalEntity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class CustomJobRepositoryImpl implements CustomJobRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    public CustomJobRepositoryImpl() {

    }

    public List<Job> fetchAll(JobFilter filter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery query = criteriaBuilder.createQuery(Job.class);
        Root<Job> jobTable = query.from(Job.class);
        Root<JobLocation> locationTable = query.from(JobLocation.class);
        Root<Company> companyTable = query.from(Company.class);
        Root<JobTitle> jobTitleTable = query.from(JobTitle.class);
        Root<WorkMode> workModeTable = query.from(WorkMode.class);
        Root<Industry> industryTable = query.from(Industry.class);
        Root<EducationLevel> educationLevelTable = query.from(EducationLevel.class);

        Join<Job, JobLocation> locationJoin = jobTable.join("jobLocation", JoinType.INNER);
        Join<Job, Company> companyJoin = jobTable.join("company", JoinType.INNER);
        Join<Job, JobTitle> jobTitleJoin = jobTable.join("jobTitle", JoinType.INNER);
        Join<Job, WorkMode> workModeJoin = jobTable.join("workMode", JoinType.INNER);
        Join<Job, EducationLevel> educationLevelJoin = jobTable.join("educationLevel", JoinType.INNER);
        Join<Job, Industry> industryJoin = jobTable.join("industry", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getLocation() != "" && filter.getLocation() != null) {
            predicates.add(criteriaBuilder.equal(locationTable.get("jobLocation"), filter.getLocation()));
        }

        if (filter.getCompany() != "" && filter.getCompany() != null) {
            predicates.add(criteriaBuilder.equal(companyTable.get("companyName"), filter.getCompany()));
        }

        if (filter.getJobTitle() != "" && filter.getJobTitle() != null) {
            predicates.add(criteriaBuilder.equal(jobTitleTable.get("jobTitle"), filter.getJobTitle()));
        }

        if (filter.getWorkMode() != "" && filter.getWorkMode() != null) {
            predicates.add(criteriaBuilder.equal(workModeTable.get("workMode"), filter.getWorkMode()));
        }

        if (filter.getIndustry() != "" && filter.getIndustry() != null) {
            predicates.add(criteriaBuilder.equal(industryTable.get("industry"), filter.getIndustry()));
        }

        if (filter.getEducationLevel() != "" && filter.getEducationLevel() != null) {
            predicates.add(criteriaBuilder.equal(educationLevelTable.get("educationLevel"), filter.getEducationLevel()));
        }

        if (filter.getFromDate() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(jobTable.get("postingDate"), filter.getFromDate()));
        }

        predicates.add(criteriaBuilder.greaterThanOrEqualTo(jobTable.get("expiryDate"), LocalDate.now()));

        query.multiselect(companyJoin, jobTitleJoin, locationJoin, workModeJoin, educationLevelJoin, industryJoin);

        query.where(predicates.stream().toArray(Predicate[]::new));

        return entityManager.createQuery(query.select(jobTable).orderBy((criteriaBuilder.desc(jobTable.get("postingDate"))))).getResultList();

    }
}
