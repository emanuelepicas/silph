package it.uniroma3.siw.foto.silph.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.foto.silph.model.FunzionarioSilph;

public interface FunzionarioSilphRepository extends CrudRepository<FunzionarioSilph,Long>{
    List<FunzionarioSilph> findByEmail(String email);
    List<FunzionarioSilph> findByPwd(String pwd);
    List<FunzionarioSilph>findByEmailAndPwd(String email,String pwd);
    
}