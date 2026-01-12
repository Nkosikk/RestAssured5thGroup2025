package ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.Test;

import java.io.File;

public class ExtentReportManager {

    //Define where the report will be saved
    // dir means directory
    private static String reportDir=System.getProperty("user.dir")+"/Reports/sauceDemo.html";
    //Create an Instance of an extent report
    private static ExtentReports extentReports;
    private static ExtentSparkReporter extentSparkReporter;

    //when its not a void method we need a return statement
    // NB!! whatever is on your right is assigning something to the left code
    public static ExtentReports extentReports(){
        extentReports = new ExtentReports();
        extentSparkReporter = new ExtentSparkReporter(new File(reportDir));
        extentReports.attachReporter(extentSparkReporter);

        extentSparkReporter.config().setDocumentTitle("Extent Report");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setReportName("Sauce Demo");

        //helps you understand which type of operating system you using to execute macbook or windows
        extentReports.setSystemInfo("OS" , System.getProperty("os.name"));
        extentReports.setSystemInfo("Execution Machine" , System.getProperty("user.name"));

        return extentReports;

    }


    @Test
    public void test(){
        System.out.println(reportDir);
    }

}
