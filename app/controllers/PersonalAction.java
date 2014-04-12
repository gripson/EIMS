package controllers;

import com.google.gson.Gson;
import com.ning.http.client.FilePart;
import com.thoughtworks.xstream.mapper.Mapper;
import models.archives;
import models.master;
import models.receivefile;
import models.psendfile;
import play.db.jpa.Blob;
import play.mvc.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 14-4-7.
 */
public class PersonalAction  extends Controller{
    public static void select(int page,int rows,String sort,String order,String keyword){
        int opage;
        int archives_size;

        if(keyword!=null){
            archives_size = archives.find("keyword like '%%" + keyword + "%%' And recperson=? And creater=?",session.get("truename"),"").fetch().size();
        }else{
            archives_size = archives.find("byRecpersonAndCreater",session.get("truename"),"").fetch().size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >archives_size){
            opage=archives_size;
        }else{
            opage=(page-1)*rows+rows;
        }

        List<archives> p_archives;

        //排序
        if(keyword!=null){
            p_archives=archives.find("keyword like '%%"+keyword+"%%' And recperson=? And creater=?",session.get("truename"),"").fetch();
        }else{
            p_archives=archives.find("recperson=? And creater=? order by "+sort+" "+order,session.get("truename"),"").from((page-1)*rows).fetch(opage);
        }

        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",archives_size);
        m.put("rows",p_archives);
        renderJSON(new Gson().toJson(m).toString());
    }

    public static void sendselect(int page,int rows,String sort,String order,String keyword){
        int opage;
        int archives_size;

        if(keyword!=null){
            archives_size = archives.find("keyword like '%%" + keyword + "%%' And creater=?",session.get("truename")).fetch().size();
        }else{
            archives_size = archives.find("byCreater",session.get("truename")).fetch().size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >archives_size){
            opage=archives_size;
        }else{
            opage=(page-1)*rows+rows;
        }

        List<archives> p_archives;

        //排序
        if(keyword!=null){
            p_archives=archives.find("keyword like '%%"+keyword+"%%' And creater=?",session.get("truename")).fetch();
        }else{
            p_archives=archives.find("creater=? order by "+sort+" "+order,session.get("truename")).from((page-1)*rows).fetch(opage);
        }

        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",archives_size);
        m.put("rows",p_archives);
        renderJSON(new Gson().toJson(m).toString());
    }

    public static String sfileadd(archives arc) throws IOException {
        String result="发送成功";
        archives archive = archives.find("byKeyword",arc.keyword).first();
        if (archive ==null){
            archive = new archives();
            archive.ptitle = arc.title;
            archive.createdater=new Date();
            archive.keyword=arc.keyword;
            archive.subtitle=arc.subtitle;
            archive.recperson=arc.recperson;
            archive.creater=session.get("truename");
            archive.recdeparment=arc.recdeparment;
            archive.reorange=arc.reorange;
            archive.reorangeonly=arc.reorangeonly;
            archive.notes=arc.notes;
            archive.sender=session.get("truename");
            archive.file= arc.file;
            archive.filename=arc.filename;
            archive.create();

            if(arc.reorangeonly!=null){
                List<master> person = master.find("orgname=? And truename<>?",arc.reorangeonly,session.get("truename")).fetch();
                if(person!=null){
                    for(int i=0;i<person.size();i++){
                        archives a = new archives();
                        a.reorangeonly=arc.reorangeonly;
                        a.recperson=person.get(i).truename;
                        a.ptitle = arc.title;
                        a.createdater=new Date();
                        a.keyword=arc.keyword;
                        a.subtitle=arc.subtitle;
                        a.notes=arc.notes;
                        a.sender=session.get("truename");
                        a.file= arc.file;
                        a.filename=arc.filename;
                        a.creater="";
                        a.create();
                    }
                }
            }

            if(arc.reorange!=null && arc.recdeparment!=null){
                List<master> person = master.find("orgname=? And department=? And truename<>?",arc.reorangeonly,arc.recdeparment,session.get("truename")).fetch();
                if(person!=null){
                    for(int i=0;i<person.size();i++){
                        archives a = new archives();
                        a.recdeparment=arc.recdeparment;
                        a.reorange=arc.reorange;
                        a.recperson=person.get(i).truename;
                        a.ptitle = arc.title;
                        a.createdater=new Date();
                        a.keyword=arc.keyword;
                        a.subtitle=arc.subtitle;
                        a.notes=arc.notes;
                        a.sender=session.get("truename");
                        a.file= arc.file;
                        a.filename=arc.filename;
                        a.creater="";
                        a.create();
                    }
                }
            }

            if(!arc.recperson.isEmpty()){
//                List<master> person = master.find("truename=? And truename!=?",session.get("truename"),arc.recperson).fetch();
                List<master> person = master.find("truename=?",arc.recperson).fetch();
                if(person!=null){
                    for(int i=0;i<person.size();i++){
                        archives a = new archives();
                        a.reorangeonly=arc.reorangeonly;
                        a.recperson=person.get(i).truename;
                        a.ptitle = arc.title;
                        a.createdater=new Date();
                        a.keyword=arc.keyword;
                        a.subtitle=arc.subtitle;
                        a.notes=arc.notes;
                        a.sender=session.get("truename");
                        a.file= arc.file;
                        a.filename=arc.filename;
                        a.creater="";
                        a.create();
                    }
                }
            }
        }else{
            result="发送失败,关键字已存在";
        }
        return result;
    }

    public static String editpwd(master u){
       String id = session.get("masterid");
       master user = master.findById(id);
       user.password = u.password;
       user.save();
       String result ="修改成功";
       return result;
    }

    public static String senddelete(String id){
        String result ="删除成功";
        archives archive = archives.find("byId",id).first();
        if(archive.file.exists()){
            archive.file.getFile().delete();
        }
        archive.delete();
        return result;
    }

    public static String receivedelete(String id){
        String result ="删除成功";
        archives archive = archives.find("byId",id).first();
        if(archive.file.exists()){
            archive.file.getFile().delete();
        }
        archive.delete();
        return result;
    }

    public static String sfilereply(archives arc) throws IOException {
        String result="发送成功";
        archives archive = archives.find("byKeyword",arc.keyword).first();
        if (archive ==null){
            archive = new archives();
            archive.ptitle = arc.title;
            archive.createdater=new Date();
            archive.keyword=arc.keyword;
            archive.subtitle=arc.subtitle;
            archive.recperson=arc.recperson;
            archive.creater=session.get("truename");
            archive.notes=arc.notes;
            archive.sender=session.get("truename");
            archive.file= arc.file;
            archive.filename=arc.filename;
            archive.checkinfo=arc.checkinfo;
            archive.create();

            if(!arc.recperson.isEmpty()){
//                List<master> person = master.find("truename=? And truename!=?",session.get("truename"),arc.recperson).fetch();
                List<master> person = master.find("truename=?",arc.recperson).fetch();
                if(person!=null){
                    for(int i=0;i<person.size();i++){
                        archives a = new archives();
                        a.reorangeonly=arc.reorangeonly;
                        a.recperson=person.get(i).truename;
                        a.ptitle = arc.title;
                        a.createdater=new Date();
                        a.keyword=arc.keyword;
                        a.subtitle=arc.subtitle;
                        a.notes=arc.notes;
                        a.sender=session.get("truename");
                        a.file= arc.file;
                        a.filename=arc.filename;
                        a.checkinfo=arc.checkinfo;
                        a.creater="";
                        a.create();
                    }
                }
            }
        }else{
            result="发送失败,关键字已存在";
        }
        return result;
    }
}
