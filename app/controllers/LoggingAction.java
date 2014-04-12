package controllers;

import com.google.gson.Gson;
import models.archives;
import models.logging;
import play.mvc.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 14-4-2.
 */
public class LoggingAction extends Controller {
    public static void select(int page,int rows,String sort,String order,String name){
        int opage;
        int log_size;

        if(name!=null){
            log_size = logging.find("title like '%%" + name + "%%'").fetch().size();
        }else{
            log_size = logging.find("byAccount",session.get("master")).fetch().size();
        }//判断分页区间过界问题

        if((page-1)*rows+rows >log_size){
            opage=log_size;
        }else{
            opage=(page-1)*rows+rows;
        }

        List<logging> p_log;

        //排序
        if(name!=null){
            p_log=logging.find("title like '%%"+name+"%%' ").fetch();
        }else{
            p_log=logging.find("Account=? order by "+sort+" "+order,session.get("master")).from((page-1)*rows).fetch(opage);
        }

        //转换成哈希传递给dategrid获取total
        Map m = new HashMap();
        m.put("total",log_size);
        m.put("rows",p_log);
        renderJSON(new Gson().toJson(m).toString());
    }
}
