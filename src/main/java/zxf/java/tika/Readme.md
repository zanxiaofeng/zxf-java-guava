# Description
- Apache Tika is an open-source content analysis library that provides a set of classes for detecting and extracting text content from different file formats.

# Demos

| Class | Description |
|---|---|
| `TikaTests` | Encoding detection using `UniversalEncodingDetector` (byte array → charset) |
| `TikaMimeDetection` | File type / MIME detection via `Tika.detect()` (files, byte arrays, with/without filename hints) |
| `TikaTextExtraction` | Text extraction from PDF/CSV/YAML using `Tika.parseToString()` and `AutoDetectParser` with `BodyContentHandler` |
| `TikaMetadataExtraction` | Metadata extraction (title, author, dates, page count, content type, encoding) from PDF/YAML/CSV |

