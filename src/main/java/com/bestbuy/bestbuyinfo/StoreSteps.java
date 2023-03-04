package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import java.util.HashMap;

public class StoreSteps {
    @Step("getting all information :{0}")
    public ValidatableResponse getAllStoreInfo() {
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_Stores)
                .then();
    }

    @Step("creating store with name :{0},type: {1},address:{2},adreess2:{3},city{4},state{5},zip{6},hours{7} ")
    public ValidatableResponse CreateStore(String name, String type) {

        StorePojo pojo=new StorePojo();
        pojo.setName(name);
        pojo.setType(type);
        pojo.setAddress("jbdf");
        pojo.setAddress2("fdgf");
        pojo.setCity("gdtdg");
        pojo.setState("fdgdfg");
        pojo.setZip("5654");
        pojo.setHours("46");
        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(pojo)
                .when()
                .post(EndPoints.CREATE_NEW_STORE)
                .then().log().all().statusCode(201);
    }

    @Step("getting store info by Name:{0}")
    public HashMap<String, Object> getStoreInfoByName(String name) {
        String part1 = "data.findAll{it.name='";
        String part2 = "'}.get(0)";

        return SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_Stores)
                .then()
                .statusCode(200)
                .extract().path(part1 + name + part2);

    }

    @Step("update Store info with name :{1}")
    public ValidatableResponse UpdateStore( String name, int storesId) {

        StorePojo pojo=new StorePojo();
        pojo.setName(name);


        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("storesID", storesId)
                .body(pojo)
                .when()
                .put(EndPoints.UPDATE_Stores_BY_ID)
                .then();


    }

    @Step("deleting store information with storeId:{0}")
    public ValidatableResponse deleteStoreInfoByID(int storesId){
        return SerenityRest.given()
                .pathParam("storesID",storesId)
                .when()
                .delete(EndPoints.DELETE_Stores_BY_ID)
                .then();
    }

    @Step("getting store info By storesId:{0}")
    public ValidatableResponse getstoreInfoBystoreId(int storesId){
        return SerenityRest.given()
                .pathParam("storesID",storesId)
                .when()
                .get(EndPoints.GET_SINGLE_Stores_BY_ID)
                .then();
    }

}


