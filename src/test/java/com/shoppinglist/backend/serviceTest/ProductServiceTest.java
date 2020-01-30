package com.shoppinglist.backend.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.shoppinglist.backend.model.Product;
import com.shoppinglist.backend.repository.ProductRepository;
import com.shoppinglist.backend.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@MockBean
	ProductRepository productRepository;

	@Test
	public void testAddProductShouldAddANewProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setName("sajt");
		product.setHolderName("testHolder");
		product.setPrice(100);
		String holderName = "testHolder";

		productService.addProduct(product, holderName);

		verify(productRepository, times(1)).save(product);

	}

	@Test
	public void testFindOneShouldReturnAProductById() {
		Product product = new Product();
		product.setId(1L);
		product.setName("sajt");
		product.setHolderName("testHolder");
		product.setPrice(100);

		Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		assertEquals(productService.findOne(1L), product);
	}

	@Test
	public void testFindOneShouldThrowIllegalArgumentException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			productService.findOne(-1L);
		});
	}

	@Test
	public void testGetProductsShouldReturnAllProductsByHolderName() {
		Product product = new Product();
		product.setName("sajt");
		product.setHolderName("testHolder");
		product.setPrice(100);

		Product secondProduct = new Product();
		product.setName("bor");
		product.setHolderName("testHolder");
		product.setPrice(300);

		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		productList.add(secondProduct);

		Mockito.when(productRepository.findByHolderName("testHolder")).thenReturn(productList);

		assertEquals(productService.getProducts("testHolder"), productList);
	}

	@Test
	public void testDeleteProductShouldDeleteAProductById() {
		Long id = 1L;

		productService.deleteProduct(id);

		verify(productRepository, times(1)).deleteById(id);
	}

	@Test
	public void testUpdateProductShouldUpdateAProductsFields() {
		Product product = new Product();
		product.setId(1L);
		product.setName("sajt");
		product.setHolderName("testHolder");
		product.setPrice(100);

		Product secondProduct = new Product();
		product.setName("bor");
		product.setHolderName("testHolder");
		product.setPrice(300);

		Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		product.setName(secondProduct.getName());
		product.setPrice(secondProduct.getPrice());

		Mockito.when(productRepository.save(product)).thenReturn(product);
		assertEquals(productService.editProduct(1L, secondProduct), product);
	}

}
