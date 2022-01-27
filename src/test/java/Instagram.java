import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Instagram {
    protected WebDriver driver;
    public static String url = "https://www.instagram.com/";
    UserInf userInf = new UserInf();

    @Before
    public void setUp() {

        try {

            ChromeOptions capabilities = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\SedatCan\\instagram\\ChromeWebDriver\\chromedriver.exe");
            driver = new ChromeDriver(capabilities);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            System.out.println("Driver Başlatıldı");

        } catch (Exception ex) {

            ex.getMessage();
            System.out.println("Driver Başlatılamadı");

        }
    }

    @Test
    public void follower() throws InterruptedException {
        driver.get(url);
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[1]/div/label/input")).sendKeys(userInf.userName);
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[2]/div/label/input")).sendKeys(userInf.password);
        driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[3]/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/div/div/div/button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(" /html/body/div[5]/div/div/div/div[3]/button[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[3]/div/div[6]/span/img")).click();
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/nav/div[2]/div/div/div[3]/div/div[6]/div[2]/div[2]/div[2]/a[1]/div")).click();
        driver.findElement(By.xpath("//*[@id=\"react-root\"]/section/main/div/header/section/ul/li[2]/a")).click();
        String z = driver.findElement(By.xpath("/html/body/div[1]/section/main/div/header/section/ul/li[2]/a/span")).getText();
        int takipci = Integer.parseInt(z);
        String y = driver.findElement(By.xpath("/html/body/div[1]/section/main/div/header/section/ul/li[3]/a/span")).getText();
        int takip = Integer.parseInt(y);
        List<WebElement> usersList = new ArrayList<>();
        List<String> usersListString = new ArrayList<>();
        List<WebElement> takipList = new ArrayList<>();
        List<String> takipListString = new ArrayList<>();

        WebElement webElementx = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]"));

        int spaceCount = 1;
        Actions actions = new Actions(driver);
        int userCount = 0;
        int takipCount = 0;

        while (takipci != userCount) {
            try {
                driver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/ul/div")).click();
                for (int i = 0; i < spaceCount; i++) {
                    actions.moveToElement(webElementx).sendKeys(Keys.ARROW_DOWN).build().perform();
                }
                spaceCount++;
                List<WebElement> webElementx2 = driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/ul/div/li"));
                userCount = webElementx2.size();
                if (takipci == userCount) {
                    System.out.println("bitti");
                    List<WebElement> users = driver.findElements(By.xpath("/html/body/div[6]/div/div/div[2]/ul/div/li/div/div[1]/div[2]/div[1]/span/a"));
                    for (WebElement webElement : users) {
                        if (!usersList.contains(webElement)) {
                            usersList.add(webElement);
                            usersListString.add(webElement.getText());
                        }

                    }

                    driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/div/div[2]/button")).click();
                    driver.findElement(By.xpath("/html/body/div[1]/section/main/div/header/section/ul/li[3]/a")).click();

                    while (takip != takipCount) {
                        try {
                            driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]/ul/div")).click();
                            WebElement webElementxtakip = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[3]"));
                            for (int i = 0; i < spaceCount; i++) {
                                actions.moveToElement(webElementxtakip).sendKeys(Keys.ARROW_DOWN).build().perform();
                            }
                            spaceCount++;
                            List<WebElement> webElementx2takip = driver.findElements(By.xpath("/html/body/div[6]/div/div/div[3]/ul/div/li"));
                            takipCount = webElementx2takip.size();
                            if (takip == takipCount) {
                                System.out.println("bitti");
                                List<WebElement> userstakip = driver.findElements(By.xpath("/html/body/div[6]/div/div/div[3]/ul/div/li/div/div[1]/div[2]/div[1]/span/a"));
                                for (WebElement webElement : userstakip) {
                                    if (!takipList.contains(webElement)) {
                                        takipList.add(webElement);
                                        takipListString.add(webElement.getText());
                                    }

                                }
                                for (int i = 0; i < takipListString.size(); i++) {
                                    if (!usersListString.contains(takipListString.get(i))) {
                                        System.out.println(takipListString.get(i));
                                    }
                                }
                            }

                        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                            System.out.println("catch");

                            ex.printStackTrace();
                        }
                    }
                }

            } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                System.out.println("catch");

                ex.printStackTrace();
            }

        }
        driver.quit();
    }
}
