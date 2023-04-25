package com.todo.dave.TodoApp;

import com.todo.dave.TodoApp.controllers.TaskController;
import com.todo.dave.TodoApp.models.TodoItem;
import com.todo.dave.TodoApp.repositories.TodoItemRepository;
import com.todo.dave.TodoApp.services.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@SpringBootTest
class TodoAppApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Mock
	private TodoService todoService;

	@InjectMocks
	private TaskController taskController;

	@Test
	public void testAddTodo() {

		// Define the request object
		TodoItem todoItem = new TodoItem();
		todoItem.setDescription("This is a test todo");

		// Define the expected response object
		TodoItem expectedResponse = new TodoItem();
		expectedResponse.setId(1L);
		expectedResponse.setDescription("This is a test todo");

		// Define the Mockito behavior
		when(todoService.save(todoItem)).thenReturn(expectedResponse);

		// Call the controller method
		ResponseEntity<TodoItem> responseEntity = TaskController.createTodoItem(todoItem);

		// Verify the response
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(expectedResponse, responseEntity.getBody());
	}
}
