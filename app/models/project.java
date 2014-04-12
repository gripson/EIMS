package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created by Administrator on 14-3-30.
 */
@Entity
@Table(name="Iproject")
public class project extends GenericModel {
    //UUID id编号
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //名称
    @Required
    public String name;

    //重要程度
    @Required
    public String importance ;

    //类别
    @Required
    public String classes;

    //开始日期
    @Required
    public String begindate ;

    //工期
    @Required
    public String duration ;

    //实施满意程度
    @Required
    public String satisfaction ;

    //客户名称
    @Required
    public String customername;

    //简介
    @Required
    public String intro;

    //负责人
    @Required
    public String principal;

    //授权查看
    @Required
    public String view;

    //类型
    @Required
    public String type;

    //结束日期
    @Required
    public String deadline;

    //状态
    @Required
    public String state;

    //金额
    @Required
    public String money;

    //创建人
    @Required
    public String creater;

    //创建时间
    @Required
    public String createdate;

    //上传文件
    @Required
    public Blob uploadfile;

    //上传文件
    @Required
    public String filename;

    //已经执行天数
    @Required
    public int executeddays;

    //超期天数
    @Required
    public int extendeddays;

    //项目归属
    @Required
    public String fronttask;

    //审核人
    @Required
    public String auditors;

    //批阅时间
    @Required
    public String markingtime;

    //意见
    @Required
    public String idea;
}
