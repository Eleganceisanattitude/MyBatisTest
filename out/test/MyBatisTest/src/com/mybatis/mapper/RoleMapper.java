package com.mybatis.mapper;

import com.mybatis.pojo.PageParams;
import com.mybatis.pojo.Role;
import com.mybatis.pojo.RoleParams;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    public int insertRole(Role role);

    public int deleteRole(Long id);

    public int updateRole(Role role);

    public Role getRole(Long id);

    public List<Role> findRole(String roleName);

    /*这种方式不推荐，首先，map 是一个键值对应的集合，使用者要通过阅读它的键，才能明了其作用；其次，使用map不能限定其传递的数
     据类型，因此业务性质不强，可读性差，使用者要读懂代码才能知道需要传递什么参数给它，所以不推荐用这种方式传递多个参数。*/
    public List<Role> findRolesByMap(Map<String, Object> parameterMap);

    //通过注解的方式查询
    public List<Role> findRolesByAnnotation(@Param("roleName") String roleName, @Param("note") String note);

    //通过JavaBean传递多个参数
    public List<Role> findRolesByBean(RoleParams roleParams);

    //分页
    public List<Role> findByMix(@Param("params") RoleParams roleParams, @Param("page") PageParams pageParams);

}
