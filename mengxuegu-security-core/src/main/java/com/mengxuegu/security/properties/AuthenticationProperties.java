package com.mengxuegu.security.properties;

import lombok.Data;

/**
 * Created by Y_Coffee on 2020/8/21
 *
 * @author CoffeeY
 */
@Data
public class AuthenticationProperties {


    private String loginPage = "/login/page";
    private String loginProcessingUrl = " /login/form";
    private String usernameParameter = "name";
    private String passwordParameter = "pwd";
    private String loginImage = "/code/image";
    private String loginMoblie = "/mobile/page";
    private String codeMobile = "/code/mobile";
    private String loginForm = "/login/form";
    private String[] staticPaths = {"/dist/**", "/modules/**", "/plugins/**"};
    private LoginResponseType loginType = LoginResponseType.REDIRECT;

}
