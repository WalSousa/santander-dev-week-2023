package me.dio.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;

public class GlobalExceptionHandlerTest {

	@InjectMocks
	private GlobalExceptionHandler handler;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		handler = new GlobalExceptionHandler();
	}
	@Test
	void test1(){
		IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Test");
		var t = handler.handle(illegalArgumentException);
		Assertions.assertEquals(422, t.getStatusCode().value());
	}

	@Test
	void test2(){
		NoSuchElementException noSuchElementException = new NoSuchElementException("Test");
		var t = handler.notFound(noSuchElementException);
		Assertions.assertEquals(404, t.getStatusCode().value());
	}

	@Test
	void test3(){
		IndexOutOfBoundsException indexOutOfBoundsException = new IndexOutOfBoundsException("Test");
		var t = handler.defaultHandler(indexOutOfBoundsException);
		Assertions.assertEquals(500, t.getStatusCode().value());
	}
}
