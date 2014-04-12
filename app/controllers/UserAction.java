package controllers;

import com.google.gson.Gson;
import com.thoughtworks.xstream.mapper.Mapper;
import models.department;
import models.master;
import models.position;
import models.role;
import net.sf.ehcache.hibernate.management.impl.BeanUtils;
import org.h2.util.New;
import play.mvc.Controller;

import java.sql.Blob;
import java.util.*;

/**
 * Created by Administrator on 14-3-11.
 */
public class UserAction extends Controller {
    //用户管理
    public static void select(int page,int rows,String sort,String order,String name){
        int opage;
        int master_size;

        if(name!=null){
            master_size = master.find("name like '%%"+name+"%%'").fetch().size();
        }else{
            master_size = master.findAll().size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >master_size){
            opage=master_size;
        }else{
            opage=(page-1)*rows+rows;
        }

        List<master> p_users;

        //排序
        if(name!=null){
           p_users=master.find("name like '%%"+name+"%%' ").fetch();
        }else{
           p_users=master.find("order by "+sort+" "+order).from((page-1)*rows).fetch(opage);
        }

        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",master_size);
        m.put("rows",p_users);
        renderJSON(new Gson().toJson(m).toString());
    }

    public static String editpwd(String id,String pwd){
        String result="修改成功";
        master user = master.find("byId",id).first();
        user.password = pwd;
        user.bdate = new Date();
        user.save();
        return result;
    }

    public static String useradd(String pwd,String username){
        String result="添加成功";
        master user = master.find("byName",username).first();
        if (user ==null){
            user = new master();
            user.password = pwd;
            user.name = username;
            user.createdate = new Date();
            user.bdate = new Date();
            user.create();
        }else{
            result="创建失败,用户名已存在";
        }
        return result;
    }

    public static String inituser(){
        String id;
        master user = new master();
        id = user.id;
        return id;
    }

    public static String userdelete(String id){
        String result ="删除成功";
        master user = master.find("byId",id).first();
        user.delete();
        return result;
    }

    public static String batchDelete(String ids){
        String id;
        String result ="删除成功";
        int length = ids.length();

        while (length>0){
            System.out.println(length);
            id=ids.substring(0,32);
            userdelete(id);
            ids=ids.substring(32,length);
            length-=32;
        }

        return result;
    }


    //角色管理
    public static void selectrole(int page,int rows,String sort,String order,String rolename){
        int opage;
        int role_size;

        if(rolename!=null){
            role_size = role.find("name like '%%" + rolename + "%%'").fetch().size();
        }else{
            role_size = role.findAll().size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >role_size){
            opage=role_size;
        }else{
            opage=(page-1)*rows+rows;
        }

        List<role> p_roles;

        //排序
        if(rolename!=null){
            p_roles=role.find("name like '%%"+rolename+"%%' ").fetch();
        }else{
            p_roles=role.find("order by "+sort+" "+order).from((page-1)*rows).fetch(opage);
        }

        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",role_size);
        m.put("rows",p_roles);
        renderJSON(new Gson().toJson(m).toString());
    }

    public static String roleadd(String rolename,String employee,String department,String permission,String record,String customer,String person,String project,String query){
        String result="添加成功";
        role orole = role.find("byRolename",rolename).first();
        if (orole ==null){
            orole = new role();
            orole.rolename = rolename;
            orole.employeeaction = employee;
            orole.departmentaction = department;
            orole.useraction = permission;
            orole.recordaction = record;
            orole.customeraction = customer;
            orole.personaction = person;
            orole.projectaction = project;
            orole.queryaction = query;
            orole.create();
        }else{
            result="创建失败,角色名已存在";
        }
        return result;
    }

    public static String roledelete(String id){
        String result ="删除成功";
        role Role = role.find("byId",id).first();
        Role.delete();
        return result;
    }

    public static String rolebatchDelete(String ids){
        String id;
        String result ="删除成功";
        int length = ids.length();

        while (length>0){
            System.out.println(length);
            id=ids.substring(0,32);
            roledelete(id);
            ids=ids.substring(32,length);
            length-=32;
        }

        return result;
    }

    public static String roleedit(String rolename,String employee,String department,String permission,String record,String customer,String person,String project,String query){
        String result="修改成功";
        role orole = role.find("byRolename",rolename).first();
        orole.rolename = rolename;
        orole.employeeaction =employee;
        orole.departmentaction=department;
        orole.personaction=person;
        orole.recordaction=record;
        orole.customeraction=customer;
        orole.useraction =permission;
        orole.projectaction=project;
        orole.queryaction=query;
        orole.save();
        return result;
    }

    public static String userpermedit(String name,String role){
        String result="修改成功";
        master user = master.find("byName",name).first();
        user.rolename = role;
        user.bdate = new Date();
        user.save();
        return result;
    }

    public static void rolecombobox(){
        List<role> orole = role.findAll();
        renderJSON(new Gson().toJson(orole).toString());
    }

