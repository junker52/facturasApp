package com.learning.facturas.app.utils;

import org.springframework.data.domain.Page;

/**
 * Created by Ricard on 07/07/2018.
 */
public class PaginatorHelper {
    private int totalPages;
    private int actualPage;
    private boolean hasNext;
    private boolean hasPrevious;
    private int totalElements;

    public PaginatorHelper(Page page ) {
        this.actualPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
        this.totalElements = page.getNumberOfElements();
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getActualPage() {
        return actualPage;
    }

    public void setActualPage(int actualPage) {
        this.actualPage = actualPage;
    }
}
