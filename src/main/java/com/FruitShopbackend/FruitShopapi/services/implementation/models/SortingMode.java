package com.FruitShopbackend.FruitShopapi.services.implementation.models;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public enum SortingMode {

    //atm none and creation date sort the same

    //Page number starts at 0, so I subtract 1 from the page received

    NONE{
        @Override
        public Pageable getPageWithSortingSpecified(Integer pageNumber) {
            return PageRequest.of(
                    pageNumber-1,
                    Integer.parseInt(System.getenv("PAGE_SIZE"))
            );
        }
    },
    CREATION_DATE {
        @Override
        public Pageable getPageWithSortingSpecified(Integer pageNumber) {
            return PageRequest.of(
                    pageNumber-1,
                    Integer.parseInt(System.getenv("PAGE_SIZE")),
                    Sort.by("createdAt")
            );
        }
    },
    LOWEST_PRICE {
        @Override
        public Pageable getPageWithSortingSpecified(Integer pageNumber) {
            return PageRequest.of(
                    pageNumber-1,
                    Integer.parseInt(System.getenv("PAGE_SIZE")),
                    Sort.by("priceValue")
            );
        }
    },
    HIGHEST_PRICE {
        @Override
        public Pageable getPageWithSortingSpecified(Integer pageNumber) {
            return PageRequest.of(
                    pageNumber-1,
                    Integer.parseInt(System.getenv("PAGE_SIZE")),
                    Sort.by("priceValue").descending()
            );
        }
    };



    public abstract Pageable getPageWithSortingSpecified(Integer pageNumber);


}
