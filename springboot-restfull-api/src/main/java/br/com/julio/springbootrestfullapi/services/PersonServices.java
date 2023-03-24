package br.com.julio.springbootrestfullapi.services;


import br.com.julio.springbootrestfullapi.converter.DozerConverter;
import br.com.julio.springbootrestfullapi.converter.custom.PersonConverter;
import br.com.julio.springbootrestfullapi.data.model.Person;
import br.com.julio.springbootrestfullapi.data.vo.PersonVO;
import br.com.julio.springbootrestfullapi.data.vo.v2.PersonVOV2;
import br.com.julio.springbootrestfullapi.exception.ResourceNotFoundException;
import br.com.julio.springbootrestfullapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;


import java.util.List;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonConverter converter;

    public PersonVO create(PersonVO person) {
        logger.info("create");
        Person entity = DozerConverter.parseObject(person, Person.class);
        PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("create v2");
        var entity = converter.convertVOToEntity(person);
        var vo = converter.convertEntityToVO(repository.save(entity));
        return vo;
    }

    public List<PersonVO> findAll() {
        return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        Person personEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        PersonVO vo = DozerConverter.parseObject(personEntity, PersonVO.class);
        return vo;
    }

    public PersonVO update(PersonVO person) {
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
