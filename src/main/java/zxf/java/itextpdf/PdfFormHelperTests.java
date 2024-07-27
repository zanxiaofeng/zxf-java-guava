package zxf.java.itextpdf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
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
            formData.put("Address 1 Text Box", "Text 3");
            formData.put("Address 2 Text Box", "Text 4");
            formData.put("House nr Text Box", "Text 5");
            formData.put("Postcode Text Box", "Text 6");
            formData.put("Country Combo Box", "US");
            formData.put("Height Formatted Field", "Text 7");
            formData.put("City Text Box", "Text 8");
            formData.put("Driving License Check Box", "On");
            formData.put("Favourite Colour List Box", "White");
            formData.put("Language 1 Check Box", "On");
            formData.put("Language 2 Check Box", "Off");
            formData.put("Language 3 Check Box", "On");

            formData.put("Language 4 Check Box", "On");
            formData.put("Language 5 Check Box", "On");
            formData.put("Gender List Box", "Man");
            byte[] filledContent = PDFFormHelper.fillForm(formInputStream, fontInputStream, formData);
            Files.write(Paths.get("./output/OoPdfFormExample-filled.pdf"), filledContent);
        }

        try (InputStream formInputStream = PdfFormHelperTests.class.getResourceAsStream("/pdf/forms/pdf-conversion-services.pdf");
             InputStream fontInputStream = PdfFormHelperTests.class.getResourceAsStream("/pdf/font/arialuni.ttf")) {
            Map<String, String> formData = new HashMap<>();
            formData.put("Customer Name", "Text 1");
            formData.put("Customer Address2", "Text 2");
            formData.put("Customer Mobile", "Text 3");
            formData.put("Customer Address1", "Text 4");
            formData.put("Customer email", "Text 5");
            //How to set???
            formData.put("Returning Customer", "Text 6");
            //How to set???
            formData.put("Returning Customer.1", "On");
            //How to set???
            formData.put("Returning Customer.2", "On");
            formData.put("Customer Website", "Text 9");
            formData.put("Pin Code", "Text 10");
            formData.put("Amount Paid Part 1", "Text 11");
            formData.put("Amount Paid Part 2", "Text 12");
            formData.put("Amount Paid Total", "Text 13");
            formData.put("Enquiry For", "Text 14");
            formData.put("Reference", "Text 15");
            formData.put("Customer Bank Name", "Text 16");
            formData.put("Bank Branch", "Text 17");
            formData.put("Account Type", "Text 18");
            formData.put("Account Number", "Text 19");
            formData.put("Fordm Date", "Text 20");

            byte[] filledContent = PDFFormHelper.fillForm(formInputStream, fontInputStream, formData, Collections.singleton("Customer Signature"));
            Files.write(Paths.get("./output/pdf-conversion-services-filled.pdf"), filledContent);
        }
    }

    private static void testPdfFormInfo() throws IOException {
        try (InputStream inputStream = PdfFormHelperTests.class.getResourceAsStream("/pdf/forms/OoPdfFormExample.pdf")) {
            PDFFormHelper.printFormInfo(inputStream);
        }

        try (InputStream inputStream = PdfFormHelperTests.class.getResourceAsStream("/pdf/forms/pdf-conversion-services.pdf")) {
            PDFFormHelper.printFormInfo(inputStream);
        }
    }
}
