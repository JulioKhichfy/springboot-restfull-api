package br.com.julio.springbootrestfullapi.controller;

import br.com.julio.springbootrestfullapi.data.vo.PersonVO;
import br.com.julio.springbootrestfullapi.data.vo.v2.PersonVOV2;
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
    
    @RequestMapping(method=RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<PersonVO> findAll() {
        return service.findAll();
    }    
    
    @RequestMapping(value="/{id}",
            method=RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    /*
    @GetMapping("/{id}")
    public Person findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }
    */

    @RequestMapping(method=RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO create(@RequestBody PersonVO person) {

        return service.create(person);
    }
    /*
    @PostMapping
	public Person create(@RequestBody Person person) {
		return service.create(person);
	}
    */

    @PostMapping(value = "/v2",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {

        return service.createV2(person);
    }


    @RequestMapping(value="/{id}",
            method=RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonVO update(@PathVariable("id") Long id,
                           @RequestBody PersonVO person) {
        PersonVO vo = service.findById(id);
        return service.update(vo);
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
        return ResponseEntity.ok().build();
    }
    /*
    @DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
     */
    
}