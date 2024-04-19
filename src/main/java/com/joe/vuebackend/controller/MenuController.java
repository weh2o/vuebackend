package com.joe.vuebackend.controller;

import com.joe.vuebackend.service.MenuService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 清單
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Setter(onMethod_ = @Autowired)
    private MenuService menuService;

}
