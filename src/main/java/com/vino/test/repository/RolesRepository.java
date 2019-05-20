package com.vino.test.repository;

import com.vino.test.models.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
    @Query(
            value = "select role from Roles role where role.name like :param or role.id like :param "
    )
    Page<Roles> getPagingAllData(@Param("param")String searchParam, Pageable pageable);

}
