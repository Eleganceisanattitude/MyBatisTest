package com.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
    private final static Class<SqlSessionFactoryUtils> LOCK = SqlSessionFactoryUtils.class;
    private static SqlSessionFactory SqlSessionFactory = null;

    //构造方法加入private关键字使得其他代码不能通过new关键字来创建它
    private SqlSessionFactoryUtils() {
    }

    //加入 synchronized 关键宇加锁 ，主要是为了防止在多线程中多次实例化 SqlSessionFactory对象，
    //从而保证 SqlSessionFactory 的唯一性
    public static SqlSessionFactory getSqlSessionFactoryUtils() {
        synchronized (LOCK) {
            if (SqlSessionFactory != null) {
                return SqlSessionFactory;
            }
            String resource = "mybatis-config.xml";
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream(resource);
                SqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return SqlSessionFactory;
        }
    }

    //创建SqlSession对象
    public static SqlSession openSqlSession() {
        if (SqlSessionFactory == null) {
            getSqlSessionFactoryUtils();
        }
        return SqlSessionFactory.openSession();
    }

}
