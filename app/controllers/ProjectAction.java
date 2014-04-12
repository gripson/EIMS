package controllers;

import com.google.gson.Gson;
import models.archives;
import models.project;
import models.projectprivilege;
import models.relations;
import play.mvc.Controller;

import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 14-3-30.
 */
public class ProjectAction extends Controller{
    public static void relationcombobox(){
        List<relations> relation = relations.findAll();
        renderJSON(new Gson().toJson(relation).toString());
    }

    public static String projectadd(project pro){
        String result = "添加成功";
        project proj = project.find("byName", pro.name).first();
        if(proj==null){
            proj = new project();
            proj.name= pro.name;
            proj.begindate=pro.begindate;
            proj.classes = pro.classes;
            proj.createdate=pro.createdate;
            proj.customername = pro.customername;
            proj.deadline = pro.deadline;
            proj.creater=pro.creater;
            proj.duration=pro.duration;
            proj.importance=pro.importance;
            proj.satisfaction=pro.satisfaction;
            proj.money=pro.money;
            proj.view=pro.view;
            proj.state=pro.state;
            proj.intro=pro.intro;
            proj.type=pro.type;
            proj.principal=pro.principal;
            proj.auditors=pro.auditors;
            proj.fronttask =pro.fronttask;
            proj.markingtime =pro.markingtime;
            proj.idea=pro.idea;
            if(pro.uploadfile!=null){
                proj.uploadfile=pro.uploadfile;
                proj.filename=pro.filename;
            }
            proj.save();
            //绑定编辑查看权限
            String people =null;
            people=pro.view+", "+pro.auditors+", "+pro.principal+", "+pro.creater;
            System.out.println(people);
            String[] str = people.split(", ");
            Set set = new TreeSet();
            for (int i = 0; i < str.length; i++) {
                set.add(str[i]);
            }
            str = (String[]) set.toArray(new String[0]);
            for (int i = 0; i < str.length; i++) {
                projectprivilege pp =new projectprivilege();
                pp.name=str[i];
                pp.projectid=proj.id;
                pp.create();
            }
        }else{
            result="创建失败,项目已存在";
        }
        return result;
    }

    public static void select(int page,int rows,String sort,String order,String name){
        int opage;
        int project_size;
        //取对象
        List<project> p_project=new ArrayList<project>();
        List<project> subp_project=new ArrayList<project>();
        project uproject=null;
        List<projectprivilege>  pp = projectprivilege.find("byName",session.get("truename")).fetch();
        for (int j=0;j<pp.size();j++)
        {
            uproject = project.findById(pp.get(j).projectid);
            p_project.add(uproject);
        }

        if(name!=null){
            project_size = project.find("name like '%%" + name + "%%'").fetch().size();
        }else{
            project_size = pp.size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >project_size){
            opage=project_size;
        }else{
            opage=(page-1)*rows+rows;
        }


        //排序
        if(name!=null){
            p_project=project.find("name like '%%"+name+"%%' ").fetch();
        }else{
            subp_project=p_project.subList((page-1)*rows,opage);
//            p_project=project.find("order by "+sort+" "+order).from((page-1)*rows).fetch(opage);
        }
        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",project_size);
        m.put("rows",subp_project);
        renderJSON(new Gson().toJson(m).toString());
    }

    public static String projectdelete(String id){
        String result ="删除成功";
        project pro = project.find("byId",id).first();
        if(pro.uploadfile.exists()){
            pro.uploadfile.getFile().delete();
        }
        //删除关联
        List<projectprivilege> opp = projectprivilege.find("byProjectid",id).fetch();
        for (int i = 0; i < opp.size(); i++) {
            opp.get(i).delete();
        }
        pro.delete();
        return result;
    }

    public static void filedownload(String id) throws IOException {
        project pro = project.findById(id);
        renderBinary(pro.uploadfile.get(), pro.filename);
    }

    public static String projectsave(project pro){
        String result = "添加成功";
        project proj = project.find("byName", pro.name).first();
        proj.name= pro.name;
        proj.begindate=pro.begindate;
        proj.classes = pro.classes;
        proj.createdate=pro.createdate;
        proj.customername = pro.customername;
        proj.deadline = pro.deadline;
        proj.creater=pro.creater;
        proj.duration=pro.duration;
        proj.importance=pro.importance;
        proj.satisfaction=pro.satisfaction;
        proj.money=pro.money;
        proj.view=pro.view;
        proj.state=pro.state;
        proj.intro=pro.intro;
        proj.type=pro.type;
        proj.principal=pro.principal;
        proj.auditors=pro.auditors;
        proj.fronttask =pro.fronttask;
        proj.markingtime =pro.markingtime;
        proj.idea=pro.idea;
        if(pro.uploadfile!=null){
            proj.uploadfile=pro.uploadfile;
            proj.filename=pro.filename;
        }
        proj.save();

        //删除原绑定
        List<projectprivilege> opp = projectprivilege.find("byProjectid",proj.id).fetch();
        for (int i = 0; i < opp.size(); i++) {
            opp.get(i).delete();
        }
        //重新绑定编辑查看权限
        String people =null;
        people=pro.view+", "+pro.auditors+", "+pro.principal+", "+pro.creater;
        System.out.println(people);
        String[] str = people.split(", ");
        Set set = new TreeSet();
        for (int i = 0; i < str.length; i++) {
            set.add(str[i]);
        }
        str = (String[]) set.toArray(new String[0]);
        for (int i = 0; i < str.length; i++) {
            projectprivilege pp =new projectprivilege();
            pp.name=str[i];
            pp.projectid=proj.id;
            pp.create();
        }
        return result;
    }
}
