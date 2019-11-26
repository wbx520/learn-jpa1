package com.wbx.domain;/*
 *@author 王炳祥
 *@createTime
 */

import com.wbx.utils.jpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql查询
 */
public class jpqlTest {
    /**
     * 查询全部
     *      jpql: from com.wbx.domain.Customer
     *      简写：from Customer
     *      sql:select * from 表名
     */

    @Test
    public void testFindAll(){
        //1.通过工具类获取EntityManager
        EntityManager entityManager= jpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        EntityTransaction tx = entityManager.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //3.查询全部
        String jpql="from com.wbx.domain.Customer";
        Query query = entityManager.createQuery(jpql);//创建Query对象，query对象才是执行jpql的对象
        //发送查询，并封装结果集
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
            
        }
        //4.提交事务（混滚事务）
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 排序查询，倒序查询所有（按id倒序）
     *      jpql: from com.wbx.domain.Customer  oeder by 实体类中的属性id desc
     *      简写：from Customer  order by 实体类中的属性id desc
     *      sql:select * from 表名 order by 主键 desc
     *
     * 进行jpql查询
     *      1.创建query查询对象
     *      2.对参数进行复制
     *      3.查询，并得到返回结果
     */

    @Test
    public void TestOrders(){
        //1.通过工具类获取EntityManager
        EntityManager entityManager= jpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        EntityTransaction tx = entityManager.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //3.查询全部
        String jpql="from com.wbx.domain.Customer order by id desc";
        Query query = entityManager.createQuery(jpql);//创建Query对象，query对象才是执行jpql的对象
        //发送查询，并封装结果集
        //getResultList:直接将查询结果封装成list
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);

        }
        //4.提交事务（混滚事务）
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 使用jpql查询 统计总数
     *      sql：select count（id）from 表名
     *      jpql:select count(实体类id属性名) from 实体类
     */
    @Test
    public void TestCount(){
        //1.通过工具类获取EntityManager
        EntityManager entityManager= jpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        EntityTransaction tx = entityManager.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //3.查询全部
        String jpql="select count(id) from com.wbx.domain.Customer";
        Query query = entityManager.createQuery(jpql);//创建Query对象，query对象才是执行jpql的对象
        //发送查询，并封装结果集
        //getResultList:直接将查询结果封装成list
        //getSingleResult:得到唯一结果集
        Object result = query.getSingleResult();
        System.out.println(result);
        //4.提交事务（混滚事务）
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 分页查询
     * sql：select * from 表名 limit ?,?
     * jpql：from Customer
     */
    @Test
    public void TestPaged(){
        //1.通过工具类获取EntityManager
        EntityManager entityManager= jpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        EntityTransaction tx = entityManager.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //3.查询全部
        //i.根据jpql语句创建query对象
        String jpql=" from com.wbx.domain.Customer";
        Query query = entityManager.createQuery(jpql);//创建Query对象，query对象才是执行jpql的对象
        //ii.对参数赋值 --分页参数
        //起始索引
        query.setFirstResult(0);
        //每页查询的条数
        query.setMaxResults(2);
        //发送查询，并封装结果集
        //getResultList:直接将查询结果封装成list
        //getSingleResult:得到唯一结果集
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }

        //4.提交事务（混滚事务）
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

    /**
     * 条件查询
     * 查询姓名以“余”开头的数据
     *      sql：select * from 表名 where uname like ?
     *      jpql:from 实体类 where 属性 like ？
     */
    @Test
    public void TestCondition(){
        //1.通过工具类获取EntityManager
        EntityManager entityManager= jpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        EntityTransaction tx = entityManager.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //3.查询全部
        //i.根据jpql语句创建query对象
        String jpql=" from com.wbx.domain.Customer where uname like ?";
        Query query = entityManager.createQuery(jpql);//创建Query对象，query对象才是执行jpql的对象
        //ii.对参数赋值 --占位符参数
        //第一个参数：占位符的索引位置（从1开始），第二个参数：取值
        query.setParameter(1,"%余%");
        //发送查询，并封装结果集
        //getResultList:直接将查询结果封装成list
        //getSingleResult:得到唯一结果集
        List list = query.getResultList();
        for (Object o : list) {
            System.out.println(o);
        }

        //4.提交事务（混滚事务）
        tx.commit();
        //5.释放资源
        entityManager.close();
    }
}
