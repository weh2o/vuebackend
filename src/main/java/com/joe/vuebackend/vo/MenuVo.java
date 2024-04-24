package com.joe.vuebackend.vo;

import com.joe.vuebackend.domain.Menu;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 展示到前端的菜單物件
 */
@Data
public class MenuVo {

    /**
     * 識別碼
     */
    private String id;

    /**
     * 英文名稱
     */
    private String name;

    /**
     * 中文名稱
     */
    private String label;

    /**
     * 地址
     */
    private String path;

    /**
     * 完整地址
     */
    private String url;

    /**
     * 圖標
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 子菜單
     */
    private List<MenuVo> children = new ArrayList<>();

    public void addChildren(MenuVo source) {
        if (Objects.isNull(children)) {
            children = new ArrayList<>();
        }
        if (Objects.nonNull(source)) {
            children.add(source);
        }
    }

    public static MenuVo of(Menu source) {
        MenuVo target = new MenuVo();
        // 識別碼
        if (StringUtils.isNotEmpty(source.getId())) {
            target.setId(source.getId());
        }

        // 英文名
        if (StringUtils.isNotEmpty(source.getName())) {
            target.setName(source.getName());
        }

        // 中文名
        if (StringUtils.isNotEmpty(source.getLabel())) {
            target.setLabel(source.getLabel());
        }

        // 地址
        if (StringUtils.isNotEmpty(source.getPath())) {
            target.setPath(source.getPath());
        }

        // 完整地址
        if (StringUtils.isNotEmpty(source.getUrl())) {
            target.setUrl(source.getUrl());
        }

        // 圖標
        if (StringUtils.isNotEmpty(source.getIcon())) {
            target.setIcon(source.getIcon());
        }
        // 排序
        if (Objects.nonNull(source.getSort())) {
            target.setSort(source.getSort());
        }
        return target;
    }
}
