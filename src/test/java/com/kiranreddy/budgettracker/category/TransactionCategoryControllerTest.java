package com.kiranreddy.budgettracker.category;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = { TransactionCategoryController.class })
public class TransactionCategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TransactionCategoryService transactionCategoryService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void retrieveCategoriesTest() throws Exception {
		when(transactionCategoryService.retrieveTransactionCategories())
				.thenReturn(Arrays.asList(new TransactionCategory(1L, "category", "type")));
		mockMvc.perform(MockMvcRequestBuilders.get("/transactions/categories"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].category").value("category"))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].type").value("type"));
	}

	@Test
	public void retrieveCategoriesByTypeTest() throws Exception {
		when(transactionCategoryService.retrieveTransactionCategoriesByType("type"))
				.thenReturn(Arrays.asList(new TransactionCategory(1L, "category", "type")));
		mockMvc.perform(MockMvcRequestBuilders.get("/transactions/categories").param("type", "type"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].category").value("category"))
				.andExpect(MockMvcResultMatchers.jsonPath("@.[0].type").value("type"));
	}

	@Test
	public void retrieveValidCategoryTest() throws Exception {
		when(transactionCategoryService.retrieveTransactionCategoryById(1L))
				.thenReturn(new TransactionCategory(1L, "category", "type"));
		mockMvc.perform(MockMvcRequestBuilders.get("/transactions/categories/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").value("category"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.type").value("type"));
	}

	@Test
	public void retrieveInValidCategoryTest() throws Exception {
		when(transactionCategoryService.retrieveTransactionCategoryById(1L))
				.thenThrow(new TransactionCategoryNotFoundException("Transaction category not found"));
		mockMvc.perform(MockMvcRequestBuilders.get("/transactions/categories/1"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void saveCategoryTest() throws Exception {
		when(transactionCategoryService.saveTransactionCategory(any()))
				.thenReturn(new TransactionCategory(1L, "category", "type"));
		mockMvc.perform(MockMvcRequestBuilders.post("/transactions/categories")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(new TransactionCategory(null, "category", "type"))))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").value("category"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.type").value("type"));
	}

	@Test
	public void updateCategoryTest() throws Exception {
		when(transactionCategoryService.updateTransactionCategory(any(), any()))
				.thenReturn(new TransactionCategory(1L, "category", "type"));
		mockMvc.perform(MockMvcRequestBuilders.put("/transactions/categories/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(new TransactionCategory(1L, "category", "type"))))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$.category").value("category"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.type").value("type"));
	}
}