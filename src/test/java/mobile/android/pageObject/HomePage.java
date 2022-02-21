package mobile.android.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static mobile.android.testSteps.BaseSteps.driver;

public class HomePage {

  public static String newProject;

  By btnHamburger = By.xpath("//android.widget.ImageButton[@content-desc='Menu']");
  By btnNewProject = By.xpath("(//android.widget.ImageButton[@content-desc='Add'])[1]");
  By txtProjectName = By.xpath("//android.widget.EditText[@text='Name']");
  By btnSubmitNewProject = By.xpath("//android.widget.TextView[@content-desc='Done']");
  By btnProjectExpandCollapse = By.xpath("(//android.widget.ImageView[@content-desc='Expand/collapse'])[1]");
  By lblProjectName = By.xpath("//android.widget.TextView[@text='Shopping List']");
  By btnCreateTask = By.id("com.todoist:id/fab");
  By txtNewTaskName = By.id("android:id/message");
  By btnCloseTask = By.id("com.todoist:id/checkmark");
  By btnOpenProject = By.xpath("//android.widget.TextView[@text='Demo Project']");
  By btnSubmitNewTask = By.xpath("//android.widget.ImageView[@content-desc='Add']");

  public void createNewProject() {
    driver.findElement(btnHamburger).click();
    driver.findElement(btnNewProject).click();
    driver.findElement(txtProjectName).click();
    driver.findElement(txtProjectName).sendKeys("Demo Project");
    driver.findElement(btnSubmitNewProject).click();
  }

  public String verifyCreatedProject() {
    driver.findElement(btnHamburger).click();
    driver.findElement(btnProjectExpandCollapse).click();
    newProject = driver.findElement(lblProjectName).getText();
    return newProject;
  }

  public void createNewTask() {
    driver.findElement(btnOpenProject).click();
    driver.findElement(btnCreateTask).click();
    driver.findElement(txtNewTaskName).click();
    driver.findElement(txtNewTaskName).sendKeys("New Task");
    driver.findElement(btnSubmitNewTask).click();
  }

  public void closeTask() {
    driver.findElement(btnCloseTask).click();
  }
}
