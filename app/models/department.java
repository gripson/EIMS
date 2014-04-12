package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created by Administrator on 14-3-20.
 */
@Entity
@Table(name="Idepartment")
public class department extends GenericModel {
    //UUID id编号
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //所属机构
    @Required
    public String orgname;

    //上级部门
    @Required
    public String parentdepartment;

    //部门名称
    @Required
    public String departmentname;

    //传真号码
    @Required
    public String fax;

    //地址
    @Required
    public String address;

    //部门职责
    @Required
    public String responsibility;

    //年度目标
    @Required
    public String annualobjectives;

    //任务计划
    @Required
    public String planning;

    //办公电话
    @Required
    public String phone;

    //简介
    @Required
    public String info;

}
