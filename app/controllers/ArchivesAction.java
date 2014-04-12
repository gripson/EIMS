package controllers;

import com.google.gson.Gson;
import models.archives;

import models.department;
import play.mvc.Controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 14-3-21.
 */
public class ArchivesAction extends Controller{
    public static void select(int page,int rows,String sort,String order,String name){
        int opage;
        int archives_size;

        if(name!=null){
            archives_size = archives.find("title like '%%" + name + "%%' And title!=?","").fetch().size();
        }else{
            archives_size = archives.find("title!=?","").fetch().size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >archives_size){
            opage=archives_size;
        }else{
            opage=(page-1)*rows+rows;
        }

        List<archives> p_archives;

        //排序
        if(name!=null){
            p_archives=archives.find("title like '%%"+name+"%%' And title!=?","").fetch();
        }else{
            p_archives=archives.find("title!=? order by "+sort+" "+order,"").from((page-1)*rows).fetch(opage);
        }

        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",archives_size);
        m.put("rows",p_archives);
        renderJSON(new Gson().toJson(m).toString());
    }

    public static String archivesadd(archives arc) throws IOException {
        String result="添加成功";
        archives archive = archives.find("byTitle",arc.title).first();
        if (archive ==null){
            archive = new archives();
            archive.nominated = arc.nominated;
            archive.archivetype = arc.archivetype;
            archive.fnsorttable = arc.fnsorttable;
            archive.fileno = arc.fileno;
            archive.title = arc.title;
            archive.filetype = arc.filetype;
            archive.positioned = arc.positioned;
            archive.boxno = arc.boxno;
            archive.subheading = arc.subheading;
            archive.keyword = arc.keyword;
            archive.referenceno = arc.referenceno;
            archive.author=arc.author;
            archive.securitylevel=arc.securitylevel;
            archive.storagelife=arc.storagelife;
            archive.degree=arc.degree;
            archive.page=arc.page;
            archive.senddepartment=arc.senddepartment;
            archive.dispatchdepartment=arc.dispatchdepartment;
            archive.notes=arc.notes;
            archive.createrdate=new Date();
            if(arc.file!=null){
                archive.file=arc.file;
                archive.filename=arc.filename;
            }
            archive.create();
        }else{
            result="创建失败,题名已存在";
        }
        return result;
    }

    public static String archivesdelete(String id){
        String result ="删除成功";
        archives archive = archives.find("byId",id).first();
        if(archive.file.exists()){
            archive.file.getFile().delete();
        }
        archive.delete();
        return result;
    }

    public static String archivesedit(archives arc) throws IOException {
        String result="修改成功";
        archives archive = archives.find("byTitle",arc.title).first();
        archive.nominated = arc.nominated;
        archive.archivetype = arc.archivetype;
        archive.fnsorttable = arc.fnsorttable;
        archive.fileno = arc.fileno;
        archive.title = arc.title;
        archive.filetype = arc.filetype;
        archive.positioned = arc.positioned;
        archive.boxno = arc.boxno;
        archive.subheading = arc.subheading;
        archive.keyword = arc.keyword;
        archive.referenceno = arc.referenceno;
        archive.author=arc.author;
        archive.securitylevel=arc.securitylevel;
        archive.storagelife=arc.storagelife;
        archive.degree=arc.degree;
        archive.page=arc.page;
        archive.senddepartment=arc.senddepartment;
        archive.dispatchdepartment=arc.dispatchdepartment;
        archive.notes=arc.notes;
        archive.createrdate=new Date();
        if(arc.file!=null){
            archive.file=arc.file;
            archive.filename=arc.filename;
        }
        System.out.println(arc.file);
        archive.save();
        return result;
    }
}
