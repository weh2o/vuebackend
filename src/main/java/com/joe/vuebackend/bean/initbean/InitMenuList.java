package com.joe.vuebackend.bean.initbean;

import com.joe.vuebackend.bean.InitMenuBean;
import com.joe.vuebackend.constant.RoleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化菜單的資料
 */
public class InitMenuList {
    public static Map<String, List<InitMenuBean>> getAll() {
        HashMap<String, List<InitMenuBean>> target = new HashMap<>();
        target.put("Home", getHome());
        target.put("SystemManagement", getSystemManagement());
        target.put("PersonalManagement", getPersonalManagement());
        target.put("Other", getOther());
        return target;
    }


    /**
     * 其他
     *
     * @return
     */
    public static List<InitMenuBean> getOther() {
        List<InitMenuBean> target = new ArrayList<>();
        target.add(InitMenuBean.builder()
                .label("課程管理")
                .path("/course")
                .icon("Memo")
                .roleNames(
                        List.of(RoleType.ADMIN.getText(),
                                RoleType.TEACHER.getText(),
                                RoleType.STUDENT.getText()
                        )
                )
                .build());
        return target;
    }


    /**
     * 人員管理。排序3
     *
     * @return
     */
    public static List<InitMenuBean> getPersonalManagement() {
        List<InitMenuBean> target = new ArrayList<>();
        String mainMenuPath = "/personalManagement";
        target.add(InitMenuBean.builder()
                .label("人員管理")
                .path(mainMenuPath)
                .icon("User")
                .sort(3)
                .build());

        target.add(InitMenuBean.builder()
                .label("教師管理")
                .path("/teacherManagement")
                .icon("Avatar")
                .sort(1)
                .roleNames(List.of(RoleType.ADMIN.getText()))
                .parent(mainMenuPath)
                .build());

        target.add(InitMenuBean.builder()
                .label("學生管理")
                .path("/studentManagement")
                .icon("Avatar")
                .sort(2)
                .roleNames(
                        List.of(RoleType.ADMIN.getText(),
                                RoleType.TEACHER.getText()
                        )
                )
                .parent(mainMenuPath)
                .build());
        return target;
    }

    /**
     * 系統管理。排序2
     *
     * @return
     */
    public static List<InitMenuBean> getSystemManagement() {
        List<InitMenuBean> target = new ArrayList<>();
        String mainMenuPath = "/system";
        target.add(InitMenuBean.builder()
                .label("系統管理")
                .path(mainMenuPath)
                .icon("Setting")
                .sort(2)
                .build());

        target.add(InitMenuBean.builder()
                .parent(mainMenuPath)
                .label("公告管理")
                .path("/announcement")
                .icon("Tickets")
                .sort(3)
                .roleNames(List.of(RoleType.ADMIN.getText()))
                .build());
        return target;
    }

    /**
     * 首頁。排序1
     */
    public static List<InitMenuBean> getHome() {
        List<InitMenuBean> target = new ArrayList<>();
        String mainMenuPath = "/home";
        target.add(InitMenuBean.builder()
                .label("首頁")
                .path(mainMenuPath)
                .icon("House")
                .sort(0)
                .roleNames(RoleType.getAllText())
                .build());
        return target;
    }
}
