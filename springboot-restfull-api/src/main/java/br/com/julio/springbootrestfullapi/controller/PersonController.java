package br.com.julio.springbootrestfullapi.controller;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.julio.springbootrestfullapi.data.vo.PersonVO;
import br.com.julio.springbootrestfullapi.data.vo.v2.PersonVOV2;
import br.com.julio.springbootrestfullapi.repository.PersonRepository;
import br.com.julio.springbootrestfullapi.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {
    
    @Autowired
    private PersonServices service;

    @Autowired
    PersonRepository repository;
    
    @RequestMapping(method=RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "application/x-yaml"})
    public List<PersonVO> findAll() {
        List<PersonVO> persons =  service.findAll();

        return persons;
    }    
    
    @RequestMapping(value="/{id}",
            method=RequestMethod.GET,
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "application/x-yaml"})
    public PersonVO findById(@PathVariable("id") Long id) {
        PersonVO vo = service.findById(id);

        return vo;
    }

    /*
    @GetMapping("/{id}")
    public Person findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
    */

    @RequestMapping(method=RequestMethod.POST,
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "application/x-yaml"},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "application/x-yaml"})
    public PersonVO create(@RequestBody PersonVO person) {

        PersonVO vo = service.create(person);

        return vo;
    }
    /*
    @PostMapping
	public Person create(@RequestBody Person person) {
		return service.create(person);
	}
    */

    @PostMapping(value = "/v2",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "application/x-yaml"},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "application/x-yaml"})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {

        return service.createV2(person);

    }


    @RequestMapping(
            method=RequestMethod.PUT,
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "application/x-yaml"},
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "application/x-yaml"})
    public PersonVO update( @RequestBody PersonVO person) {
        PersonVO personVO = service.update(person);

        return personVO;
    }
    /*
    @PutMapping
	public Person update(@RequestBody Person person) {
		return service.update(person);
	}
    */
    
    @RequestMapping(value="/{id}", 
            method=RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    /*
    @DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
     */
    
}