package br.com.julio.springbootrestfullapi.services;


import br.com.julio.springbootrestfullapi.controller.PersonController;
import br.com.julio.springbootrestfullapi.converter.DozerConverter;
import br.com.julio.springbootrestfullapi.converter.custom.PersonConverter;
import br.com.julio.springbootrestfullapi.data.model.Person;
import br.com.julio.springbootrestfullapi.data.vo.PersonVO;
import br.com.julio.springbootrestfullapi.data.vo.v2.PersonVOV2;
import br.com.julio.springbootrestfullapi.exception.RequiredObjectIsNullException;
import br.com.julio.springbootrestfullapi.exception.ResourceNotFoundException;
import br.com.julio.springbootrestfullapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonConverter converter;

    public PersonVO create(PersonVO person) {
        logger.info("create");
        if (person == null) throw new RequiredObjectIsNullException();
        Person entity = DozerConverter.parseObject(person, Person.class);
        PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("create v2");
        var entity = converter.convertVOToEntity(person);
        var vo = converter.convertEntityToVO(repository.save(entity));
        return vo;
    }

    public List<PersonVO> findAll() {
        List<PersonVO> vos = DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
        vos
                .stream()
                .forEach(p -> p.add(
                                linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()
                        )
                );
        return vos;
    }

    public PersonVO findById(Long id) {

        Person personEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        PersonVO vo = DozerConverter.parseObject(personEntity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO update(PersonVO person) {
        if (person == null) throw new RequiredObjectIsNullException();
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
