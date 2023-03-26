package com.example.product;

import com.example.product.dao.Product;
import com.example.product.dao.ProductDAO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class ProductDAOImplTest {

  @Autowired
  ProductDAO productDAO;

  //등록
  @Test
  @DisplayName("상품등록")
  void save(){
    Product product = new Product();
    product.setPname("키보드");
    product.setQuantity(1L);
    product.setPrice(10000L);

    Long productId = productDAO.save(product);
    log.info("productId={}",productId);
    Assertions.assertThat(productId).isGreaterThan(0L);
  }

  //조회
  @Test
  @DisplayName("상품조회")
  void findById(){
    Long productId = 10L;
    Optional<Product> product = productDAO.findById(productId);

    Product findedProduct = product.orElseThrow();
    Assertions.assertThat(findedProduct.getPname()).isEqualTo("키보드");
    Assertions.assertThat(findedProduct.getQuantity()).isEqualTo(1L);
    Assertions.assertThat(findedProduct.getPrice()).isEqualTo(10000L);
  }

  //수정
  @Test
  @DisplayName("상품수정")
  void update() {
    Long productId = 70L;
    Product product = new Product();
    product.setPname("키보드_수정");
    product.setQuantity(1L);
    product.setPrice(20000L);
    int updatedRowCount = productDAO.update(productId, product);
    Optional<Product> findedProduct = productDAO.findById(productId);

    Assertions.assertThat(updatedRowCount).isEqualTo(1);
    Assertions.assertThat(findedProduct.get().getPname()).isEqualTo(product.getPname());
    Assertions.assertThat(findedProduct.get().getQuantity()).isEqualTo(product.getQuantity());
    Assertions.assertThat(findedProduct.get().getPrice()).isEqualTo(product.getPrice());
  }

  //삭제
  @Test
  @DisplayName("상품삭제")
  void delete() {
    Long productId = 10L;
    int deletedRowCount = productDAO.delete(productId);
    Optional<Product> findedProduct = productDAO.findById(productId);

    Assertions.assertThatThrownBy(()->findedProduct.orElseThrow())
        .isInstanceOf(NoSuchElementException.class);
  }

  //목록
  @Test
  @DisplayName("상품목록")
  void findAll() {
    List<Product> list = productDAO.findAll();

    Assertions.assertThat(list.size()).isGreaterThan(0);
  }

}
