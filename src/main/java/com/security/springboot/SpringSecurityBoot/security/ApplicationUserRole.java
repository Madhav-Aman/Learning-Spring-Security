package com.security.springboot.SpringSecurityBoot.security;

import com.google.common.collect.Sets;
import com.security.springboot.SpringSecurityBoot.student.Student;

import java.util.Set;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            ApplicationUserPermission.STUDENT_READ,
            ApplicationUserPermission.COURSE_WRITE,
            ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.STUDENT_WRITE
    ));

    private Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions){
        this.permissions=permissions;
    }
}
