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

    private String codeImage = "/code/image";

    private String mobilePage = "/mobile/page";

    private String codeMobile = "/code/mobile";

    private String mobileForm = "/mobile/form";

    private String userLogout = "/user/logout";
    /**
     * 设置默认值，防止在没写入情况下出错
     */
    private Integer tokenValiditySeconds = 60 * 60 * 24 * 7;

    private String[] staticPaths = {"/dist/**", "/modules/**", "/plugins/**"};

    private LoginResponseType loginType = LoginResponseType.REDIRECT;


}
