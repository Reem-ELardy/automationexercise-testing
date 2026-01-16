package utils;

import io.qameta.allure.Feature;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class AllureReportChangesListener implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> suites) {
        String projectName = System.getProperty("allure.suite.name", "DefaultProjectName");

        for (XmlSuite suite : suites) {
            suite.setName(projectName);

            List<XmlTest> originalTests = new ArrayList<>(suite.getTests());
            suite.getTests().clear();

            for (XmlTest test : originalTests) {
                List<XmlClass> classes = test.getClasses();

                if (classes.size() > 1) {
                    // Create separate XmlTest for each class
                    for (XmlClass clazz : classes) {
                        XmlTest newTest = new XmlTest(suite);
                        newTest.setName(getFeatureName(clazz));
                        List<XmlClass> singleClassList = new ArrayList<>();
                        singleClassList.add(clazz);
                        newTest.setXmlClasses(singleClassList);
                    }
                } else {
                    // Single class, just rename test based on feature
                    XmlClass clazz = classes.get(0);
                    test.setName(getFeatureName(clazz));
                    suite.getTests().add(test);
                }
            }
        }
    }

    private String getFeatureName(XmlClass xmlClass) {
        try {
            Class<?> clazz = Class.forName(xmlClass.getName()); // load the actual class
            Feature feature = clazz.getAnnotation(Feature.class);
            if (feature != null) return feature.value();
            return clazz.getSimpleName(); // fallback to real class simple name
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return xmlClass.getName(); // fallback to full name if class not found
        }
    }
}
