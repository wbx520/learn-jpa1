package com.wbx.domain;/*
 *@author 王炳祥
 *@createTime
 * 客户实体类
 *      配置映射关系：
 *  1.实体类和表的映射关系
 *  @Entity：声明这是一个实体类
 *  @Table：配置实体类和表的映射关系
 *      name：配置数据库表的名称
 *  2.实体类中属性和表中字段的映射关系
 *
 *
 */

import javax.persistence.*;


@Entity
@Table(name = "jpa")
public class Customer {

    /**
     *  @Id:声明主键的配置
     *  @GeneratedValue：配置主键的生成策略
     *      GenerationType.IDENTITY：自增（底层数据库必须支持自动增长，比如mysql）
     *      GenerationType.SEQUENCE：序列（底层数据库必须支持序列，比如Oracle）
     *      GenerationType.TABLE：jpa提供的一种机制，通过一张数据表的的形式帮助我们完成主键自增
     *      GenerationType.AUTO：由程序自动的帮我们选择主键生成策略
     *  @Column:配置属性和表中字段的映射关系
     *      name：数据库表中字段的名称
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "uname")
    private String uname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", uname='" + uname + '\'' +
                '}';
    }
}
