package zxf.java.itextpdf;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class PDFCreator {
    public byte[] fillForm(InputStream pdfTemplateStream, InputStream pdfFontStream, Map<String, String> formData) throws IOException {

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PdfDocument pdfDocument = new PdfDocument(new PdfReader(pdfTemplateStream), new PdfWriter(outputStream))) {

            FontProgram fontProgram = FontProgramFactory.createFont(IOUtils.toByteArray(pdfFontStream));
            PdfFont pdfFont = PdfFontFactory.createFont(fontProgram, PdfEncodings.IDENTITY_H, true);

            PdfAcroForm pdfForm = PdfAcroForm.getAcroForm(pdfDocument, true);
            Map<String, PdfFormField> pdfFormFields = pdfForm.getFormFields();

            for (Map.Entry<String, String> formDataFiled : formData.entrySet()) {
                PdfFormField pdfFormField = pdfFormFields.get(formDataFiled.getKey());
                if (pdfFormField != null) {
                    pdfFormField.setValue(formDataFiled.getValue()).setFont(pdfFont).setReadOnly(true);
                }
            }

            pdfForm.flattenFields();
            return outputStream.toByteArray();
        }
    }
}
