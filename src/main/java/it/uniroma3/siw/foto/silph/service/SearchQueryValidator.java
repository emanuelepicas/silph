package it.uniroma3.siw.foto.silph.service;


import it.uniroma3.siw.foto.silph.model.SerchQuery;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SearchQueryValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SerchQuery.class.equals(clazz.getClass());
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "query", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "required");
    }

}