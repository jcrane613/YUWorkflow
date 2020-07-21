package com.reljicd.service;

import com.reljicd.exception.NotEnoughProductsInStockException;
import com.reljicd.model.Form;
import com.reljicd.model.Product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public interface ShoppingCartService {

    void addProduct(Product product);
    
    void addForm(Form form);
    
    void approveForm(Form form);

    void denyForm(Form form);
    
    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();

	Set<Form> getFormsInCart();

	Form getForm();
}
