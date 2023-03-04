package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ServicePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import java.util.HashMap;

public class ServiceSteps {

    @Step("getting all information :{0}")
    public ValidatableResponse getAllServiceInfo(){
        return   SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then();

    }

    @Step("creating Service with Name :{0}")
    public ValidatableResponse createService(String name) {


        ServicePojo pojo = new ServicePojo();
        pojo.setName(name);
        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(pojo)
                .when()
                .post(EndPoints.CREATE_NEW_SERVICES)
                .then().log().all().statusCode(201);
    }

    @Step("getting services info by Name:{0}")
    public HashMap<String, Object> getserviceInfoByName(String name) {
        String part1 = "data.findAll{it.name='";
        String part2 = "'}.get(0)";

        return SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then()
                .statusCode(200)
                .extract().path(part1 + name + part2);

    }

    @Step("update services info with serviceId:{0},Name :{1}")
    public ValidatableResponse updateservice(Object serviceId, String name) {

        ServicePojo pojo = new ServicePojo();;
        pojo.setName(name);


        return SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("servicesID", serviceId)
                .body(pojo)
                .when()
                .put(EndPoints.UPDATE_SERVICES_BY_ID)
                .then();


    }

    @Step("deleting services information with serviceId:{0}")
    public ValidatableResponse deleteserviceInfoByID(Object serviceId){
        return SerenityRest.given()
                .pathParam("servicesID",serviceId)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then();
    }

    @Step("getting service info By servicesId:{0}")
    public ValidatableResponse getserviceInfoByservicesId(Object serviceId){
        return SerenityRest.given()
                .pathParam("servicesID",serviceId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICES_BY_ID)
                .then();
    }
}
