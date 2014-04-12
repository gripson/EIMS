package controllers;

import com.google.gson.Gson;
import models.department;
import models.master;
import models.organization;
import models.role;
import play.mvc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 14-3-20.
 */
public class DepartmentAction extends Controller {
    //用户管理
    public static void select(int page,int rows,String sort,String order,String name){
        int opage;
        int depart_size;

        if(name!=null){
            depart_size = department.find("name like '%%" + name + "%%'").fetch().size();
        }else{
            depart_size = department.findAll().size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >depart_size){
            opage=depart_size;
        }else{
            opage=(page-1)*rows+rows;
        }

        List<master> p_depart;

        //排序
        if(name!=null){
            p_depart=department.find("name like '%%"+name+"%%' ").fetch();
        }else{
            p_depart=department.find("order by "+sort+" "+order).from((page-1)*rows).fetch(opage);
        }

        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",depart_size);
        m.put("rows",p_depart);
        renderJSON(new Gson().toJson(m).toString());
    }

    public static void organizationcombobox(){
        List<organization> organ = organization.findAll();
        renderJSON(new Gson().toJson(organ).toString());
    }

    public static String departmentadd(String orgname,String parentdepartment,String departmentname,String phone,String fax,String address,String responsibility,
                                       String annualobjectives,String planning,String info){
        String result="添加成功";
        department depart = department.find("byDepartmentname",departmentname).first();
        if (depart ==null){
            depart = new department();
            depart.orgname = orgname;
            depart.parentdepartment = parentdepartment;
            depart.departmentname = departmentname;
            depart.phone = phone;
            depart.fax = fax;
            depart.address = address;
            depart.address = address;
            depart.responsibility = responsibility;
            depart.annualobjectives = annualobjectives;
            depart.planning = planning;
            depart.info = info;
            depart.create();
        }else{
            result="创建失败,机构已存在";
        }
        return result;
    }

    public static String departmentdelete(String id){
        String result ="删除成功";
        department deparment = department.find("byId",id).first();
        deparment.delete();
        return result;
    }

    public static String departmentsave(String orgname,String parentdepartment,String departmentname,String phone,String fax,String address,String responsibility,
                                       String annualobjectives,String planning,String info){
        String result="修改成功";
        department depart = department.find("byDepartmentname",departmentname).first();
        depart.orgname = orgname;
        depart.parentdepartment = parentdepartment;
        depart.phone = phone;
        depart.fax = fax;
        depart.address = address;
        depart.address = address;
        depart.responsibility = responsibility;
        depart.annualobjectives = annualobjectives;
        depart.planning = planning;
        depart.info = info;
        depart.save();
        return result;
    }

}
