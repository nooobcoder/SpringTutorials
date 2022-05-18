package guru.springfamework.services;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResourceNotFoundExceptionTest {

    @Test
    public void testResourceNotFoundException() {
        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException();
        assertEquals(null, resourceNotFoundException.getMessage());
    }

    @Test
    public void testResourceNotFoundExceptionWithMessage() {
        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException("message");
        assertEquals("message", resourceNotFoundException.getMessage());
    }

    @Test
    public void testResourceNotFoundExceptionWithMessageAndCause() {
        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException("message", new Throwable());
        assertEquals("message", resourceNotFoundException.getMessage());
    }

    @Test
    public void testResourceNotFoundExceptionWithMessageAndCauseAndSuppressionAndStackTrace() {
        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException("message", new Throwable(), true, true);
        assertEquals("message", resourceNotFoundException.getMessage());
        assertEquals(Throwable.class, resourceNotFoundException.getCause().getClass());
    }
}