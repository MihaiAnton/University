
package UnitTests;

import Domain.IdEntity;
import Repository.GenericRepository;
import Validators.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Str implements IdEntity<Integer> {
    private int Id;

    public Str(int Id) {
        this.Id = Id;
    }

    @Override
    public Integer getId() {
        return this.Id;
    }

    @Override
    public void setId(Integer id) {
        this.Id = id;
    }
}

class TestValidator implements Validator<Str>{
    @Override
    public void validate(Str s){

    }
}

class TestRepository extends GenericRepository<Integer, Str> {

    /**
     * Extends the GenericRepository class, being specified in <StudentId, Student> pairs
     */

    public TestRepository(TestValidator validator){
        super(validator);
    }


}
class GenericRepositoryTest {

    GenericRepository<Integer, Str> repo;

    @BeforeEach
    void setUp() {
        repo = new TestRepository(new TestValidator());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findOne() {
        Str test = new Str(1);
        repo.save(test);
        Assertions.assertEquals(repo.findOne(1),test);
    }

    @Test
    void findAll() {
        Str test = new Str(1);
        repo.save(test);

        for(Str s : repo.findAll())
             Assertions.assertEquals(s,test);
    }

    @Test
    void save() {
        Str test = new Str(1);
        Assertions.assertEquals(repo.save(test), null);
        Assertions.assertEquals(repo.save(test), test);

    }

    @Test
    void delete() {
        Str test = new Str(1);
        repo.save(test);
        Assertions.assertEquals(repo.delete(1) , test);
        Assertions.assertEquals(repo.delete(1) , null);
    }

    @Test
    void update() {
        Str test = new Str(1);
        Str test2 = new Str(2);
        repo.save(test);
        Assertions.assertEquals(repo.update(test),null);
        Assertions.assertEquals(repo.update(test2),test2);

    }
}
