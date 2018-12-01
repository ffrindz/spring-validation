package com.maatic;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharacterValidator implements ConstraintValidator<SpecialCharacter, Object> {

    private String field;

    public void initialize(SpecialCharacter constraintAnnotation) {
        this.field = constraintAnnotation.field();
    }

    public boolean isValid(Object value,
                           ConstraintValidatorContext context) {

        String REGEX = "^[a-zA-Z0-9,.$@]*$";
        Pattern pattern = Pattern.compile(REGEX);

        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);

        Object fieldType = new BeanWrapperImpl(value)
                .getPropertyType(field);

        if (fieldValue != null && fieldType != null && fieldType.toString().contains("String")) {
            Matcher matcher = pattern.matcher(fieldValue.toString());
            return matcher.matches();
        } else if (fieldValue != null && fieldType != null && fieldType.toString().contains("List")) {
            List<Boolean> booleanList = new ArrayList<>();
            for (String listValue : (List<String>) fieldValue) {
                Matcher matcher = pattern.matcher(listValue);
                booleanList.add(matcher.matches());
            }
            return !booleanList.contains(false);
        }
        return true;
    }
}