package com.bd.userusasmspanel.smspanel.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/index")
    public String dasbord(){
        return "normal/user_dasbord";
    }
//    reset password

}
