package models;

import org.hibernate.annotations.GenericGenerator;
import play.data.validation.Required;
import play.db.jpa.GenericModel;

import javax.persistence.*;

/**
 * Created by Administrator on 14-4-3.
 */
@Entity
@Table(name="Iprojectprivilege")
public class projectprivilege extends GenericModel {
    //UUID id编号
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name="id")
    public String id;

    //员工名字
    @Required
    public String name;

    //项目ID
    @Required
    public String projectid;
}
