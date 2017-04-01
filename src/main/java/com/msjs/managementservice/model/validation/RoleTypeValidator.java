package com.msjs.managementservice.model.validation;

import com.msjs.managementservice.model.Role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleTypeValidator implements ConstraintValidator<RoleType, String> {

    @Override
    public void initialize(RoleType roleType) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.equals(Role.ROLE_ADMIN.toString()) || s.equals(Role.ROLE_USER.toString());
    }
}
