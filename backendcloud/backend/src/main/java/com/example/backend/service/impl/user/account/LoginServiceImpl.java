package com.example.backend.service.impl.user.account;

import com.example.backend.pojo.User;
import com.example.backend.service.impl.utils.UserDetailsImpl;
import com.example.backend.service.user.account.LoginService;
import com.example.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;  //身份验证API

    @Override
    public Map<String, String> getToken(String username, String password) {
        //如何从AuthenticationManager获得用户的数据信息，核心代码:
        //将登陆时输入的用户名和密码封装一个类，并对密码加密
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        //登陆失败会自动处理,如果没有返回异常和null值，那么验证服务便是完成
        //该方法会去调用UserDetailsServiceImpl.loadUserByUsername,查找数据库从而完成验证服务
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //获得用户信息，即UserDetails对象
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        User user = loginUser.getUser();
        String jwt = JwtUtil.createJWT(user.getId().toString());

        //能走到这里说明已经成功了
        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");
        map.put("token", jwt);

        return map;
    }
}
