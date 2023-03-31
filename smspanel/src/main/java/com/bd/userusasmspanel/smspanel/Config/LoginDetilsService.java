package com.bd.userusasmspanel.smspanel.Config;

import com.bd.userusasmspanel.smspanel.Entity.User;
import com.bd.userusasmspanel.smspanel.Repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LoginDetilsService implements UserDetailsService {
    @Autowired
    private Userrepo smspanelrepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User smspanel = smspanelrepo.getSmspanelByEmail(username);
      if(smspanel==null){
          throw   new UsernameNotFoundException("user not found");
      }
  CustomSmspanel customSmspanel=new CustomSmspanel(smspanel);
        return customSmspanel;
    }
}
