package reporting;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        try {
            String projectPath = System.getProperty("user.dir");
            String reportFolder = projectPath + "/extent-reports";
            Path reportPath = Paths.get(reportFolder);
            if (!Files.exists(reportPath)) {
                Files.createDirectories(reportPath);
            }

            String reportFile = reportFolder + "/BookswagonReport.html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportFile);
            extent = new ExtentReports();
            extent.attachReporter(spark);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
