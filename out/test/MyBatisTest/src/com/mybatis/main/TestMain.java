package com.mybatis.main;

import com.mybatis.mapper.RoleMapper;
import com.mybatis.pojo.PageParams;
import com.mybatis.pojo.Role;
import com.mybatis.pojo.RoleParams;
import com.mybatis.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(TestMain.class);
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionFactoryUtils.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Role role = roleMapper.getRole(1L);
            log.info(role.getRoleName());


            //使用Map接口传递参数
            System.out.println("使用Map接口传递参数");
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("roleName", "Elegance");
            parameterMap.put("note", "ele");
            List<Role> roleList = roleMapper.findRolesByMap(parameterMap);
            for (Role r : roleList
                    ) {
                System.out.println(r.getId());
                System.out.println(r.getRoleName());
                System.out.println(r.getNote());
            }

            //使用注解传递参数
            System.out.println("使用注解传递参数");
            for (Role r : roleList
                    ) {
                System.out.println(r.getId());
                System.out.println(r.getRoleName());
                System.out.println(r.getNote());
            }

            //使用JavaBean传递参数
            System.out.println("使用JavaBean传递参数");
            RoleParams roleParams = new RoleParams();
            roleParams.setRoleName("Elegance");
            roleParams.setNote("ele");
            roleList = roleMapper.findRolesByBean(roleParams);
            for (Role r : roleList
                    ) {
                System.out.println(r.getId());
                System.out.println(r.getRoleName());
                System.out.println(r.getNote());
            }

            //分页
            PageParams pageParams = new PageParams();
            pageParams.setStart(1);
            pageParams.setLimit(10);
            roleList = roleMapper.findByMix(roleParams, pageParams);

            for (Role r : roleList
                    ) {
                System.out.println(r.getId());
                System.out.println(r.getRoleName());
                System.out.println(r.getNote());
            }
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
