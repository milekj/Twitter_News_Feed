package agh.tai.twitter_news_feed.automation_tests;

import agh.tai.twitter_news_feed.authentication.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

@Component
public class Selenium {

    private static final String FILE = "credentials_twitter.properties";

    private static final String EMAIL = "twitter.email";

    private static final String PASSWORD = "twitter.password";

    private static Credentials credentials = new Credentials();

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            Map<String, String> map = new HashMap<>();
            while (Objects.nonNull(line = reader.readLine())) {
                String[] split = line.split("=");
                map.put(split[0], split[1]);
            }

            credentials.setEmail(map.get(EMAIL));
            credentials.setPassword(map.get(PASSWORD).toCharArray());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sleep() throws InterruptedException {
        sleepWithTime(1000);
    }

    private static void sleepWithTime(int time) throws InterruptedException {
        Thread.sleep(time);
    }

    private WebDriver goToLoginTwitterPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//        driver.get("localhost:8080");
        driver.get("https://twitter-news-feed.herokuapp.com/");
        sleep();
        driver.findElement(By.className("login-image")).click();
        sleep();
        return driver;
    }

    public void testInvalidLoginCredentials() throws InterruptedException {
        WebDriver driver = goToLoginTwitterPage();
        driver.findElement(By.id("username_or_email")).sendKeys(credentials.getEmail());
        sleep();
        driver.findElement(By.id("password")).sendKeys((Arrays.toString(credentials.getPassword()) + "invalid"));
        sleep();
        driver.findElement(By.id("allow")).click();
        sleepWithTime(5000);
        driver.quit();
        System.out.println("Invalid password");
    }

    public void test() throws InterruptedException {
        WebDriver driver = goToLoginTwitterPage();
        driver.findElement(By.id("username_or_email")).sendKeys(credentials.getEmail());
        sleep();
        driver.findElement(By.id("password")).sendKeys(new String(credentials.getPassword()));
        sleep();
        driver.findElement(By.id("allow")).click();
        sleep();
        driver.findElement(By.className("menu-interest")).click();
        sleep();
        driver.findElement(By.className("input-field")).sendKeys("Trump");
        sleep();
        driver.findElement(By.className("add-interest")).click();
        sleep();
        driver.findElement(By.xpath("//*[text()='Trump']")).click();
        sleepWithTime(5000);
        driver.findElement(By.className("menu-news")).click();
        sleep();
        driver.findElement(By.className("news-number")).clear();
        sleep();
        driver.findElement(By.className("news-number")).sendKeys("1");
        sleep();
        driver.findElement(By.className("news-number-click")).click();
        scrollDown(driver, 20);
        sleepWithTime(5000);
        scrollTop(driver);
        sleep();
        driver.findElement(By.className("news-number")).clear();
        sleep();
        driver.findElement(By.className("news-number")).sendKeys("5");
        sleep();
        driver.findElement(By.className("news-number-click")).click();
        scrollDown(driver, 100);
        sleepWithTime(3000);
        scrollTop(driver);
        sleep();
        driver.findElement(By.className("menu-info")).click();
        sleep();
        driver.findElement(By.className("menu-logout")).click();
        sleepWithTime(5000);
        driver.quit();
        System.out.println("Test completed.");
    }

    private void scrollDown(WebDriver driver, int bound) {
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        IntStream.range(0, bound).forEach(i -> {
                    jsx.executeScript("window.scrollBy(" + i + "," + (i + 1) + ")", "");
                    handleSleep();
                }
        );
    }

    private void handleSleep() {
        try {
            sleepWithTime(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void scrollTop(WebDriver driver) {
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0, 0)", "");

    }

}
