package com.wd7.authcentercpn1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.ConstructorArgs;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="sys_user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private    Integer id;
    private    String  username;
    private    String  password;
    private    boolean accountNonExpired;
    private    boolean accountNonLocked;
    private    boolean credentialsNonExpired;
    private    boolean enabled;


}
