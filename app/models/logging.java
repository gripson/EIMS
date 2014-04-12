package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 14-4-2.
 */
@Entity
@Table(name="Ilogging")
public class logging extends GenericModel{
    //UUID id编号
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //账号
    @Required
    public String account;

    //姓名
    @Required
    public String truename;

    //登录时间
    @Required
    public Date entertime;

    //退出时间
    @Required
    public Date endtime;

    //IP地址
    @Required
    public String ip;
}
