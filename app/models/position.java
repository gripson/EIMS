package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created by Administrator on 14-3-31.
 */
@Entity
@Table(name="Iposition")
public class position extends GenericModel{
    //UUID id编号
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //所属部门
    @Required
    public String department;

    //所属机构
    @Required
    public String orgname;

    //职位名称
    @Required
    public String departmentname;

    //管理职位
    @Required
    public String manage;

    //上级职位
    @Required
    public String higherposition;

    //职位级别
    @Required
    public String level;

    //编制人数
    @Required
    public String staffnum;

    //在职人数
    @Required
    public String strength;

    //所属体系
    @Required
    public String system;

    //所属体系_行政
    @Required
    public String system_admin;

    //所属体系_人事
    @Required
    public String system_personnel;

    //所属体系_管理
    @Required
    public String system_manage;

    //所属体系_处长
    @Required
    public String system_head;

    //所属体系_市场
    @Required
    public String system_market;

    //所属体系_副处长
    @Required
    public String system_secondhead;

    //所属体系_特殊
    @Required
    public String system_special;

    //所属体系_科员
    @Required
    public String system_Clerk;

    //所属体系_开发
    @Required
    public String system_develop;

    //需定期轮岗
    @Required
    public String workshift;

    //需定期轮岗周期
    @Required
    public String workshift_cycle;

    //需定期交流
    @Required
    public String communicate;

    //需定期交流周期
    @Required
    public String communicate_cycle;

    //职位描述
    @Required
    public String description;

    //岗位职责
    @Required
    public String duty;

    //任职要求
    @Required
    public String required;
}
