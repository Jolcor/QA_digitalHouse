package reports;

import com.aventstack.extentreports.ExtentReports;

public class ExtentFactory {
    public static ExtentReports getInstance() {
        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("OS", "Windows");// sistema operativo
        extent.setSystemInfo("Navegador", "Chrome"); // explorador qe se va usar
        extent.setSystemInfo("Ambiente", "QA"); // ambiente que siempre es el QA
        extent.setSystemInfo("Selenium WebDriver", "4.25.0"); // ambiente que siempre es el QA
        return extent;
    }
}
