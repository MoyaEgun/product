package com.example.product.dao;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

  Long save(Product product);


  Optional<Product> findById(Long pId);


  int update(Long pId,Product product);


  int delete(Long pId);


  int deleteAll();


  List<Product> findAll();


  boolean isExist(Long productId);

  int countOfRecord();
}
