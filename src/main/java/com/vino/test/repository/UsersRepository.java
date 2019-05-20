package com.vino.test.repository;

import com.vino.test.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(
            value = "select us from Users us where us.name like :param or us.email like :param or us.username like :param"
    )
    Page<Users> getPagingAllData(@Param("param")String searchParam, Pageable pageable);

}
