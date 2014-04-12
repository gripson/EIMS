package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 14-3-27.
 */
@Entity
@Table(name="Irelations")
public class relations extends GenericModel{
    //UUID id编号
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //客户标识
    @Required
    public String identifying;

    //创建人
    @Required
    public String creater;

    //客户编号
    @Required
    public String customerid;

    //客户简称
    @Required
    public String customerreferred;

    //客户名称
    @Required
    public String customername;

    //英文名
    @Required
    public String englishname;

    //客户类型1
    @Required
    public String customertypeparent;

    //客户类型2
    @Required
    public String customertype;

    //地区
    @Required
    public String address;

    //所属客户
    @Required
    public String belongs;

    //所属人
    @Required
    public String belongpeople;

    //总机电话
    @Required
    public String phone;

    //电子邮件
    @Required
    public String email;

    //邮政编码
    @Required
    public String postalcode;

    //网址
    @Required
    public String web;

    //行业
    @Required
    public String industry;

    //认可程度
    @Required
    public String degree;

    //企业性质
    @Required
    public String enterpriseproperty;

    //上级公司
    @Required
    public String parentcompany;

    //信誉度
    @Required
    public String creditworthiness;

    //信用额度
    @Required
    public String highestdegree;

    //赊销天数
    @Required
    public String creditlimit;

    //隶属关系
    @Required
    public String affiliation;

    //公司简介
    @Required
    public String companyprofile;

    //创建时间
    @Required
    public Date createdate;

    //年龄
    @Required
    public String age;

    //状态
    @Required
    public String state;

    //离职日期
    @Required
    public Date departuredate;

    //职位
    @Required
    public String position;

    //角色
    @Required
    public String role;

    //办公电话
    @Required
    public String officephone;

    //手机
    @Required
    public String telephone;

    //传真号码
    @Required
    public String fax;

    //电子邮件
    @Required
    public String personemail;

    //家庭电话
    @Required
    public String homephone;

    //MSN(QQ)
    @Required
    public String qq;

    //地址
    @Required
    public String personaddress;

    //邮政编码
    @Required
    public String personpostalcode;

    //认可程度
    @Required
    public String persondegree;

    //重要程度
    @Required
    public String importance;

    //关系
    @Required
    public String relation;

    //联系对象
    @Required
    public String contactobject;

    //关心重点
    @Required
    public String concern;

    //利益诉求
    @Required
    public String interestappeal;

    //备注
    @Required
    public String remark;

    //性别
    @Required
    public String sex;

    //加入
    @Required
    public String joinus;

    //生活类型
    @Required
    public String lifetype;

    //姓名
    @Required
    public String truename;

    //负责的需求
    @Required
    public String takeneed;

    //省份
    @Required
    public String  prov;

    //地区（县）
    @Required
    public String dist;

    //地区
    @Required
    public String area;

    //城市
    @Required
    public String city;
}
