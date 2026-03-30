package utils;

import com.aventstack.extentreports.*;

public class ExtentTestManager {

    public static ExtentTest test;

    public static void startTest(String name) {
        test = ExtentManager.getInstance().createTest(name);
    }
}
