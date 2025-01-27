package com.ruben.hexagonal.infrastructure.adapters.input.rest.response;

import java.util.List;

public class PagedResponse<T> {

    private List<T> results; // Generic list of results
    private Pagination pagination; // Pagination metadata

    public PagedResponse() {
    }

    public PagedResponse(List<T> results, Pagination pagination) {
        this.results = results;
        this.pagination = pagination;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public static class Pagination {
        private int page;
        private int perPage;
        private int total;



        public Pagination() {
        }

        public Pagination(int page, int perPage, int total) {
            this.page = page;
            this.perPage = perPage;
            this.total = total;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }


        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

    }
}