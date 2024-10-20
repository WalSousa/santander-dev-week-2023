package me.dio.service;

import me.dio.domain.model.Account;
import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		userService = new UserServiceImpl(userRepository);
	}

	@Test
	void testGetAll(){
		List<User> users = new ArrayList<>();

		User obj = new User();

		obj.setId(1L);
		obj.setName("test");
		users.add(obj);

		when(userRepository.findAll()).thenReturn(users);

		var response = userService.getAll();
		assertNotNull(response);
	}

	@Test
	void testGetId(){
		User obj = new User();
		obj.setId(1L);
		obj.setName("test");
		when(userRepository.findById(any())).thenReturn(Optional.of(obj));

		var response = userService.findById(1L);
		assertNotNull(response);
	}

	@Test
	void testGetIdEmpty(){
		when(userRepository.findById(any())).thenReturn(Optional.empty());

		Assertions.assertThrows(NoSuchElementException.class, () -> userService.findById(1L));
	}

	@Test
	void testdeleteIdEmpty(){
		when(userRepository.findById(any())).thenReturn(Optional.empty());

		Assertions.assertThrows(NoSuchElementException.class, () -> userService.deleteById(1L));
	}

	@Test
	void testdeleteId(){
		User obj = new User();
		obj.setId(1L);
		obj.setName("test");
		when(userRepository.findById(any())).thenReturn(Optional.of(obj));

		doNothing().when(userRepository).deleteById(any());
		userService.deleteById(1L);
	}

	@Test
	void testCreate() {
		User obj = new User();
		obj.setId(1L);
		obj.setName("test");
		Account account = new Account();
		account.setId(1L);
		account.setNumber("123");
		account.setLimit(new BigDecimal(5));
		account.setAgency("001");
		account.setBalance(new BigDecimal(6));
		obj.setAccount(account);
		when(userRepository.existsByAccountNumber(any())).thenReturn(Boolean.FALSE);
		when(userRepository.save(any())).thenReturn(obj);
		var response = userService.create(obj);
		assertNotNull(response);
	}

	@Test
	void testCreateException() {
		User obj = new User();
		obj.setId(1L);
		obj.setName("test");
		Account account = new Account();
		account.setId(1L);
		account.setNumber("123");
		account.setLimit(new BigDecimal(5));
		account.setAgency("001");
		account.setBalance(new BigDecimal(6));
		obj.setAccount(account);

		when(userRepository.existsByAccountNumber(any())).thenReturn(Boolean.TRUE);
		Assertions.assertThrows(IllegalArgumentException.class, () -> userService.create(obj));
	}

	@Test
	void testUpdate() {
		User obj = new User();
		obj.setId(1L);
		obj.setName("test");
		Account account = new Account();
		account.setId(1L);
		account.setNumber("123");
		account.setLimit(new BigDecimal(5));
		account.setAgency("001");
		account.setBalance(new BigDecimal(6));
		obj.setAccount(account);
		when(userRepository.findById(any())).thenReturn(Optional.of(obj));
		when(userRepository.save(any())).thenReturn(obj);
		var response = userService.update(1L, obj);
		assertNotNull(response);
	}

	@Test
	void testUpdateException() {
		User obj = new User();
		obj.setId(1L);
		obj.setName("test");
		Account account = new Account();
		account.setId(1L);
		account.setNumber("123");
		account.setLimit(new BigDecimal(5));
		account.setAgency("001");
		account.setBalance(new BigDecimal(6));
		obj.setAccount(account);

		when(userRepository.findById(any())).thenReturn(Optional.empty());

		Assertions.assertThrows(NoSuchElementException.class, () -> userService.update(1L, obj));
	}
}
