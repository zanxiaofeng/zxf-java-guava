package zxf.java.tika;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TikaTextExtraction {
    public static void main(String[] args) throws IOException, TikaException {
        // --- Approach 1: Simple Tika facade ---
        System.out.println("=== Tika Facade (parseToString) ===");
        Tika tika = new Tika();
        String pdfText = tika.parseToString(new File("docs/Vavr User Guide.pdf"));
        System.out.println("PDF text (first 500 chars):");
        System.out.println(pdfText.substring(0, Math.min(500, pdfText.length())));
        System.out.println("...");

        String csvText = tika.parseToString(new File("output/output.csv"));
        System.out.println("\nCSV text:\n" + csvText);

        String yamlText = tika.parseToString(new File("src/main/resources/yaml/example-1.yml"));
        System.out.println("YAML text:\n" + yamlText);

        // --- Approach 2: AutoDetectParser with BodyContentHandler ---
        System.out.println("\n=== AutoDetectParser with Content Handler ===");
        Parser parser = new AutoDetectParser();
        // BodyContentHandler default write limit is 100k chars; -1 means unlimited
        BodyContentHandler handler = new BodyContentHandler(-1);
        ParseContext context = new ParseContext();
        context.set(Parser.class, parser);

        try (InputStream stream = new FileInputStream("docs/Vavr User Guide.pdf")) {
            parser.parse(stream, handler, new org.apache.tika.metadata.Metadata(), context);
            String content = handler.toString();
            System.out.println("PDF extracted text length: " + content.length() + " chars");
            System.out.println("First 300 chars:");
            System.out.println(content.substring(0, Math.min(300, content.length())));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        // --- Approach 3: Limited write limit handler ---
        System.out.println("\n=== Limited Write Limit Handler ===");
        BodyContentHandler limitedHandler = new BodyContentHandler(200);
        try (InputStream stream = new FileInputStream("docs/Vavr User Guide.pdf")) {
            parser.parse(stream, limitedHandler, new org.apache.tika.metadata.Metadata(), context);
            System.out.println("Limited extraction (max 200 chars):");
            System.out.println(limitedHandler.toString());
        } catch (SAXException e) {
            // BodyContentHandler throws this when write limit is exceeded
            System.out.println("Write limit reached (expected): " + e.getMessage());
        }
    }
}
