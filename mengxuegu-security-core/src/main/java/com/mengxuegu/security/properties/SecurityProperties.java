package com.mengxuegu.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Y_Coffee on 2020/8/21
 *
 * @author CoffeeY
 */
@Data
@Component
@ConfigurationProperties(prefix = "mengxuegu.security")
public class SecurityProperties {

    private AuthenticationProperties authentication;

}
