package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

/**
 * Created by Administrator on 14-3-21.
 */
@Entity
@Table(name="Iarchives")
public class archives extends GenericModel {
    //UUID id编号
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //题名
    @Required
    public String title;

    //归档号
    @Required
    public String fileno;

    //文件类型
    @Required
    public String filetype;

    //案卷提名
    @Required
    public String nominated;

    //盒号
    @Required
    public String boxno;

    //档案类别
    @Required
    public String archivetype ;

    //创建者
    @Required
    public String creater;

    //摆放位置
    @Required
    public String positioned;

    //类别号
    @Required
    public String fnsorttable;

    //创建日期
    @Required
    public Date createrdate;

    //副标题 000
    @Required
    public String subheading;

    //关键词
    @Required
    public String keyword;

    //文号
    @Required
    public String referenceno;

    //责任者
    @Required
    public String author;

    //密级
    @Required
    public String securitylevel;

    //保存期限
    @Required
    public String storagelife;

    //紧急程度
    @Required
    public String degree;

    //页数
    @Required
    public String page;

    //来文单位
    @Required
    public String senddepartment;

    //发文单位
    @Required
    public String dispatchdepartment;

    //备注
    @Required
    public String notes;

    //文件
    @Required
    public Blob file;

    //文件名字
    @Required
    public String filename;

    //个人文件标题
    @Required
    public String ptitle;

    //个人文件副标题
    @Required
    public String subtitle;

    //个人文件发送者
    @Required
    public String sender;

    //个人文件仅所属部门
    @Required
    public String reorangeonly;

    //个人文件所属机构
    @Required
    public String reorange;

    //个人文件所属部门
    @Required
    public String recdeparment;

    //个人文件标题
    @Required
    public String recperson;

    //个人文件创建时间
    @Required
    public Date createdater;

    //个人文件审核
    @Required
    public String checkinfo;
}
