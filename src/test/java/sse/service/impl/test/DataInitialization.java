package sse.service.impl.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import sse.entity.Administrator;
import sse.entity.Menu;
import sse.entity.Student;
import sse.entity.SystemMessage;
import sse.entity.Teacher;
import sse.entity.TimeNode;
import sse.entity.Topic;
import sse.entity.User;
import sse.entity.Will;
import sse.enums.MatchLevelEnum;
import sse.enums.MatchTypeEnum;
import sse.enums.TimeNodeTypeEnum;
import sse.enums.TopicStatusEnum;
import sse.enums.TopicTypeEnum;

public class DataInitialization extends BaseJPATest {

    public int fbn(String name)
    {
        return ((User) (em.createQuery("select u from User u where u.name=:name").setParameter("name", name)
                .getResultList().get(0))).getId();
    }

    public Teacher fbnTeacher(String name)
    {
        return (Teacher) (em.createQuery("select t from Teacher t where t.name=:name").setParameter("name", name)
                .getResultList().get(0));
    }

    public Student fbnStudent(String name)
    {
        return (Student) (em.createQuery("select s from Student s where s.name=:name").setParameter("name", name)
                .getResultList().get(0));
    }

    public int menufbn(String name)
    {
        return ((Menu) (em.createQuery("select m from Menu m where m.name=:name").setParameter("name", name)
                .getResultList().get(0))).getId();
    }

    public int menufbn(String name, String role)
    {
        return ((Menu) (em.createQuery("select m from Menu m where m.name=:name and m.role=:role")
                .setParameter("name", name)
                .setParameter("role", role)
                .getResultList().get(0))).getId();
    }

