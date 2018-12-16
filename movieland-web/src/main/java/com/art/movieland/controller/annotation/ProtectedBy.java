package com.art.movieland.controller.annotation;

import com.art.movieland.entity.UserRole;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.METHOD)
public @interface ProtectedBy {
    UserRole[] value() default UserRole.USER;
}

