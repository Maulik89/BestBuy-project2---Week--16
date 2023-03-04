package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import java.util.HashMap;

public class ProductSteps {

    @Step("getting all information :{0}")
    public ValidatableResponse getAllProducttInfo() {
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then();
    }

    @Step("creating product with name :{0},type: {1},email:{2},model:{3} ")
    public ValidatableResponse createProduct(String name, String type, String model) {

        ProductPojo pojo=new ProductPojo();
        pojo.setName(name);
        pojo.setType(type);
        pojo.setPrice(1700);
        pojo.setShipping(15);
        pojo.setUpc("af");
        pojo.setDescription("fwerf");
        pojo.setManufacturer("gergrgreg");
        pojo.setModel(model);
        pojo.setUrl("aerg");
        pojo.setImage("yjnyu");
        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(pojo)
                .when()
                .post(EndPoints.CREATE_NEW_PRODUCTS)
                .then().log().all().statusCode(201);
    }

    @Step("getting product info by Name:{0}")
    public HashMap<String, Object> getProductInfoByName(String name) {
        String part1 = "data.findAll{it.name='";
        String part2 = "'}.get(0)";

        return SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then()
                .statusCode(200)
                .extract().path(part1 + name + part2);

    }

    @Step("update product infor with name :{1}")
    public ValidatableResponse updateProduct( String name, int productsID) {

        ProductPojo pojo = new ProductPojo();
        pojo.setName(name);


        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("productsID", productsID)
                .body(pojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();


    }

    @Step("deleting product information with productId:{0}")
    public ValidatableResponse deleteProductInfoByID(int productsId){
        return SerenityRest.given()
                .pathParam("productsID",productsId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }

    @Step("getting product info By productsId:{0}")
    public ValidatableResponse getProductsInfoByproductsId(int prodcutsId){
        return SerenityRest.given()
                .pathParam("productsID",prodcutsId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();
    }

}

