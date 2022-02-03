package springbootCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootCrud.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
