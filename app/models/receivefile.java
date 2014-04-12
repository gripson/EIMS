package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 14-4-7.
 */
@Entity
@Table(name="Ireceivefile")
public class receivefile extends GenericModel {
    //UUID id编号
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //标题
    @Required
    public String title;

    //副标题
    @Required
    public String subtitle;

    //关键字
    @Required
    public String keyword;

    //简介
    @Required
    public String intro;

    //附件
    @Required
    public String rfile;

    //发件人
    @Required
    public String sender;

    //收件人
    @Required
    public String recperson;

    //创建时间
    @Required
    public Date createdater;

    //附件
    @Required
    public Blob file;

    //附件名字
    @Required
    public String filename;
}
