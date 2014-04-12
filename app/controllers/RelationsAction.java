package controllers;

import com.google.gson.Gson;
import models.archives;
import models.department;
import models.relations;
import play.mvc.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 14-3-27.
 */
public class RelationsAction extends Controller {
    public static String relationsadd(relations rel){
        String result="添加成功";
        relations re = relations.find("byCustomername",rel.customername).first();
        if(re ==null){
            relations relation= new relations();
            relation.affiliation = rel.affiliation;
            relation.age = rel.age;
            relation.belongpeople =rel.belongpeople;
            relation.belongs=rel.belongs;
            relation.city=rel.city;
            relation.prov=rel.prov;
            relation.dist=rel.dist;
            relation.area=rel.prov+" "+rel.city+" "+rel.dist;
            relation.companyprofile=rel.companyprofile;
            relation.concern=rel.concern;
            relation.contactobject = rel.contactobject;
            relation.createdate=new Date();
            relation.creater=rel.creater;
            relation.creditlimit=rel.creditlimit;
            relation.creditworthiness=rel.creditworthiness;
            relation.customertype=rel.customertype;
            relation.customerid=rel.customerid;
            relation.customerreferred=rel.customerreferred;
            relation.customername=rel.customername;
            relation.englishname=rel.englishname;
            relation.customertypeparent=rel.customertypeparent;
            relation.web=rel.web;
            relation.phone=rel.phone;
            relation.telephone=rel.telephone;
            relation.state=rel.state;
            relation.role=rel.role;
            relation.remark=rel.remark;
            relation.relation=rel.relation;
            relation.qq=rel.qq;
            relation.postalcode=rel.postalcode;
            relation.personpostalcode=rel.personpostalcode;
            relation.personemail=rel.personemail;
            relation.email=rel.email;
            relation.parentcompany=rel.parentcompany;
            relation.personaddress=rel.personaddress;
            relation.officephone=rel.officephone;
            relation.persondegree=rel.persondegree;
            relation.highestdegree= rel.highestdegree;
            relation.lifetype = rel.lifetype;
            relation.truename=rel.truename;
            relation.sex=rel.sex;
            relation.departuredate=rel.departuredate;
            relation.position=rel.position;
            relation.fax=rel.fax;
            relation.homephone=rel.homephone;
            relation.qq=rel.qq;
            relation.importance=rel.importance;
            relation.relation=rel.relation;
            relation.takeneed=rel.takeneed;
            relation.interestappeal=rel.interestappeal;
            relation.enterpriseproperty=rel.enterpriseproperty;
            relation.degree=rel.degree;
            relation.industry=rel.industry;
            relation.identifying=rel.identifying;
            relation.joinus=rel.joinus;
            relation.address=rel.address;
            relation.save();
        }else{
            result="已存在此客户";
        }
        return result;
    }


    public static String relationssave(relations rel){
        String result="保存成功";
        relations relation = relations.find("byCustomername",rel.customername).first();
        relation.affiliation = rel.affiliation;
        relation.age = rel.age;
        relation.belongpeople =rel.belongpeople;
        relation.belongs=rel.belongs;
        relation.city=rel.city;
        relation.prov=rel.prov;
        relation.dist=rel.dist;
        relation.area=rel.prov+" "+rel.city+" "+rel.dist;
        relation.companyprofile=rel.companyprofile;
        relation.concern=rel.concern;
        relation.contactobject = rel.contactobject;
        relation.creater=rel.creater;
        relation.creditlimit=rel.creditlimit;
        relation.creditworthiness=rel.creditworthiness;
        relation.customertype=rel.customertype;
        relation.customerid=rel.customerid;
        relation.customerreferred=rel.customerreferred;
        relation.customername=rel.customername;
        relation.englishname=rel.englishname;
        relation.customertypeparent=rel.customertypeparent;
        relation.web=rel.web;
        relation.phone=rel.phone;
        relation.telephone=rel.telephone;
        relation.state=rel.state;
        relation.role=rel.role;
        relation.remark=rel.remark;
        relation.relation=rel.relation;
        relation.qq=rel.qq;
        relation.postalcode=rel.postalcode;
        relation.personpostalcode=rel.personpostalcode;
        relation.personemail=rel.personemail;
        relation.email=rel.email;
        relation.parentcompany=rel.parentcompany;
        relation.personaddress=rel.personaddress;
        relation.officephone=rel.officephone;
        relation.persondegree=rel.persondegree;
        relation.highestdegree= rel.highestdegree;
        relation.lifetype = rel.lifetype;
        relation.truename=rel.truename;
        relation.sex=rel.sex;
        relation.departuredate=rel.departuredate;
        relation.position=rel.position;
        relation.fax=rel.fax;
        relation.homephone=rel.homephone;
        relation.qq=rel.qq;
        relation.importance=rel.importance;
        relation.relation=rel.relation;
        relation.takeneed=rel.takeneed;
        relation.interestappeal=rel.interestappeal;
        relation.enterpriseproperty=rel.enterpriseproperty;
        relation.degree=rel.degree;
        relation.industry=rel.industry;
        relation.identifying=rel.identifying;
        relation.joinus=rel.joinus;
        relation.address=rel.address;
        relation.save();
        return result;
    }

    public static void select(int page,int rows,String sort,String order,String customername){
        int opage;
        int relations_size;

        if(customername!=null){
            relations_size = relations.find("customername like '%%" + customername + "%%' And creater=?",session.get("truename")).fetch().size();
        }else{
            relations_size = relations.find("byCreater",session.get("truename")).fetch().size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >relations_size){
            opage=relations_size;
        }else{
            opage=(page-1)*rows+rows;
        }

        List<relations> p_relations;

        //排序
        if(customername!=null){
            p_relations=relations.find("title like '%%"+customername+"%%' And creater=?",session.get("truename")).fetch();
        }else{
            p_relations=relations.find("creater=? order by "+sort+" "+order,session.get("truename")).from((page-1)*rows).fetch(opage);
        }

        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",relations_size);
        m.put("rows",p_relations);
        renderJSON(new Gson().toJson(m).toString());
    }

    public static String relationsdelete(String id){
        String result ="删除成功";
        relations relation = relations.find("byId",id).first();
        relation.delete();
        return result;
    }
}
