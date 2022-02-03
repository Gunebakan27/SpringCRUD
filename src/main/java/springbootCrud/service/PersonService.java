package springbootCrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import springbootCrud.model.Person;
import springbootCrud.repository.PersonRepository;

@Service
public class PersonService {
	public static PersonRepository pRepos;
	// Dependency Injection bunu yapmayıp klasik yöntemle çağırsaydık birbirine sıkı sıkı bağlı olacaktı, birinin
	//ömrü ötekine bağlı oluyor,biri oluşmadan diğeri oluşturulamıyor ve kolay ayrılmıyor
	// Spring buna ihtiyaç duyduğunda (çalıştırıldığında), oluşturuyor, alttaki tetiklenmeden boşuna çalışmasın diye.
	@Autowired
	public PersonService(PersonRepository pRepo) {
		pRepos=pRepo;
	}
//veritabanina kisi ekleyen servis methodu
	public Person addPerson(Person person) {	
		return pRepos.save(person); //pRepos. jpa sayesinde DBye kisi verileri kaydoldu	
	}
	//DBden tüm listeyi getir
	public List<Person> getPersons(){
		return pRepos.findAll();	
	}
	//id ile kisi silme
	
	public String delPersonWithId(Integer id) {
		if(pRepos.findById(id)==null) {
			throw new EmptyResultDataAccessException(id);
		}
		pRepos.deleteById(id);
		return id+" id'li kisi silindi";	
	}
//	//PUT 
////PUT , kaynağın var olup olmadığını kontrol etmek içindir, ardından güncellemek ,
////aksi takdirde yeni kaynak oluşturmak içindir. PATCH her zaman sadece bir kaynağı güncellemek içindir.
////PUT için, json dizisine id belirterek yazarız, bu id li kişi yoksa yenisini oluşturur,
////  id belirtmezsek kendi sıradaki id ile yeni oluşturur,sadece ismini yeni verirsek diğer documentler null yada 0 gelir.
////  "id":77,
////"ad": "xxx",
////"soyad": "ver",
////"yas": 66}
	public Person personUpdate(Person newPerson) {
		return pRepos.save(newPerson);
	}
//	//PATCH= bir kişinin istediğiniz field ını değiştirir, metodu yazarken kişiyi id si ile seçeriz belirtiriz
//	
	public Person personRenew(Person newPerson, Integer id) {
	Person oldPerson=pRepos.findById(id).orElseThrow(()->new IllegalStateException(id+" li kisi bulunamadi"));
			if (newPerson.getPname()!=null) {
				oldPerson.setPname(newPerson.getPname());
			} 	if (newPerson.getPsurname()!=null) {
				oldPerson.setPsurname(newPerson.getPsurname());
			}	if (newPerson.getAge()!=0) {
				oldPerson.setAge(newPerson.getAge());
			}
	return pRepos.save(oldPerson);
	}
	
	
}
