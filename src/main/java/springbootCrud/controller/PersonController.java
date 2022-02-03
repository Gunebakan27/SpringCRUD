package springbootCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springbootCrud.model.Person;
import springbootCrud.service.PersonService;

@RestController //parent controller: her türlü mapping kullanilabilir
public class PersonController {

	public PersonService pService;
	
	@Autowired
	//biz yazınca değil lazım olduğunda oluştur. alttaki gibi yaparak sıkı sıkı
	//bağlanmalarını engelledik, yeri geldiğinde birbirinden bağımsız olsun diye.
	//dependency injection yaptık, controller ın içine service yi injekte ettik
	public PersonController(PersonService pService) {
		this.pService=pService;
	}
	@GetMapping(path="/personels") //get islemi yaptigimiz icin requestmapping degil de bu anatasyonu kullandik
	public List<Person> fetchPerson(){		
		return pService.getPersons(); 	
	}
	@PostMapping(path="/personels/add")
	public Person addPerson(@RequestBody Person person) { // modelde olusan toplu bir veri geliyor
		return pService.addPerson(person);
		}
	@DeleteMapping(path="/personels/del/{id}")
	public String delPerson(@PathVariable Integer id) {
		return pService.delPersonWithId(id);
	}
	@PutMapping(path="/personels/update")
	public Person updatePerson(@RequestBody Person newPerson) {
		return pService.personUpdate(newPerson);
	}
	@PatchMapping(path="/personels/renew/{id}")
	public Person renewWithId(@RequestBody Person person,@PathVariable Integer id){
		return pService.personRenew(person,id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
