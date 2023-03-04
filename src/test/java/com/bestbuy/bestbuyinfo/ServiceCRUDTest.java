package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ServicePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ServiceCRUDTest extends TestBase {
    static String name = "Free MOT Service" + TestUtils.getRandomValue();

    static Object servicesId;

    @Title("Create a new Category")
    @Test
    public void test001() {
        ServicePojo pojo = new ServicePojo();
        pojo.setName(name);


        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(pojo)
                .when()
                .post(EndPoints.CREATE_NEW_SERVICES)
                .then().log().all().statusCode(201);
    }

    @Title("Verify if Category was created")
    @Test
    public void test002() {
        String part1 ="data.findAll{it.name='";
        String part2 ="'}.get(0)";

        HashMap<String,String> servicesData = SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then()
                .statusCode(200).extract().path(part1 + name + part2);

        Assert.assertThat(servicesData,hasValue(name));
        servicesId = servicesData.get("id");
        System.out.println(servicesId);

    }

    @Title("Update the Category and verify the updated information")
    @Test
    public void test003() {

        name = name + TestUtils.getRandomValue();

        ServicePojo pojo = new ServicePojo();;
        pojo.setName(name);

        SerenityRest.given()
                .contentType(ContentType.JSON)
                .pathParam("servicesID", servicesId)
                .body(pojo)
                .when()
                .patch(EndPoints.UPDATE_SERVICES_BY_ID)
                .then().log().all().statusCode(200);

        String part1 = "data.findAll{it.name=='";
        String part2 = "'}.get(0)";

        HashMap<String, ?> servicesData = SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(servicesData, hasValue(name));

    }

    @Title("Delete product Data")
    @Test
    public void test004() {
        SerenityRest.given()
                .pathParams("servicesID",servicesId)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then().log().all().statusCode(200);

        SerenityRest.given()
                .pathParams("servicesID",servicesId)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then().log().all().statusCode(404);

    }
}
