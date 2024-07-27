package zxf.java.itextpdf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PdfFormHelperTests {
    public static void main(String[] args) throws IOException {
        testPdfFormInfo();
        testFillForm();
    }

    private static void testFillForm() throws IOException {
        try (InputStream formInputStream = PdfFormHelperTests.class.getResourceAsStream("/pdf/forms/OoPdfFormExample.pdf");
             InputStream fontInputStream = PdfFormHelperTests.class.getResourceAsStream("/pdf/font/arialuni.ttf")) {
            Map<String, String> formData = new HashMap<>();
            formData.put("Given Name Text Box", "Text 1");
            formData.put("Family Name Text Box", "Text 2");
            formData.put("Address 1 Text Box", "On");
            formData.put("Address 2 Text Box", "Text 2");
            formData.put("Postcode Text Box", "Text 2");
            formData.put("Country Combo Box", "US");
            formData.put("Height Formatted Field", "US");
            formData.put("City Text Box", "US");
            formData.put("Driving License Check Box", "On");
            formData.put("Favourite Colour List Box", "White");
            formData.put("Language 1 Check Box", "On");
            formData.put("Language 2 Check Box", "Off");
            formData.put("Language 3 Check Box", "On");
            formData.put("Language 4 Check Box", "On");
            formData.put("Language 5 Check Box", "On");
            formData.put("Gender List Box", "On");
            byte[] filledContent = PDFFormHelper.fillForm(formInputStream, fontInputStream, formData);
            Files.write(Paths.get("./output/filled.pdf"), filledContent);
        }
    }

    private static void testPdfFormInfo() throws IOException {
        try (InputStream inputStream = PdfFormHelperTests.class.getResourceAsStream("/pdf/forms/OoPdfFormExample.pdf")) {
            PDFFormHelper.printFormInfo(inputStream);
        }

//        try (InputStream inputStream = PdfFormHelperTests.class.getResourceAsStream("/pdf/forms/pdf-conversion-services.pdf")) {
//            PDFFormHelper.printFormInfo(inputStream);
//        }
    }
}