    @Test
    public void test() {
        System.out.println("Start");
        beginTransaction();
        String p = "Initial0";
        Student yuesongWang = new Student(1, "1152754", "王岳松", p);
        em.persist(yuesongWang);
        Student leiYang = new Student(2, "1152755", "杨磊", p);
        em.persist(leiYang);
        Student JunyaoZhao = new Student(3, "1152756", "赵俊尧", p);
        em.persist(JunyaoZhao);
        Student ZenanHu = new Student(4, "1152757", "胡泽南", p);
        em.persist(ZenanHu);
        Student BoyiLi = new Student(5, "1152759", "李博一", p);
        em.persist(BoyiLi);
        Student YifanZhang = new Student(6, "1152760", "张一帆", p);
        em.persist(YifanZhang);

        Teacher yanLiu = new Teacher(7, "1252753", "刘岩", p, 3);
        em.persist(yanLiu);
        Teacher pingSun = new Teacher(8, "1252754", "孙萍", p, 2);
        em.persist(pingSun);
        Teacher dongQingWang = new Teacher(9, "1252755", "王冬青", p, 5);
        em.persist(dongQingWang);
        Teacher changQingYin = new Teacher(10, "1252756", "尹长青", p, 2);
        em.persist(changQingYin);
        Teacher meihuiLi = new Teacher(11, "1252757", "李美惠", p, 2);
        em.persist(meihuiLi);

        Administrator admin = new Administrator(12, "admin", "admin", "admin");
        em.persist(admin);
        commitTransaction();
        
        beginTransaction();
        List<Will> willList = new ArrayList<Will>();
        // Yuesong Wang
        willList.add(new Will(fbnStudent("王岳松"), fbnTeacher("刘岩"), 1));

        willList.add(new Will(fbnStudent("王岳松"), fbnTeacher("孙萍"), 2));
        willList.add(new Will(fbnStudent("王岳松"), fbnTeacher("王冬青"), 3));
        // LeiYang
        willList.add(new Will(fbnStudent("杨磊"), fbnTeacher("李美惠"), 1));
        willList.add(new Will(fbnStudent("杨磊"), fbnTeacher("尹长青"), 2));
        // JunYao zhao
        willList.add(new Will(fbnStudent("赵俊尧"), fbnTeacher("刘岩"), 1));
        willList.add(new Will(fbnStudent("赵俊尧"), fbnTeacher("尹长青"), 2));
        willList.add(new Will(fbnStudent("赵俊尧"), fbnTeacher("孙萍"), 3));
        // Zenan hu
        willList.add(new Will(fbnStudent("胡泽南"), fbnTeacher("刘岩"), 1));
        willList.add(new Will(fbnStudent("胡泽南"), fbnTeacher("孙萍"), 2));
        willList.add(new Will(fbnStudent("胡泽南"), fbnTeacher("王冬青"), 3));
        // Boyi li
        willList.add(new Will(fbnStudent("李博一"), fbnTeacher("孙萍"), 1));
        willList.add(new Will(fbnStudent("李博一"), fbnTeacher("王冬青"), 2));
        willList.add(new Will(fbnStudent("李博一"), fbnTeacher("刘岩"), 3));
        // Yifan Zhang
        willList.add(new Will(fbnStudent("张一帆"), fbnTeacher("刘岩"), 1));
        willList.add(new Will(fbnStudent("张一帆"), fbnTeacher("孙萍"), 2));
        willList.add(new Will(fbnStudent("张一帆"), fbnTeacher("李美惠"), 3));

        for (Will w : willList)
            em.persist(w);

        commitTransaction();
        
        beginTransaction();
        yuesongWang.setTeacher(yanLiu);
        yuesongWang.setMatchLevel(MatchLevelEnum.第一志愿);
        yuesongWang.setMatchType(MatchTypeEnum.系统分配);
        em.merge(yuesongWang);
        commitTransaction();
        
        beginTransaction();
        List<Menu> menus = new ArrayList<Menu>();
        commitTransaction();
        beginTransaction();
        em.persist(new Menu("选择导师", null, "Student", ""));
        menus.add(new Menu("所有老师", menufbn("选择导师"), "Student",
                "http://localhost:8080/sse/dispatch/student/student_all_teachers"));
        menus.add(new Menu("填报志愿", menufbn("选择导师"), "Student",
                "http://localhost:8080/sse/dispatch/student/student_select_will"));
        menus.add(new Menu("我的老师", menufbn("选择导师"), "Student",
                "http://localhost:8080/sse/dispatch/student/student_my_teacher"));
        for (Menu m : menus)
            em.persist(m);
        commitTransaction();

        beginTransaction();
        menus = new ArrayList<Menu>();
        em.persist(new Menu("毕设相关", null, "Student", ""));
        menus.add(new Menu("选题申请", menufbn("毕设相关"), "Student",
                "http://localhost:8080/sse/dispatch/student/student_select_topic"));
        menus.add(new Menu("任务书", menufbn("毕设相关"), "Student",
                "http://localhost:8080/sse/dispatch/student/student_renwushu"));
        menus.add(new Menu("开题报告", menufbn("毕设相关"), "Student",
                "http://localhost:8080/sse/dispatch/student/student_kaitibaogao"));
        menus.add(new Menu("最终论文", menufbn("毕设相关"), "Student",
                "http://localhost:8080/sse/dispatch/student/student_zuizhonglunwen"));

        for (Menu m : menus)
            em.persist(m);
        commitTransaction();
        
        beginTransaction();
        menus = new ArrayList<Menu>();
        em.persist(new Menu("个人中心", null, "Student", ""));
        commitTransaction();
        
        beginTransaction();
        menus.add(new Menu("个人信息", menufbn("个人中心"), "Student",
                "http://localhost:8080/sse/dispatch/student/student_personal_center"));

        for (Menu m : menus)
            em.persist(m);
        commitTransaction();

        beginTransaction();
        menus = new ArrayList<Menu>();
        em.persist(new Menu("志愿", null, "Administrator", ""));
        commitTransaction();
        
        beginTransaction();
        menus.add(new Menu("志愿表", menufbn("志愿"), "Administrator",
                "http://localhost:8080/sse/dispatch/administrator/admin_list_will"));
        menus.add(new Menu("分配志愿", menufbn("志愿"), "Administrator",
                "http://localhost:8080/sse/dispatch/administrator/admin_match_will"));
        for (Menu m : menus)
        {
            em.persist(m);
        }
        commitTransaction();

        beginTransaction();
        menus = new ArrayList<Menu>();
        em.persist(new Menu("信息管理", null, "Administrator", ""));
        commitTransaction();
        
        beginTransaction();
        menus.add(new Menu("学生管理", menufbn("信息管理"), "Administrator",
                "http://localhost:8080/sse/dispatch/administrator/admin_maintain_student"));
        menus.add(new Menu("教师管理", menufbn("信息管理"), "Administrator",
                "http://localhost:8080/sse/dispatch/administrator/admin_maintain_teacher"));
        menus.add(new Menu("文档管理", menufbn("信息管理"), "Administrator",
                "http://localhost:8080/sse/dispatch/administrator/admin_match_will"));
        for (Menu m : menus)
        {
            em.persist(m);
        }
        commitTransaction();

        beginTransaction();
        menus = new ArrayList<Menu>();
        em.persist(new Menu("公告与日程", null, "Administrator", ""));
        commitTransaction();
        
        beginTransaction();
        menus.add(new Menu("公告管理", menufbn("公告与日程"), "Administrator",
                "http://localhost:8080/sse/dispatch/administrator/admin_list_messages"));
        menus.add(new Menu("日程节点", menufbn("公告与日程"), "Administrator",
                "http://localhost:8080/sse/dispatch/administrator/admin_set_timenode"));
        for (Menu m : menus)
        {
            em.persist(m);
        }
        commitTransaction();
        
        beginTransaction();
        menus = new ArrayList<Menu>();
        em.persist(new Menu("学生相关", null, "Teacher", ""));
        commitTransaction();
        
        beginTransaction();
        menus.add(new Menu("选择学生", menufbn("学生相关"), "Teacher",
                "http://localhost:8080/sse/dispatch/teacher/teacher_select_student"));
        menus.add(new Menu("我的学生", menufbn("学生相关"), "Teacher",
                "http://localhost:8080/sse/dispatch/teacher/teacher_my_students"));
        em.persist(new Menu("选题相关", null, "Teacher", ""));
        menus.add(new Menu("批准选题", menufbn("选题相关"), "Teacher",
                "http://localhost:8080/sse/dispatch/teacher/teacher_review_topic"));

        em.persist(new Menu("个人中心", null, "Teacher", ""));
        menus.add(new Menu("个人信息", menufbn("个人中心", "Teacher"), "Teacher",
                "http://localhost:8080/sse/dispatch/teacher/teacher_personal_center"));
        for (Menu m : menus)
        {
            em.persist(m);
        }
        commitTransaction();

        beginTransaction();
        // Topic
        Topic t = new Topic(1, "测试主选题", "测试副选题", "测试主内容", null, TopicStatusEnum.待审核, "不错！", TopicTypeEnum.个人选题);
        yuesongWang.setTopic(t);
        em.merge(t);
        commitTransaction();
        
        
        // Time nodes
        beginTransaction();
        List<TimeNode> timeNodes = new ArrayList<TimeNode>();
        timeNodes.add(new TimeNode(TimeNodeTypeEnum.关键, "志愿填报", new Date(), "志愿填报时间"));
        timeNodes.add(new TimeNode(TimeNodeTypeEnum.关键, "课题申报", addTime(new Date(), 1), "课题申报时间"));
        timeNodes.add(new TimeNode(TimeNodeTypeEnum.关键, "毕设进行", addTime(new Date(), 2), "毕设进行时间"));
        timeNodes
                .add(new TimeNode(TimeNodeTypeEnum.关键, "答辩申请", addTime(new Date(), 3), "答辩申请时间"));
        for (TimeNode m : timeNodes)
        {
            em.persist(m);
        }

        // Time nodes
        List<SystemMessage> systemMessages = new ArrayList<SystemMessage>();
        systemMessages.add(new SystemMessage(1, "测试公告", "测试公告测试公告测试公告测试公告测试公告"));
        for (SystemMessage m : systemMessages)
        {
            em.persist(m);
        }
        commitTransaction();
        System.out.println("Finished");
    }

    public static Date addTime(Date date, int minute)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }
}
