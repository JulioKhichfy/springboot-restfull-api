package br.com.julio.springbootrestfullapi.repository;


import br.com.julio.springbootrestfullapi.data.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}