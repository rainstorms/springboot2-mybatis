package rain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rain.TestDao1;
import rain.dao.TestDao;
import rain.domain.User;

@RestController
@RequestMapping("/hello")
public class HelloWorld {

    @Autowired TestDao dao;
    @Autowired TestDao1 dao1;

    @RequestMapping("/world")
    public String f() {
        User s = dao.selectUser("123456");
        String s1 = dao1.selectUser("123456");
        return s.getUserName() + s1;
    }
}
