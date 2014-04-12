package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import javax.swing.text.GapContent;

/**
 * Created by Administrator on 14-3-21.
 */
@Entity
@Table(name="Ipermission")
public class permission extends GenericModel {
    //UUID id编号
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //管理人员
    @Required
    public String managerpeople ;

    //授权范围(机构/部门）
    @Required
    public String loa;

    //排除人员
    @Required
    public String excludepeople ;
}
