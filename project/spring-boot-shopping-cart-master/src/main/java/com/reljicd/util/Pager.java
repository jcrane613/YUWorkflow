package com.reljicd.util;


import java.util.Optional;

import com.reljicd.model.Product;
import org.springframework.data.domain.Page;

/**
 * @author Dusan Raljic
 */
public class Pager {

    private final Page<Product> products;

    public Pager(Page<Product> products2) {
        this.products = products2;
    }

    public int getPageIndex() {
        return products.getNumber() + 1;
    }

    public int getPageSize() {
        return products.getSize();
    }

    public boolean hasNext() {
        return products.hasNext();
    }

    public boolean hasPrevious() {
        return products.hasPrevious();
    }

    public int getTotalPages() {
        return products.getTotalPages();
    }

    public long getTotalElements() {
        return products.getTotalElements();
    }

    public boolean indexOutOfBounds() {
        return this.getPageIndex() < 0 || this.getPageIndex() > this.getTotalElements();
    }

}