package com.bd.userusasmspanel.smspanel.Controller;

import com.bd.userusasmspanel.smspanel.Entity.User;
import com.bd.userusasmspanel.smspanel.Repo.Userrepo;
import com.bd.userusasmspanel.smspanel.Services.UserEmailServises;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class FrogotEmailcontroller {
    @Autowired
    private UserEmailServises emailServises;
    @Autowired
    private Userrepo userrepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    Random random=new Random(1000);
    @PostMapping("/change-password")
    public String otpsend(@RequestParam("email")String email, HttpSession session) throws MessagingException {
        System.out.println("email:"+email);

       int otp= random.nextInt(999999);
        System.out.println("otp:"+otp);
        String subject="OTP FROM Volp None Number";
        String Massage="Your OTP is: "+otp ;
        String to=email;


        boolean b= this.emailServises.MailSender(subject,Massage,to);

        if (b){
            session.setAttribute("oldotp",otp);
            session.setAttribute("email",email);
            return "Otp-From";
        }
        else {
            session.setAttribute("massage","Chack Your Email Id!!");
           return "normal/restfrom";
        }

    }

    @PostMapping("/verfy-password")
    public String OtpVerify(@RequestParam("otp") int otp ,HttpSession session){
          int oldotp=(int) session.getAttribute("oldotp");
          String email=(String) session.getAttribute("email");
          if (oldotp==otp){
             User user= this.userrepo.getSmspanelByEmail(email);
             if (user==null){
                 session.setAttribute("massage","email id not valid");
                 return "normal/restfrom";
             }
             else {

             }
               return "password-change-from";
          }
          else {
              session.setAttribute("massage","Your Otp Not Valid");
              return "Otp-From";
          }
    }
  @PostMapping("/Change/password")
    public String changefrom(@RequestParam("new_password")String new_password,HttpSession session){
      String email=(String) session.getAttribute("email");
      User user=this.userrepo.getSmspanelByEmail(email);
      user.setPassword(this.bCryptPasswordEncoder.encode(new_password));
      this.userrepo.save(user);
      return "redirect:/login";
    }
}
