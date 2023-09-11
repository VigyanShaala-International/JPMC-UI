package com.vigyanshaala.repository.jobPortalRepository;

import com.vigyanshaala.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, String>{

//    @Transactional
//    @Query(value="SELECT student_name FROM student s where s.student_id=:studentEmail", nativeQuery = true)
//    String getStudentName (
//            @Param("studentEmail") String studentEmail);
//

    @Override
    Optional<UserRole> findById(String emailId);
}

