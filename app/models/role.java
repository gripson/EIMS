package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created by Administrator on 14-3-17.
 */
@Entity
@Table(name="Irole")
public class role extends GenericModel {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //角色名称
    @Required
    public String rolename;

    //角色信息
    @Required
    public String roleinfo;

    //员工管理模块权限
    @Required
    public String employeeaction;

    //部门模块权限
    @Required
    public String departmentaction;

    //权限管理模块权限
    @Required
    public String useraction;

    //档案管理模块权限
    @Required
    public String recordaction ;

    //客户关系模块权限
    @Required
    public String customeraction ;

    //个人管理模块权限
    @Required
    public String personaction;

    //项目管理模块权限
    @Required
    public String projectaction;

    //查询统计模块权限
    @Required
    public String queryaction;
}
