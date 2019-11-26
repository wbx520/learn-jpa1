package com.wbx.domain;/*
 *@author 王炳祥
 *@createTime
 */

import com.wbx.utils.jpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaTest {
    /**
     * 测试jpa的保存
     *      案例：保存一个客户到数据库中
     *  jpa的操作步骤：
     *      1.加载配置文件创建工厂（实体管理类工厂）对象
     *      2.通过实体管理类工厂获得实体管理器
     *      3.获取事务对象，开启事务
     *      4.完成CROD
     *      5.提交事务（混滚事务）
     *      6.释放资源
     */
    @Test
    public void testSave(){
////        1.加载配置文件创建工厂（实体管理类工厂）对象
        //每次都会根据xml配置文件对表进行create/update操作，要注意，如果xml中是create，那查询会报错
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa");
//        //2.通过实体管理类工厂获得实体管理器
//        EntityManager entityManager = factory.createEntityManager();

        EntityManager entityManager=jpaUtils.getEntityManager();
        //3.获取事务对象，开启事务
        EntityTransaction tx = entityManager.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //4.保存一个客户到数据库中
        Customer customer = new Customer();
        customer.setUname("余彩莲");
        //保存
        entityManager.persist(customer);//保存操作
        //5.提交事务（混滚事务）
        tx.commit();
        //6.释放资源
        entityManager.close();

//        factory.close();//使用工具类创建的factory是公共的，关闭后其他线程无法使用
    }


    /**
     * 根据id查询
     * find查询
     *      1.查询的对象就是当前客户本身
     *      2.在调用find方法时，就会发送sql语句查询数据库
     *
     */

    @Test
    public void testFind(){

        //1.通过工具类获取EntityManager
        EntityManager entityManager=jpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        EntityTransaction tx = entityManager.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //3.根据id查询
        Customer customer = entityManager.find(Customer.class,1);
        System.out.println(customer);
        //5.提交事务（混滚事务）
        tx.commit();
        //6.释放资源
        entityManager.close();

//        factory.close();//使用工具类创建的factory是公共的，关闭后其他线程无法使用
    }

    /**
     * 根据id查询
     * getReference方法
     *      1.获取的对象是一个动态代理的对象
     *      2.调用getReference方法不会立即发送sql语句
     *          当调用查询结果对象时，才会发送查询的sql语句：什么时候用，什么时候发送sql
     *  延迟加载（懒加载）
     *      得到一个动态代理对象
     *      什么时候用，什么时候才会查询
     *
     */

    @Test
    public void testReference(){

        //1.通过工具类获取EntityManager
        EntityManager entityManager=jpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        EntityTransaction tx = entityManager.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //3.根据id查询
        Customer customer = entityManager.getReference(Customer.class,1);
        System.out.println(customer);
        //4.提交事务（混滚事务）
        tx.commit();
        //5.释放资源
        entityManager.close();

//        factory.close();//使用工具类创建的factory是公共的，关闭后其他线程无法使用
    }

    /**
     * 删除
     */
    @Test
    public void testRemove(){

        //1.通过工具类获取EntityManager
        EntityManager entityManager=jpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        EntityTransaction tx = entityManager.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //3.根据id查询
        Customer customer = entityManager.getReference(Customer.class,1);
        //4.删除
        entityManager.remove(customer);
        //5.提交事务（混滚事务）
        tx.commit();
        //6.释放资源
        entityManager.close();

//        factory.close();//使用工具类创建的factory是公共的，关闭后其他线程无法使用
    }

    /**
     * 更新
     */
    @Test
    public void testMerge(){

        //1.通过工具类获取EntityManager
        EntityManager entityManager=jpaUtils.getEntityManager();
        //2.获取事务对象，开启事务
        EntityTransaction tx = entityManager.getTransaction();//获取事务对象
        tx.begin();//开启事务
        //3.根据id查询
        Customer customer = entityManager.getReference(Customer.class,2);
        //4.更新
        customer.setUname("爱小余");
        entityManager.merge(customer);

        //5.提交事务（混滚事务）
        tx.commit();
        //6.释放资源
        entityManager.close();
    }
}
