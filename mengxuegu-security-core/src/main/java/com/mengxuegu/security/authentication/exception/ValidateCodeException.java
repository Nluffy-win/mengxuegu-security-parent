package com.mengxuegu.security.authentication.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author CoffeeY
 * @Classname ValidateCodeExcetipn * @Description TODO * @Date 2020/8/23 20:38 * @Created by John
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
