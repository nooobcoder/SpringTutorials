package guru.springfamework;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;

import static org.mockito.ArgumentMatchers.any;

public class Spring5MvcRestApplicationTest {

    @Test
    public void testApplication() {
        SpringApplication app = Mockito.mock(SpringApplication.class);
        Mockito.when(app.run(any(String[].class))).thenReturn(any());
    }
}