package controllers;

import models.*;

import play.mvc.*;

import java.io.IOException;
import java.util.Date;


public class Application extends Controller{

    @Before
    static void addUser() {
        master user = connected();
        if(user != null) {
            renderArgs.put("master", user);
        }
    }

    static master connected() {
        if(renderArgs.get("master") != null) {
            return renderArgs.get("master", master.class);
        }
        String name = session.get("master");
        if(name != null) {
            return master.find("byName", name).first();
        }
        return null;
    }

    public static void login(){
        render();
    }

    public static void logincheck(String name, String password,String ip) {
        master user = master.find("byNameAndPassword", name, password).first();
        if(user != null) {
            role rol= role.find("byRolename",user.rolename).first();
            session.put("master", user.name);
            session.put("truename",user.truename);
            session.put("masterid", user.id);
            session.put("employeeaction",rol.employeeaction);
            session.put("customeraction",rol.customeraction);
            session.put("departmentaction",rol.departmentaction);
            session.put("personaction",rol.personaction);
            session.put("projectaction",rol.projectaction);
            session.put("queryaction",rol.queryaction);
            session.put("recordaction",rol.recordaction);
            session.put("useraction",rol.useraction);
            logging log = new logging();
            log.account=user.name;
            log.truename=user.truename;
            log.entertime=new Date();
            log.ip=ip;
            log.create();
            session.put("logid",log.id);
            index();
        }
        // Oops
        flash.error("用户名或密码错误");
        login();
    }

    public static void logout(){
        logging log = logging.findById(session.get("logid"));
        log.endtime=new Date();
        log.save();
        session.clear();
        index();
    }

    public static void index() {
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void center() {
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void north() {
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void west(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void home(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void usermanager(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void usereditpwd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void useradd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void userpermedit(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void userrole(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void userroleadd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void userroleedit(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void employee(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void employeeadd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void employeeedit(String id){
        master user = master.find("byId",id).first();
        render(user);
    }

    public static void employeepermission(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void employeepermissionedit(String id){
        permission per = permission.find("byId",id).first();
        render(per);
    }

    public static void employeepermissionadd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void organization(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void organizationadd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void organizationcheck(String id){
        organization org = organization.find("byId",id).first();
        render(org);
    }

    public static void organizationdepart(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void organizationdepartadd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void organizationdepartcheck(String id){
        department depart = department.find("byId",id).first();
        render(depart);
    }

    public static void archivesmanager(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void archivesmanageradd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void archivesmanageredit(String id){
        archives archive = archives.find("byId",id).first();
        render(archive);
    }

    public static void email(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void filedownload(String id) throws IOException {
        archives archive = archives.findById(id);
        renderBinary(archive.file.get(), archive.filename); 
    }

    public static void relationsadd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void relationsmy(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void relationscheck(String id){
        if(connected() != null) {
            relations relation=relations.find("byId",id).first();
            render(relation);
        }
        login();
    }

    public static void projectadd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void projectmanager(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void projectedit(String id){
        if(connected() != null) {
            project pro = project.find("byId",id).first();
            render(pro);
        }
        login();
    }

    public static void position(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void positionadd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void positioncheck(String id){
        if(connected() != null) {
            position pos=position.find("byId",id).first();
            render(pos);
        }
        login();
    }

    public static void logging(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void receivefiles(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void receivefilesedit(String id){
        archives arc = archives.find("byId",id).first();
        render(arc);
    }

    public static void receivefilesreply(String id){
        archives arc = archives.find("byId",id).first();
        render(arc);
    }

    public static void sendfiles(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void sendfilesadd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void editpwd(){
        if(connected() != null) {
            render();
        }
        login();
    }

    public static void personalinfo(){
        if(connected() != null) {
            String id=session.get("masterid");
            master user = master.findById(id);
            render(user);
        }
        login();
    }

    public static void sendfileedit(String id){
        archives arc = archives.find("byId",id).first();
        render(arc);
    }
}