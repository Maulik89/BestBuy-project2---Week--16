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
public class ServiceCRUDTestWithSteps extends TestBase {


    static String name = "Free MOT Service" + TestUtils.getRandomValue();

    static Object servicesId;

    @Steps
    ServiceSteps serviceSteps;


    @Title("This will create a new service")
    @Test
    public void test001() {

        ValidatableResponse response =serviceSteps.createService(name);
        response.statusCode(201);
    }

    @Title("Verify if service is created")
    @Test
    public void test002() {
        HashMap<String,Object> categoryData =serviceSteps.getserviceInfoByName(name);
        Assert.assertThat(categoryData,hasValue(name));
        servicesId= categoryData.get("id");
        System.out.println(serviceSteps);

    }
    @Title("Update the user information")
    @Test
    public void test003() {
        name = name + "categoryTest";

        serviceSteps.updateservice(servicesId, name);
        HashMap<String,Object> categoryMap=serviceSteps.getserviceInfoByName(name);
        Assert.assertThat(categoryMap,hasValue(name));

    }
    @Title("Delete category info by categoryID and verify it's deleted or not")
    @Test
    public void test004(){

        serviceSteps.deleteserviceInfoByID(servicesId).statusCode(200);
        serviceSteps.getserviceInfoByservicesId(servicesId).statusCode(404);

    }

}