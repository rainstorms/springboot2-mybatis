package mythware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mythware.dao.TestDao;
import mythware.domain.User;
import mythware.service.TestService;


@RestController
@RequestMapping("/hello")
public class ThemeController {

    @Autowired TestDao dao;
    @Autowired TestService service;

    @RequestMapping("/world")
    public String helloWorld() {
        User s = dao.selectUser("123456");
        return s.getUserName();
    }

    @RequestMapping("/world2")
    public void helloWorld2() {
//        service.addTest1("3");
    }
}
