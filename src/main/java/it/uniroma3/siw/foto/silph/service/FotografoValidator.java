package it.uniroma3.siw.foto.silph.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.foto.silph.model.Fotografo;

@Component
public class FotografoValidator implements Validator {

    @Autowired
    FotografoService fotografoService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Fotografo.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors error) {
        ValidationUtils.rejectIfEmptyOrWhitespace(error, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(error, "cognome", "required");


    }
    
}