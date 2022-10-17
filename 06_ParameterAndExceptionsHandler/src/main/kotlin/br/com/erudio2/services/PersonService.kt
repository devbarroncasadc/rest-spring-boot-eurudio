package br.com.erudio2.services

import br.com.erudio2.exceptions.ResourceNotFoundException
import br.com.erudio2.model.Person
import br.com.erudio2.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository
    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<Person> {
        logger.info("Finding all person")

        return repository.findAll()
    }

    fun findById(id: Long): Person {
        logger.info("Finding one person")

        return repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }
    }

    fun create(person: Person): Person {
        logger.info("Creating one person with name! ${person.firstName}! ")

        return repository.save(person);
    }

    fun update(person: Person): Person {
        logger.info("Updating a person with name! ${person.firstName}! ")

        val entity = repository.findById(person.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return repository.save(entity);
    }

    fun delete(id: Long) {
        logger.info("Deleting a person with id! ${id}! ")

        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        repository.delete(entity)

    }

}