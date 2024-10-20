package me.dio.controller;

import jakarta.servlet.http.HttpServletRequest;
import me.dio.domain.model.ListUserDto;
import me.dio.domain.model.SimplePageable;
import me.dio.domain.model.User;
import me.dio.domain.model.UserListDTO;
import me.dio.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@InjectMocks
	private UserController controller;

	@Mock
	private UserService userService;

	@Mock
	private HttpServletRequest request;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		controller = new UserController(userService);
	}

	@Test
	void testGetAll() {

		UserListDTO res = new UserListDTO();
		res.setPageable(new SimplePageable());
		List<ListUserDto> users = new ArrayList<>();
		ListUserDto obj = new ListUserDto();
		obj.setId(1L);
		obj.setName("test");
		users.add(obj);

		res.setUsers(users);

		when(userService.getAll()).thenReturn(res);
		var response = controller.findAll();
		assertNotNull(response);
	}

	@Test
	void testGetAllEmpty() {

		UserListDTO res = new UserListDTO();

		res.setUsers(new ArrayList<>());

		when(userService.getAll()).thenReturn(res);
		var response = controller.findAll();
		assertNotNull(response);
	}

	@Test
	void testgetId() {
		User user = new User();
		user.setId(1L);
		user.setName("test");

		when(userService.findById(any())).thenReturn(user);

		var response = controller.findById(1L);
		assertNotNull(response);
	}

	@Test
	void testCreate() {
		User user = new User();
		user.setId(1L);
		user.setName("test");

		when(userService.create(any())).thenReturn(user);

		var response = controller.create(user);
		assertNotNull(response);
	}

	@Test
	void testUpdate() {
		User user = new User();
		user.setId(1L);
		user.setName("test");

		when(userService.update(any(), any())).thenReturn(user);

		var response = controller.update(1L, user);
		assertNotNull(response);
	}

	@Test
	void testDeleteId() {
		doNothing().when(userService).deleteById(any());

		var response = controller.deleteUser(1L);
		assertEquals(204, response.getStatusCode().value());
	}
}
