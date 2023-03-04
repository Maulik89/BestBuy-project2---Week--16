package com.bestbuy.bestbuyinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.CategoryPojo;
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
public class CategoryCRUDTest extends TestBase {

    static String name = "Range Rover" + TestUtils.getRandomValue();
    static String category = "D300 AWD AUTOMATIC MHEV" + TestUtils.getRandomValue();
    static Object categoryId;

    @Title("Create a new Category")
    @Test
    public void test001() {
        CategoryPojo pojo = new CategoryPojo();
        pojo.setName(name);
        pojo.setId(category);

        SerenityRest.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(pojo)
                .when()
                .post(EndPoints.CREATE_NEW_CATEGORIES)
                .then().log().all().statusCode(201);
    }

    @Title("Verify if Category was created")
    @Test
    public void test002() {
        String part1 = "data.findAll{it.name='";
        String part2 = "'}.get(0)";

        HashMap<String, String> categoryData = SerenityRest.given()
                .log().all()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then()
                .statusCode(200).extract().path(part1 + name + part2);

        Assert.assertThat(categoryData, hasValue(name));
        categoryId = categoryData.get("id");
        System.out.println(categoryId);

    }

    @Title("Update the Category and verify the updated information")
    @Test
    public void test003() {

        name = name + TestUtils.getRandomValue();

        CategoryPojo pojo = new CategoryPojo();
        pojo.setName(name);

        SerenityRest.given()
                .contentType(ContentType.JSON)
                .pathParam("categoriesID", categoryId)
                .body(pojo)
                .when()
                .patch(EndPoints.UPDATE_CATEGORIES_BY_ID)
                .then().log().all().statusCode(200);

        String part1 = "data.findAll{it.name=='";
        String part2 = "'}.get(0)";

        HashMap<String, ?> categoriesData = SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then().statusCode(200).extract().path(part1 + name + part2);
        Assert.assertThat(categoriesData, hasValue(name));

    }

    @Title("Delete product Data")
    @Test
    public void test004() {
        SerenityRest.given()
                .pathParams("categoriesID", categoryId)
                .when()
                .delete(EndPoints.DELETE_CATEGORIES_BY_ID)
                .then().log().all().statusCode(200);

        SerenityRest.given()
                .pathParams("categoriesID", categoryId)
                .when()
                .delete(EndPoints.DELETE_CATEGORIES_BY_ID)
                .then().log().all().statusCode(404);

    }

}
