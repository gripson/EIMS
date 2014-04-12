package models;
import controllers.ArchivesAction;
import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.io.File;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by Administrator on 14-4-7.
 */
@Entity
@Table(name="Isendfiles")
public class sendfiles extends GenericModel {
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

    //发件人
    @Required
    public String sender;

    //收件机构
    @Required
    public String reorangeonly;

    //收件机构
    @Required
    public String reorange;

    //收件部门
    @Required
    public String recdeparment;

    //收件人
    @Required
    public String recperson;

    //创建时间
    @Required
    public Date createdater;

    //简介
    @Required
    public String notes;

    //附件
    @Required
    public Blob file;

    //附件名字
    @Required
    public String filename;
}
