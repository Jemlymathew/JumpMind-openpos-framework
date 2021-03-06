package org.jumpmind.pos.service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface EndpointOverride {
    String path();
    String value() default "";    
    String implementation() default Endpoint.IMPLEMENTATION_DEFAULT;
}