package zxf.java.tika;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.FileInputStream;
import java.io.InputStream;

public class TikaMetadataExtraction {
    public static void main(String[] args) throws Exception {
        Parser parser = new AutoDetectParser();

        // --- Extract metadata from PDF ---
        System.out.println("=== PDF Metadata ===");
        Metadata pdfMeta = new Metadata();
        try (InputStream stream = new FileInputStream("docs/Vavr User Guide.pdf")) {
            parser.parse(stream, new BodyContentHandler(), pdfMeta, new ParseContext());
        }

        // Common metadata properties
        System.out.println("Title:    " + pdfMeta.get(TikaCoreProperties.TITLE));
        System.out.println("Author:   " + pdfMeta.get(TikaCoreProperties.CREATOR));
        System.out.println("Created:  " + pdfMeta.get(TikaCoreProperties.CREATED));
        System.out.println("Modified: " + pdfMeta.get(TikaCoreProperties.MODIFIED));
        System.out.println("Format:   " + pdfMeta.get(Metadata.CONTENT_TYPE));
        System.out.println("Pages:    " + pdfMeta.get("xmpTPages"));

        // --- Extract metadata from YAML ---
        System.out.println("\n=== YAML Metadata ===");
        Metadata yamlMeta = new Metadata();
        try (InputStream stream = new FileInputStream("src/main/resources/yaml/example-1.yml")) {
            parser.parse(stream, new BodyContentHandler(), yamlMeta, new ParseContext());
        }
        System.out.println("Format:   " + yamlMeta.get(Metadata.CONTENT_TYPE));
        System.out.println("Encoding: " + yamlMeta.get(Metadata.CONTENT_ENCODING));

        // --- Extract metadata from CSV ---
        System.out.println("\n=== CSV Metadata ===");
        Metadata csvMeta = new Metadata();
        try (InputStream stream = new FileInputStream("output/output.csv")) {
            parser.parse(stream, new org.apache.tika.sax.BodyContentHandler(), csvMeta, new ParseContext());
        }
        System.out.println("Format:   " + csvMeta.get(Metadata.CONTENT_TYPE));
        System.out.println("Encoding: " + csvMeta.get(Metadata.CONTENT_ENCODING));

        // --- Extract metadata from JPG ---
        System.out.println("\n=== JPG Metadata ===");
        Metadata jpgMeta = new Metadata();
        try (InputStream stream = new FileInputStream("src/main/resources/img/IMG_20240723_081450.jpg")) {
            parser.parse(stream, new BodyContentHandler(), jpgMeta, new ParseContext());
        }
        System.out.println("Format:    " + jpgMeta.get(Metadata.CONTENT_TYPE));
        System.out.println("Width:     " + jpgMeta.get(Metadata.IMAGE_WIDTH));
        System.out.println("Height:    " + jpgMeta.get(Metadata.IMAGE_LENGTH));
        System.out.println("Make:      " + jpgMeta.get(Metadata.EQUIPMENT_MAKE));
        System.out.println("Model:     " + jpgMeta.get(Metadata.EQUIPMENT_MODEL));
        System.out.println("Date:      " + jpgMeta.get(TikaCoreProperties.CREATED));
        System.out.println("Latitude:  " + jpgMeta.get(Metadata.LATITUDE));
        System.out.println("Longitude: " + jpgMeta.get(Metadata.LONGITUDE));

        // --- Extract metadata from TIFF ---
        System.out.println("\n=== TIFF Metadata ===");
        Metadata tiffMeta = new Metadata();
        try (InputStream stream = new FileInputStream("src/main/resources/img/IMG_20240723_081450-output.tiff")) {
            parser.parse(stream, new BodyContentHandler(), tiffMeta, new ParseContext());
        }
        System.out.println("Format:    " + tiffMeta.get(Metadata.CONTENT_TYPE));
        System.out.println("Width:     " + tiffMeta.get(Metadata.IMAGE_WIDTH));
        System.out.println("Height:    " + tiffMeta.get(Metadata.IMAGE_LENGTH));
        System.out.println("Bits/Samp: " + tiffMeta.get(Metadata.BITS_PER_SAMPLE));
        System.out.println("Resolution:" + tiffMeta.get(Metadata.RESOLUTION_HORIZONTAL));

        // --- List all metadata properties ---
        System.out.println("\n=== All PDF Metadata Properties ===");
        for (String name : pdfMeta.names()) {
            System.out.printf("  %-30s = %s%n", name, pdfMeta.get(name));
        }

        System.out.println("\n=== All JPG Metadata Properties ===");
        for (String name : jpgMeta.names()) {
            System.out.printf("  %-30s = %s%n", name, jpgMeta.get(name));
        }

        System.out.println("\n=== All TIFF Metadata Properties ===");
        for (String name : tiffMeta.names()) {
            System.out.printf("  %-30s = %s%n", name, tiffMeta.get(name));
        }
    }
}
