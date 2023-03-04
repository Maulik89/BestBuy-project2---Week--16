package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.CategoryPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import java.util.HashMap;

public class CategorySteps {

    @Step("getting all information :{0}")
    public ValidatableResponse getAllStudentInfo(){
        return   SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then();

    }

    @Step("creating catgories with Name :{0}")
    public ValidatableResponse createCategories(String name, String category) {


        CategoryPojo pojo = new CategoryPojo();
        pojo.setName(name);
        pojo.setId(category);
        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(pojo)
                .when()
                .post(EndPoints.CREATE_NEW_CATEGORIES)
                .then().log().all().statusCode(201);
    }

    @Step("getting catgory info by Name:{0}")
    public HashMap<String, Object> getCatgoryInfoByName(String name) {
        String part1 = "data.findAll{it.name='";
        String part2 = "'}.get(0)";

        return SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then()
                .statusCode(200)
                .extract().path(part1 + name + part2);

    }

    @Step("update categories infor with categoryId:{0},Name :{1}")
    public ValidatableResponse updatecategory(Object categoryId, String name) {

        CategoryPojo pojo = new CategoryPojo();
        pojo.setName(name);

        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("categoriesID", categoryId)
                .body(pojo)
                .when()
                .put(EndPoints.UPDATE_CATEGORIES_BY_ID)
                .then();


    }

    @Step("deleting category information with categoryId:{0}")
    public ValidatableResponse deletecategoryInfoByID(Object categoryId){
        return SerenityRest.given()
                .pathParam("categoriesID",categoryId)
                .when()
                .delete(EndPoints.DELETE_CATEGORIES_BY_ID)
                .then();
    }

    @Step("getting student info By studentId:{0}")
    public ValidatableResponse getcategoryInfoBycategoryId(Object categoryId){
        return SerenityRest.given()
                .pathParam("categoriesID",categoryId)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORIES_BY_ID)
                .then();
    }
}
