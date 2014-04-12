package controllers;

import com.google.gson.Gson;
import models.organization;
import play.mvc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 14-3-20.
 */
public class OrganizationAction extends Controller{
    public static void select(int page,int rows,String sort,String order,String name){
        int opage;
        int Organ_size;

        if(name!=null){
            Organ_size = organization.find("name like '%%" + name + "%%'").fetch().size();
        }else{
            Organ_size = organization.findAll().size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >Organ_size){
            opage=Organ_size;
        }else{
            opage=(page-1)*rows+rows;
        }

        List<organization> p_Organ;

        //排序
        if(name!=null){
            p_Organ= organization.find("name like '%%" + name + "%%' ").fetch();
        }else{
            p_Organ= organization.find("order by " + sort + " " + order).from((page-1)*rows).fetch(opage);
        }

        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",Organ_size);
        m.put("rows",p_Organ);
        renderJSON(new Gson().toJson(m).toString());
    }

    public static String organizationadd(String orgname,String orgabbreviation,String phone,String fax,String postcode,String address,String web){
        String result="添加成功";
        organization org = organization.find("byOrgname",orgname).first();
        organization org2 = organization.find("byOrgabbreviation",orgabbreviation).first();
        if (org ==null && org2 == null){
            org = new organization();
            org.orgname = orgname;
            org.orgabbreviation = orgabbreviation;
            org.phone = phone;
            org.fax = fax;
            org.postcode = postcode;
            org.address = address;
            org.web = web;
            org.create();
        }else{
            result="创建失败,机构已存在";
        }
        return result;
    }

    public static String organizationsave(String orgname,String orgabbreviation,String phone,String fax,String postcode,String address,String web){
        String result="添加成功";
        organization org = organization.find("byOrgname",orgname).first();
        org.phone = phone;
        org.fax = fax;
        org.postcode = postcode;
        org.address = address;
        org.web = web;
        org.save();
        return result;
    }

    public static String organizatiodelete(String id){
            String result ="删除成功";
            organization org = organization.find("byId",id).first();
            org.delete();
            return result;
    }
}
