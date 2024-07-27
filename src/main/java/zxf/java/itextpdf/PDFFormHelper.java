package zxf.java.itextpdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public interface PDFFormHelper {

    static byte[] fillForm(InputStream pdfTemplateStream, InputStream pdfFontStream, Map<String, String> formData) throws IOException {
        return fillForm(pdfTemplateStream, pdfFontStream, formData, Collections.emptySet());
    }

    static byte[] fillForm(InputStream pdfTemplateStream, InputStream pdfFontStream, Map<String, String> formData, Set<String> excludedFields) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (PdfDocument pdfDocument = new PdfDocument(new PdfReader(pdfTemplateStream), new PdfWriter(outputStream))) {
            FontProgram fontProgram = FontProgramFactory.createFont(IOUtils.toByteArray(pdfFontStream));
            PdfFont pdfFont = PdfFontFactory.createFont(fontProgram, PdfEncodings.IDENTITY_H, true);

            PdfAcroForm pdfForm = PdfAcroForm.getAcroForm(pdfDocument, true);
            Map<String, PdfFormField> pdfFormFields = pdfForm.getFormFields();

            for (Map.Entry<String, String> formDataFiled : formData.entrySet()) {
                PdfFormField pdfFormField = pdfFormFields.get(formDataFiled.getKey());
                if (pdfFormField != null) {
                    fillDataWithStyle(pdfFormField, formDataFiled.getValue(), pdfFont);
                }
            }

            for (String fieldName : formData.keySet()) {
                if (!excludedFields.contains(fieldName)) {
                    pdfForm.partialFormFlattening(fieldName);
                }
            }

            pdfForm.flattenFields();
        }

        // Must below the pdfDocument close
        return outputStream.toByteArray();
    }

    static void printFormInfo(InputStream pdfTemplateStream) throws IOException {
        try (PdfDocument pdfDocument = new PdfDocument(new PdfReader(pdfTemplateStream))) {
            PdfAcroForm pdfForm = PdfAcroForm.getAcroForm(pdfDocument, true);
            Map<String, PdfFormField> pdfFormFields = pdfForm.getFormFields();

            for (Map.Entry<String, PdfFormField> formFiled : pdfFormFields.entrySet()) {
                System.out.println(formFiled.getValue().getClass());
                System.out.println("Name: " + formFiled.getKey());
                System.out.println("Type: " + formFiled.getValue().getFormType());
                System.out.println("Style: " + formFiled.getValue().getDefaultStyle());
                PdfObject defaultValue = formFiled.getValue().getDefaultValue();
                String defaultValueClass = defaultValue == null ? "" : defaultValue.getClass().getName();
                System.out.println("Default Value: " + defaultValue + ", " + defaultValueClass);
                System.out.println();
            }
        }
    }


    static void fillDataWithStyle(PdfFormField pdfFormField, String value, PdfFont pdfFont) {
        PdfName fieldType = pdfFormField.getFormType();

        pdfFormField.setValue(value).setFont(pdfFont).setReadOnly(true);
    }
}
