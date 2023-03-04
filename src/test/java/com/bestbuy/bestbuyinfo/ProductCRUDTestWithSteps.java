package com.bestbuy.bestbuyinfo;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)

public class ProductCRUDTestWithSteps extends TestBase {

    static String name = "Range Rover" + TestUtils.getRandomValue();
    static String type = "D300 AWD AUTOMATIC MHEV" + TestUtils.getRandomValue();
    static String model = "Range Rover SE" + TestUtils.getRandomValue();
    static int productsId;

    @Steps
    ProductSteps productSteps;

    @Title("Create a new product")
    @Test
    public void test001() {

        ValidatableResponse response =productSteps.createProduct( name,  type,  model);
        response.statusCode(201);

    }


    @Title("Cerify if product is created")
    @Test
    public void test002() {
        HashMap<String,Object> productMapData =productSteps.getProductInfoByName(name);
        Assert.assertThat(productMapData,hasValue(name));
        productsId= (int) productMapData.get("id");
        System.out.println(productsId);

    }

    @Title("Update the product information")
    @Test
    public void test003() {
        name = name + "test";

        productSteps.updateProduct(name,productsId);
        HashMap<String,Object> productMap=productSteps.getProductInfoByName(name);
        Assert.assertThat(productMap,hasValue(name));

    }
    @Title("Delete product info by productID and verify it's deleted or not")
    @Test
    public void test004(){

        productSteps.deleteProductInfoByID(productsId).statusCode(200);
        productSteps.getProductsInfoByproductsId(productsId).statusCode(404);

    }

}
