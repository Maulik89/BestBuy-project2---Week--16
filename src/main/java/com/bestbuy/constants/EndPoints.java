package com.bestbuy.constants;

public class EndPoints {

    // ------ This is Endpoints of BestBuy API ------ //


    // Endpoints of Products

    public static final String GET_ALL_PRODUCTS="/products";
    public static final String CREATE_NEW_PRODUCTS="/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/products/{productsID}";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{productsID}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{productsID}";


    // Endpoints of Store

    public static final String GET_ALL_Stores = "/stores";
    public static final String CREATE_NEW_STORE="/stores";
    public static final String GET_SINGLE_Stores_BY_ID = "/stores/{storesID}";
    public static final String UPDATE_Stores_BY_ID = "/stores/{storesID}";
    public static final String DELETE_Stores_BY_ID = "/stores/{storesID}";



    // Endpoints of Services

    public static final String GET_ALL_SERVICES = "/services";
    public static final String CREATE_NEW_SERVICES = "/services";
    public static final String GET_SINGLE_SERVICES_BY_ID = "/services//{servicesID}";
    public static final String UPDATE_SERVICES_BY_ID = "/services/{servicesID}";
    public static final String DELETE_SERVICES_BY_ID = "/services/{servicesID}";



    // Endpoints of Categories

    public static final String CREATE_NEW_CATEGORIES = "/categories";
    public static final String GET_ALL_CATEGORIES = "/categories";
    public static final String GET_SINGLE_CATEGORIES_BY_ID = "/categories/{categoriesID}";
    public static final String UPDATE_CATEGORIES_BY_ID = "/categories/{categoriesID}";
    public static final String DELETE_CATEGORIES_BY_ID = "/categories/{categoriesID}";

}
