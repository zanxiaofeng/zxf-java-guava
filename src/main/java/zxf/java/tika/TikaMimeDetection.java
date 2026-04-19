package zxf.java.tika;

import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TikaMimeDetection {
    public static void main(String[] args) throws IOException {
        Tika tika = new Tika();

        // Detect MIME type from project resource files
        File[] resourceFiles = {
                new File("docs/Vavr User Guide.pdf"),
                new File("output/output.csv"),
                new File("src/main/resources/yaml/example-1.yml"),
                new File("src/main/resources/pdf/font/arialuni.ttf"),
                new File("pom.xml"),
                new File("src/main/resources/mappings/abc-200.json")
        };

        System.out.println("=== File MIME Detection ===");
        for (File file : resourceFiles) {
            if (file.exists()) {
                String mimeType = tika.detect(file);
                System.out.printf("%-55s -> %s%n", file.getPath(), mimeType);
            }
        }

        // Detect from byte array (without file name hint)
        System.out.println("\n=== Byte Array Detection ===");
        String htmlContent = "<html><body><h1>Hello</h1></body></html>";
        String detectedHtml = tika.detect(htmlContent.getBytes());
        System.out.println("HTML content    -> " + detectedHtml);

        byte[] pdfHeader = {(byte) 0x25, (byte) 0x50, (byte) 0x44, (byte) 0x46, 0x2D}; // %PDF-
        String detectedPdf = tika.detect(pdfHeader);
        System.out.println("PDF header      -> " + detectedPdf);

        byte[] zipHeader = {(byte) 0x50, (byte) 0x4B, 0x03, 0x04}; // PK..
        String detectedZip = tika.detect(zipHeader);
        System.out.println("ZIP header      -> " + detectedZip);

        // Detect from byte array with filename hint (more accurate)
        System.out.println("\n=== Detection with Filename Hint ===");
        byte[] yamlBytes = Files.readAllBytes(Path.of("src/main/resources/yaml/example-1.yml"));
        String withoutHint = tika.detect(yamlBytes);
        String withHint = tika.detect(yamlBytes, "example-1.yml");
        System.out.println("YAML without hint: " + withoutHint);
        System.out.println("YAML with hint:    " + withHint);
    }
}
