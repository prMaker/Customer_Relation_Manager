package com.kaishengit.controller;

import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.dto.JSONResult;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Inject
    private UserService userService;

    @RequestMapping(value = "/usermanager",method = RequestMethod.GET)
    public String userMageger(Model model){
        List<Role> roleList = userService.findAllRole();
        model.addAttribute("roleList",roleList);
        return "admin/adminlist";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public String userNew(User user){
        userService.saveUser(user);
        return "success";
    }

    @RequestMapping(value = "/username",method = RequestMethod.GET)
    @ResponseBody
    public String checkUsername(String username){
        User user = userService.findUserByUsername(username);
        if(user == null){
            return "true";
        }
        return "false";
    }

    @RequestMapping(value = "/usermanager/data",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<User> userData(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String keyword = request.getParameter("search[value]");

        List<User> userList = userService.findUserByParam(keyword,start,length);
        Long countTotal = userService.countTotal();
        Long countParam = userService.countParam(keyword);

        return new DataTablesResult<>(draw,userList,countTotal,countParam);

    }


}
