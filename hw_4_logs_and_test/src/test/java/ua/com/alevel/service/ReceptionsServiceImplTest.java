package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Reception;
import ua.com.alevel.service.impl.ReceptionServiceImpl;

import static ua.com.alevel.service.TestUtil.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReceptionsServiceImplTest {

    private final static ReceptionService receptionService = new ReceptionServiceImpl();

    @BeforeAll
    static void initDBForTest() {
        for (int i = 0; i < COUNT_OF_RECEPTIONS; i++) {
            Reception reception = TestUtil.generateReception();
            receptionService.create(reception);
        }
        verifyReceptionArrayWhenReceptionsArrayIsNotEmpty(COUNT_OF_RECEPTIONS);
    }

    private static void verifyReceptionArrayWhenReceptionsArrayIsNotEmpty(int size) {
        int sizeDoctors = receptionService.findAll().length;
        Assertions.assertTrue(sizeDoctors != 0);
        Assertions.assertEquals(size, sizeDoctors);
    }

    @Order(1)
    @Test
    public void shouldCreateReceptionWhenReviewIsEmpty() {
        Reception reception = TestUtil.generateReception().setReviewComment(null);
        receptionService.create(reception);
        verifyReceptionArrayWhenReceptionsArrayIsNotEmpty(COUNT_OF_RECEPTIONS + 1);
    }

    @Order(2)
    @Test
    public void shouldBeReception() {
        Long id = getRandomIdFromReceptionList();
        receptionService.delete(id);
        verifyReceptionArrayWhenReceptionsArrayIsNotEmpty(COUNT_OF_RECEPTIONS);
    }

    @Order(3)
    @Test
    public void shouldBeFindReceptionWhenIdIsRandom() {
        Reception reception = getReceptionFromReceptionList((getRandomIdFromReceptionList()));
        Assertions.assertNotNull(reception);
    }

    @Order(4)
    @Test
    public void shouldBeUpdateReception() {
        Long id = getRandomIdFromReceptionList();
        Reception reception = getReceptionFromReceptionList(id);
        reception.setReviewComment("test");
        receptionService.update(reception);
        reception = receptionService.findById(id);
        Assertions.assertEquals("test", reception.getReviewComment());
    }

    @Order(5)
    @Test
    public void shouldBeNonEmptyArrayWhenAllReceptionsDeleted() {
        TestUtil.deleteAllReceptions();
        Assertions.assertEquals(0, receptionService.findAll().length);
    }
}
