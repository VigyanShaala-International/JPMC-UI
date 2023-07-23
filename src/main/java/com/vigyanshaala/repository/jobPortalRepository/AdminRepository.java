package com.vigyanshaala.repository.jobPortalRepository;

import com.vigyanshaala.entity.jobPortalEntity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, String>{

    @Transactional
    @Query(value="SELECT admin_name FROM admin a where a.admin_id=:adminEmail", nativeQuery = true)
    String getAdminName (
            @Param("adminEmail") String adminEmail);


}