    //-------------------------------------------------------------------员工管理------------------------------------------------------------------------------------
   public static String employeeadd(String name,String password,String truename,String IDnumber,String nativeplace,String filenumber,String useraccount,String employeeid,
                                   String wagenumber,String securityno,String sex,Date birthday,String age,String birthplace,
                                   String nation,String household,String householdnature, String highestdegree,String school,String usercreate,
                                   String maritalstatus,String employeestatus,String nationality, String IDype,String politicsstatus,String healthcondition,
                                   String highestdiploma,String professionaltitle,String specialty,Date userdate,String employeesstate,
                                   String employeetype,Date hiredate,String homeaddress,String postcode,String homephone,String email,
                                   String otherphone,String mobile,String department,String orgname,String departmentname,Blob photo)
    {
        String result="保存成功";
        master omaster = master.find("byName",name).first();
        if (omaster ==null){
            omaster = new master();
            omaster.name = name;
            omaster.password = password;
            omaster.truename = truename;
            omaster.IDnumber = IDnumber;
            omaster.nativeplace = nativeplace;
            omaster.filenumber=filenumber;
            omaster.useraccount = useraccount;
            omaster.employeeid = employeeid;
            omaster.wagenumber=wagenumber;
            omaster.securityno = securityno;
            omaster.sex=sex;
            omaster.brithday = birthday;
            omaster.age=age;
            omaster.birthplace=birthplace;
            omaster.nation=nation;
            omaster.household=household;
            omaster.householdnature=householdnature;
            omaster.highestdegree=highestdegree;
            omaster.school=school;
            omaster.usercreate=usercreate;
            omaster.maritalstatus=maritalstatus;
            omaster.employeestatus=employeestatus;
            omaster.nationality=nationality;
            omaster.IDype=IDype;
            omaster.politicsstatus=politicsstatus;
            omaster.healthcondition=healthcondition;
            omaster.highestdiploma=highestdiploma;
            omaster.professionaltitle=professionaltitle;
            omaster.specialty=specialty;
            omaster.userdate=userdate;
            omaster.employeesstate=employeesstate;
            omaster.employeetype=employeetype;
            omaster.hiredate=hiredate;
            omaster.homeaddress=homeaddress;
            omaster.postcode=postcode;
            omaster.homephone=homephone;
            omaster.email=email;
            omaster.otherphone=otherphone;
            omaster.mobile=mobile;
            omaster.createdate=new Date();
            omaster.orgname=orgname;
            omaster.department= department;
            omaster.departmentname=departmentname;
            omaster.photo= (play.db.jpa.Blob) photo;
            omaster.save();
        }else{
            result="创建失败,用户名已存在";
        }
        return result;
    }

    public static String employeeedit(String name,String password,String truename,String IDnumber,String nativeplace,String filenumber,String useraccount,String employeeid,
                                      String wagenumber,String securityno,String sex,Date birthday,String age,String birthplace,
                                      String nation,String household,String householdnature, String highestdegree,String school,String usercreate,
                                      String maritalstatus,String employeestatus,String nationality, String IDype,String politicsstatus,String healthcondition,
                                      String highestdiploma,String professionaltitle,String specialty,Date userdate,String employeesstate,
                                      String employeetype,Date hiredate,String homeaddress,String postcode,String homephone,String email,
                                      String otherphone,String mobile,String department,String orgname,String departmentname,Blob photo)
    {
        String result="保存成功";
        master omaster = master.find("byName",name).first();
        if (omaster !=null){
            omaster.name = name;
            omaster.password = password;
            omaster.truename = truename;
            omaster.IDnumber = IDnumber;
            omaster.nativeplace = nativeplace;
            omaster.filenumber=filenumber;
            omaster.useraccount = useraccount;
            omaster.employeeid = employeeid;
            omaster.wagenumber=wagenumber;
            omaster.securityno = securityno;
            omaster.sex=sex;
            omaster.brithday = birthday;
            omaster.age=age;
            omaster.birthplace=birthplace;
            omaster.nation=nation;
            omaster.household=household;
            omaster.householdnature=householdnature;
            omaster.highestdegree=highestdegree;
            omaster.school=school;
            omaster.usercreate=usercreate;
            omaster.maritalstatus=maritalstatus;
            omaster.employeestatus=employeestatus;
            omaster.nationality=nationality;
            omaster.IDype=IDype;
            omaster.politicsstatus=politicsstatus;
            omaster.healthcondition=healthcondition;
            omaster.highestdiploma=highestdiploma;
            omaster.professionaltitle=professionaltitle;
            omaster.specialty=specialty;
            omaster.userdate=userdate;
            omaster.employeesstate=employeesstate;
            omaster.employeetype=employeetype;
            omaster.hiredate=hiredate;
            omaster.homeaddress=homeaddress;
            omaster.postcode=postcode;
            omaster.homephone=homephone;
            omaster.email=email;
            omaster.otherphone=otherphone;
            omaster.mobile=mobile;
            omaster.createdate=new Date();
            omaster.orgname=orgname;
            omaster.department= department;
            omaster.departmentname=departmentname;
            omaster.photo= (play.db.jpa.Blob) photo;
            omaster.save();
        }else{
            result="修改失败,员工不存在";
        }
        return result;
    }
}
