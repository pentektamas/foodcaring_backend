package ro.disi.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import ro.disi.DISIApplicationTestConfig;
import ro.disi.dtos.PersonDummyDTO;
import ro.disi.dtos.PersonDummyDetailsDTO;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/test-sql/create.sql")
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:/test-sql/delete.sql")
public class PersonDummyServiceIntegrationTests extends DISIApplicationTestConfig {

    @Autowired
    PersonDummyService personDummyService;

    @Test
    public void testGetCorrect() {
        List<PersonDummyDTO> personDummyDTOList = personDummyService.findPersons();
        assertEquals("Test Insert Person", 1, personDummyDTOList.size());
    }

    @Test
    public void testInsertCorrectWithGetById() {
        PersonDummyDetailsDTO p = new PersonDummyDetailsDTO("John", "Somewhere Else street", 22);
        UUID insertedID = personDummyService.insert(p);

        PersonDummyDetailsDTO insertedPerson = new PersonDummyDetailsDTO(insertedID, p.getName(), p.getAddress(), p.getAge());
        PersonDummyDetailsDTO fetchedPerson = personDummyService.findPersonById(insertedID);

        assertEquals("Test Inserted Person", insertedPerson, fetchedPerson);
    }

    @Test
    public void testInsertCorrectWithGetAll() {
        PersonDummyDetailsDTO p = new PersonDummyDetailsDTO("John", "Somewhere Else street", 22);
        personDummyService.insert(p);

        List<PersonDummyDTO> personDummyDTOList = personDummyService.findPersons();
        assertEquals("Test Inserted Persons", 2, personDummyDTOList.size());
    }
}
