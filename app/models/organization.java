package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created by Administrator on 14-3-20.
 */
@Entity
@Table(name="Iorganization")
public class organization extends GenericModel {
    //UUID id编号
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //机构简称
    @Required
    public String orgabbreviation;

    //机构名称
    @Required
    public String orgname;

    //电话
    @Required
    public String phone;

    //邮编
    @Required
    public String postcode;

    //网址
    @Required
    public String web;

    //地址
    @Required
    public String address;

    //传真号码
    @Required
    public String fax;
}
