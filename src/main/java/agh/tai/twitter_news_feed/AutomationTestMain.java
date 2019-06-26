package agh.tai.twitter_news_feed;

import agh.tai.twitter_news_feed.automation_tests.Selenium;

public class AutomationTestMain {

    public static void main(String[] args) throws InterruptedException {
        Selenium selenium = new Selenium();
        selenium.testInvalidLoginCredentials();
        Selenium.sleep();
        selenium.test();
    }
}
