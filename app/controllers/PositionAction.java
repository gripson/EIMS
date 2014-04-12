package controllers;

import com.google.gson.Gson;
import models.department;
import models.master;
import models.position;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 14-3-31.
 */
public class PositionAction  extends Controller{
    public static void select(int page,int rows,String sort,String order,String name){
        int opage;
        int posi_size;

        if(name!=null){
            posi_size = position.find("name like '%%" + name + "%%'").fetch().size();
        }else{
            posi_size = position.findAll().size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >posi_size){
            opage=posi_size;
        }else{
            opage=(page-1)*rows+rows;
        }

        List<position> p_posi=new ArrayList<position>();
        p_posi=position.findAll();

        for(int i=0;i<posi_size;i++){
            p_posi.get(i).strength = master.find("orgname=? and department=? and departmentname=?",p_posi.get(i).orgname,p_posi.get(i).department,p_posi.get(i).departmentname).fetch().size()+"";
            p_posi.get(i).save();
        }
        //排序
        if(name!=null){
            p_posi=position.find("name like '%%"+name+"%%' ").fetch();
        }else{
            p_posi=position.find("order by "+sort+" "+order).from((page-1)*rows).fetch(opage);
        }

        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",posi_size);
        m.put("rows",p_posi);

        renderJSON(new Gson().toJson(m).toString());
    }

    public static void deparmentcombobox(String orgname){
        List<department> departments = department.find("byOrgname", orgname).fetch();
        renderJSON(new Gson().toJson(departments).toString());
    }

    public static void deparmentallcombobox(){
        List<department> departments = department.findAll();
        renderJSON(new Gson().toJson(departments).toString());
    }

    public static void departmentnameallcombobox(){
        List<position> positions = position.findAll();
        renderJSON(new Gson().toJson(positions).toString());
    }

    public static void deparmentnamecombobox(String departments,String orgname){
        List<position> positions =null;
        if (departments!=null && orgname!=null){
               positions = position.find("byOrgnameAndDepartment", orgname,departments).fetch();
        }
        renderJSON(new Gson().toJson(positions).toString());
    }

    public static String positionadd(position pos){
        String result="添加成功";
        position positions = position.find("byDepartmentname",pos.departmentname).first();
        if (positions ==null){
            positions = new position();
            positions.orgname = pos.orgname;
            positions.communicate = pos.communicate;
            positions.departmentname = pos.departmentname;
            positions.communicate_cycle=pos.communicate_cycle;
            positions.department=pos.department;
            positions.description=pos.description;
            positions.duty=pos.duty;
            positions.higherposition=pos.higherposition;
            positions.level=pos.level;
            positions.manage=pos.manage;
            positions.required=pos.required;
            positions.staffnum=pos.staffnum;
            positions.system_admin=pos.system_admin;
            positions.system_Clerk=pos.system_Clerk;
            positions.system_develop=pos.system_develop;
            positions.system_head=pos.system_head;
            positions.system_manage=pos.manage;
            positions.system_market=pos.system_market;
            positions.system_personnel=pos.system_personnel;
            positions.system_secondhead=pos.system_secondhead;
            positions.system_special=pos.system_special;
            positions.workshift=pos.workshift;
            positions.workshift_cycle=pos.workshift_cycle;
            if (pos.system_admin!=null){
               positions.system=pos.system_admin;
            }else{
               positions.system="";
            }
            if (pos.system_Clerk!=null)
                positions.system=positions.system+" "+pos.system_Clerk;
            if (pos.system_develop!=null)
                positions.system=positions.system+" "+pos.system_develop;
            if (pos.system_head!=null)
                positions.system=positions.system+" "+pos.system_head;
            if (pos.system_market!=null)
                positions.system=positions.system+" "+pos.system_market;
            if (pos.system_personnel!=null)
                positions.system=positions.system+" "+pos.system_personnel;
            if (pos.system_secondhead!=null)
                positions.system=positions.system+" "+pos.system_secondhead;
            if (pos.system_special!=null)
                positions.system=positions.system+" "+pos.system_special;
            positions.create();
        }else{
            result="创建失败,机构已存在";
        }
        return result;
    }

    public static String positionsave(position pos){
        String result="修改成功";
        position positions = position.find("byDepartmentname",pos.departmentname).first();
        positions.orgname = pos.orgname;
        positions.communicate = pos.communicate;
        positions.departmentname = pos.departmentname;
        positions.communicate_cycle=pos.communicate_cycle;
        positions.department=pos.department;
        positions.description=pos.description;
        positions.duty=pos.duty;
        positions.higherposition=pos.higherposition;
        positions.level=pos.level;
        positions.manage=pos.manage;
        positions.required=pos.required;
        positions.staffnum=pos.staffnum;
        positions.system_admin=pos.system_admin;
        positions.system_Clerk=pos.system_Clerk;
        positions.system_develop=pos.system_develop;
        positions.system_head=pos.system_head;
        positions.system_manage=pos.manage;
        positions.system_market=pos.system_market;
        positions.system_personnel=pos.system_personnel;
        positions.system_secondhead=pos.system_secondhead;
        positions.system_special=pos.system_special;
        positions.workshift=pos.workshift;
        positions.workshift_cycle=pos.workshift_cycle;
        if (pos.system_admin!=null){
            positions.system=pos.system_admin;
        }else{
            positions.system="";
        }
        if (pos.system_Clerk!=null)
            positions.system=positions.system+" "+pos.system_Clerk;
        if (pos.system_develop!=null)
            positions.system=positions.system+" "+pos.system_develop;
        if (pos.system_head!=null)
            positions.system=positions.system+" "+pos.system_head;
        if (pos.system_market!=null)
            positions.system=positions.system+" "+pos.system_market;
        if (pos.system_personnel!=null)
            positions.system=positions.system+" "+pos.system_personnel;
        if (pos.system_secondhead!=null)
            positions.system=positions.system+" "+pos.system_secondhead;
        if (pos.system_special!=null)
            positions.system=positions.system+" "+pos.system_special;
            positions.save();
        return result;
    }

    public static String positiondelete(String id){
        String result ="删除成功";
        position posi = position.find("byId",id).first();
        posi.delete();
        return result;
    }
}
