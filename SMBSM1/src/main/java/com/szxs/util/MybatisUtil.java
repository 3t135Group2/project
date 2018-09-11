package com.szxs.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {

    private static SqlSessionFactory factory;
    static {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession openSession(){
        return factory.openSession();
    }

    public static SqlSession openSession(boolean autoCommit){
        return factory.openSession(autoCommit);
    }

    public static  void closeSqlSession(SqlSession sqlSession){
       if(null!=sqlSession){
           sqlSession.close();
       }
    }

}
