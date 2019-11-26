package com.wbx.utils;/*
 *@author 王炳祥
 *@createTime
 * 解决实体类工厂的浪费资源和耗时问题，（b站av号53910024）
 *      通过静态代码块的形式，当程序第一次访问此工具类时，创建一个公共的实体管理器工厂对象
 * 第一次访问getEntityManager方法：经过静态代码块创建一个factory对象，在调用方法创建一个EntityManager对象
 * 第二次访问getEntityManager方法：直接通过一个已经创建好的factory对象，创建EntityManager对象
 */

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class jpaUtils {

    private static EntityManagerFactory factory;

    static {
        //1.加载配置文件，创建EntityManagerFactory
        factory = Persistence.createEntityManagerFactory("jpa");
    }

    //获取EntityManager对象
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
