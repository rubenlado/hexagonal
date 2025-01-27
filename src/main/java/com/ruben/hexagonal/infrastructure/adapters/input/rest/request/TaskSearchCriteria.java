package com.ruben.hexagonal.infrastructure.adapters.input.rest.request;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 *
 * Patrón builder: útil cuando tenemos muchos filtros opcionales, podemos añadirlos a nuestro antojo
 *
 *
 * Ejemplo: Si tenemos un findByCriteria y la criteria tiene muchos parámetros, una opción podría ser
 * hacer sobrecarga de nuestro metodo. Pero si hay mucha criteria diferente, acabamos antes instanciando
 * una clase builder
 */


public class TaskSearchCriteria {

    private Optional<String> search;
    private Pageable pageable;

    private TaskSearchCriteria(Builder builder) {
        this.pageable = builder.pageable;
        this.search = builder.search;
    }

    public Pageable getPageable() {
        return pageable;
    }


    public Optional<String> getSearch() {
        return search;
    }


    public static class Builder {
        private Optional<String> search = Optional.empty();
        private Pageable pageable = Pageable.unpaged();

        public Builder search(String search) {
            this.search = Optional.ofNullable(search);
            return this;
        }

        public Builder pageable(Pageable pageable) {
            this.pageable = pageable;
            return this;
        }


        public TaskSearchCriteria build() {
            return new TaskSearchCriteria(this);
        }


    }


}



