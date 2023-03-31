package com.bd.userusasmspanel.smspanel.Controller;

import com.bd.userusasmspanel.smspanel.Entity.User;
import com.bd.userusasmspanel.smspanel.Repo.Userrepo;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Userrepo smspanelrepo;

//all page controller

    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("title","Home USA SmsPanel");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("title","About USA SmsPanel");
        return "about";
    }


    @RequestMapping("/login")
    public String LogIn(Model model){
        model.addAttribute("title","LoginUSA SmsPanel");
        return "login";
    }


    @RequestMapping("/signup")
    public String SignUp(Model model){
        model.addAttribute("title","SignUp USA SmsPanel");
        model.addAttribute("smspanel",new User());
        return "signup";
    }
//    resetpassget

    @GetMapping("/resetpassword")
    public String resetpass(Model model){
        model.addAttribute("title","Reset USA SmsPanel");
        return "normal/restfrom";
    }

//    handeler to signup
//
//    @PostMapping("signup")
//    public String SignUpuser(User smspanel){
//        smspanel.setRole("ROLE_USER");
//           smspanel.setPassword(bCryptPasswordEncoder.encode(smspanel.getPassword()));
//            smspanelrepo.save(smspanel);
//            return "/normal/user_dasbord";
//      }
//      @PostMapping("/change-password")
//      public String changepass(@RequestParam("email")String email,@RequestParam("oldpassword")
//                                     String oldpassword, @RequestParam
//              ("newpassword")String newpassword, Principal principal
//      , HttpSession session){
//          System.out.println(" your email"+email);
//          System.out.println("Old Password"+oldpassword);
//          System.out.println("New Password"+newpassword);
//        String Username=principal.getName();
//        User currentuser=smspanelrepo.getSmspanelByEmail(Username);
//
//          if(this.bCryptPasswordEncoder.matches(oldpassword,currentuser.getPassword())){
//              currentuser.setPassword(this.bCryptPasswordEncoder.encode(newpassword));
//              this.smspanelrepo.save(currentuser);
//          }
//        else {
//            return "normal/restfrom";
//        }
//        return "login";
//      }



}
