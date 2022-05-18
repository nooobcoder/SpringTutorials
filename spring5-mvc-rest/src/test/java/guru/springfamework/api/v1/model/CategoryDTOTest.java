package guru.springfamework.api.v1.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDTOTest {

    @Test
    public void testCategoryDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Test");

        assertEquals(1L, categoryDTO.getId().longValue());
        assertEquals("Test", categoryDTO.getName());
    }
}