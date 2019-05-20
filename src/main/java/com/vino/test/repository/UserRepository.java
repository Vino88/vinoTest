package com.vino.test.repository;

import com.vino.test.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<Users, Integer> {
    Users findByEmail(String email);
    Users findByUsername(String username);

    @Query(
            value = "select users from Users users where users.name like :param"

    )
    List<Users> getAllData(@Param("param")String searchParam);

    @Query(
            value = "select users from Users users where users.name like :param"

    )
    Page<Users> getPagingAllData(@Param("param")String searchParam, Pageable pageable);

    @Query(
            value = "select users from Users users where users.role = 3 and (users.username like :param or users.name like :param or users.email like :param)"
    )
    List<Users> getDataByRoleUser(@Param("param")String searchParam);
}
