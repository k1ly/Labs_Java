package by.belstu.it.lyskov.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class XMLValidator {

    public void validateXML(final String xmlFilePath, final String xsdFilePath) {
        try (InputStream xmlFile = new FileInputStream(xmlFilePath);
             InputStream xsdFile = new FileInputStream(xsdFilePath)) {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsdFile));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (SAXException | IOException e) {
            Logger logger = LogManager.getLogger();
            logger.error("Ошибка валидации XML файла с помощью XSD схемы!");
            throw new RuntimeException("Ошибка валидации XML файла!", e);
        }
    }
}
