package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    public static final String NAME = "Jimmy";
    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryMapper categoryMapper;

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllCategories() {

        //given
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setName(NAME);

        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setName(NAME);

        when(categoryMapper.categoryToCategoryDTO(any(Category.class))).thenReturn(categoryDTO1).thenReturn(categoryDTO2);


        //when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        //then
        assertEquals(2, categoryDTOS.size());
    }

    @Test
    public void getCategoryByName() throws ResourceNotFoundException {

        //given
        Category category = new Category();
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(NAME);

        when(categoryMapper.categoryToCategoryDTO(any(Category.class))).thenReturn(categoryDTO);

        //when
        CategoryDTO categoryDTO1 = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(NAME, categoryDTO1.getName());
    }
}