package com.wd7.authcentercpn1.dao;

import com.wd7.authcentercpn1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface SysUserRepository extends JpaRepository<UserEntity,Integer> {

    public UserEntity findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE UserEntity xe set xe.password=:pwd")
    int  updatePasswordAll(@Param("pwd") String pwd);


}
