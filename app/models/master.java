package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 14-3-11.
 */
@Entity
@Table(name="Imaster")
public class master  extends GenericModel {
    //UUID id编号
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //用户名称
    @Required
    public String name;

    //用户密码
    @Required
    public String password;

    //最终修改时间
    @Required
    public Date bdate;

    //真实姓名
    @Required
    public String truename;

    //性别
    @Required
    public String sex;

    //生日
    @Required
    public Date brithday;

    //所属部门
    @Required
    public String dept;

    //职务
    @Required
    public String position;

    //职务说明
    @Required
    public String positiondesc;

    //办公室电话
    @Required
    public String officephone;

    //手机
    @Required
    public String mobile;

    //家庭电话
    @Required
    public String homephone;

    //电子邮件
    @Required
    public String email;

    //家庭住址
    @Required
    public String address;

    //邮编
    @Required
    public String postcode;

    //个人照片
    @Required
    public Blob photo;

    //创建者id
    @Required
    public String masterid;

    //创建者名称
    @Required
    public String mastername;

    //创建日期
    @Required
    public Date createdate;

    //角色
    @Required
    public String rolename;

    //状态
    @Required
    public String status;

    //入职时间
    @Required
    public Date hiredate;

    //证件号码
    @Required
    public String IDnumber;

    //籍贯
    @Required
    public String nativeplace;

    //档案编号
    @Required
    public String filenumber;

    //用户帐号
    @Required
    public String useraccount;

    //员工编号
    @Required
    public String employeeid;

    //工资卡号
    @Required
    public String wagenumber;

    //出生地址
    @Required
    public String birthplace;

    //户口
    @Required
    public String household;

    //婚姻状况
    @Required
    public String maritalstatus;

    //国籍
    @Required
    public String nationality;

    //政治面貌
    @Required
    public String politicsstatus;

    //最高学位
    @Required
    public String highestdiploma ;

    //职称
    @Required
    public String professionaltitle;

    //社保号
    @Required
    public String securityno;

    //年龄
    @Required
    public String age;

    //民族
    @Required
    public String nation;

    //户口性质
    @Required
    public String householdnature;

    //员工身份
    @Required
    public String employeestatus;

    //证件类型
    @Required
    public String IDype;

    //健康状况
    @Required
    public String healthcondition;

    //最高学历
    @Required
    public String highestdegree;

    //毕业院校
    @Required
    public String school;

    //专业
    @Required
    public String specialty;

    //人员档案创建人
    @Required
    public String usercreate;

    //人员档案创建时间
    @Required
    public Date userdate;

    //员工状态
    @Required
    public String employeesstate;

    //员工类型
    @Required
    public String employeetype ;

    //家庭地址
    @Required
    public String homeaddress ;

    //其他电话
    @Required
    public String otherphone;

    //所在机构
    @Required
    public String orgname;

    //所在部门
    @Required
    public String department;

    //职位
    @Required
    public String departmentname;
}
