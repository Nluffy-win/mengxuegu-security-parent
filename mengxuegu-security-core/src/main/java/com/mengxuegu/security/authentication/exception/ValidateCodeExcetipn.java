package com.mengxuegu.security.authentication.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Classname ValidateCodeExcetipn * @Description TODO * @Date 2020/8/23 20:38 * @Created by John
 */
public class ValidateCodeExcetipn extends AuthenticationException {
    public ValidateCodeExcetipn(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeExcetipn(String msg) {
        super(msg);
    }
}
