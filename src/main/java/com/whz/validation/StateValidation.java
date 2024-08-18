package com.whz.validation;

import com.whz.annotation.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author MyPC
 * @description
 * @since 2024/8/18 14:56
 */
// ConstraintValidator<给哪个注解提供校验规则, 检验的数据类型>
public class StateValidation implements ConstraintValidator<State, String> {

    /**
     * @description
     *
     * @param value 将来要校验的数据
     * @param context context in which the constraint is evaluated
     * @return false 校验失败，true 校验成功
     * @date 2024/8/18 14:56
     */

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value.equals("已发布") || value.equals("草稿")) {
            return true;
        }
        return false;
    }
}
