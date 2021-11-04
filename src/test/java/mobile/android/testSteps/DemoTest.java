package mobile.android.testSteps;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import mobile.android.pageObject.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest extends BaseSteps {

  String responseBody;
  HomePage homePage = new HomePage();
  private String userToken;

  @Test
  public void createNewProjectUsingMobile() throws InterruptedException {
    homePage.createNewProject();
    usingAPIToVerifyCreatedProject();
  }

  public void usingAPIToVerifyCreatedProject() throws InterruptedException {
    this.userToken = "f28f4da33b36e8d079296bda837fc8440a775fdc";
    Thread.sleep(5000);
    RestAssured.baseURI = "https://api.todoist.com";
    RequestSpecification request = RestAssured.given();
    request.header("Authorization", "Bearer " + userToken);
    Response response = request.request(Method.GET, "/rest/v1/projects");
    responseBody = response.getBody().asString();
    System.out.println("Project list: " + responseBody);
    Assert.assertTrue(responseBody.contains("Demo Project"));
  }

  @Test
  public void createProjectUsingAPI() throws InterruptedException {
    RestAssured.baseURI = "https://api.todoist.com";
    RequestSpecification request = RestAssured.given();
    request.header("Authorization", "Bearer " + userToken).header("Content-Type","application/json");
    String newProject = "{\"name\": \"Shopping List\"}";
    Response response = request.body(newProject).request(Method.POST, "/rest/v1/projects");
    responseBody = response.getBody().asString();
    System.out.println("Project created: " + responseBody);
    usingMobileToVerifyCreatedProject();
  }

  public void usingMobileToVerifyCreatedProject() throws InterruptedException {
    Thread.sleep(5000);
    homePage.verifyCreatedProject();
    Assert.assertEquals(HomePage.newProject,"Shopping List");
  }

  @Test
  public void createTaskUsingMobileAndReopen() throws InterruptedException {
    homePage.createNewTask();
    getActiveTasks();
    homePage.closeTask();
    reopenTaskUsingAPI();
  }

  public void getActiveTasks() throws InterruptedException {
    Thread.sleep(5000);
    RestAssured.baseURI = "https://api.todoist.com";
    RequestSpecification request = RestAssured.given();
    request.header("Authorization", "Bearer " + userToken);
    Response response = request.request(Method.GET, "/rest/v1/tasks");
    responseBody = response.getBody().asString();
    System.out.println("Active tasks list: " + responseBody);
  }

  public void reopenTaskUsingAPI() throws InterruptedException {
    Thread.sleep(5000);

  }
}
