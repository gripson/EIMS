package controllers;


import com.google.gson.Gson;
import models.*;
import play.mvc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 14-3-21.
 */
public class PermissionAction extends Controller {
    public static void select(int page,int rows,String sort,String order,String name){
        int opage;
        int per_size;

        if(name!=null){
            per_size = permission.find("name like '%%" + name + "%%'").fetch().size();
        }else{
            per_size = permission.findAll().size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >per_size){
            opage=per_size;
        }else{
            opage=(page-1)*rows+rows;
        }

        List<permission> p_pers;

        //排序
        if(name!=null){
            p_pers=permission.find("name like '%%"+name+"%%' ").fetch();
        }else{
            p_pers=permission.find("order by "+sort+" "+order).from((page-1)*rows).fetch(opage);
        }

        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",per_size);
        m.put("rows",p_pers);
        renderJSON(new Gson().toJson(m).toString());
    }

    public static void usercombobox(){
        List<master> user = master.findAll();
        renderJSON(new Gson().toJson(user).toString());
    }

    public static void orgcombobox(){
        List<organization> orga = organization.findAll();
        renderJSON(new Gson().toJson(orga).toString());
    }

    public static String permissionadd(String loa,String managerpeople,String excludepeople){
        String result="添加成功";
        permission perm= new permission();
        perm.loa = loa;
        perm.managerpeople = managerpeople;
        perm.excludepeople = excludepeople;
        perm.save();
        return result;
    }

    public static String permissionsave(String loa,String managerpeople,String excludepeople,String id){
        String result="添加成功";
        permission perm= permission.findById(id);
        perm.loa = loa;
        perm.managerpeople = managerpeople;
        perm.excludepeople = excludepeople;
        perm.save();
        return result;
    }

    public static String permissiondelete(String id){
        String result ="删除成功";
        permission perm = permission.find("byId",id).first();
        perm.delete();
        return result;
    }
}
