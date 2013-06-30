package com.tkmtwo.util.org.dom4j;

import java.io.Writer;
import java.io.StringWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.InputSource;

import org.apache.commons.lang.BooleanUtils;
//import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xml.resolver.tools.ResolvingXMLReader;
import org.apache.xml.resolver.tools.CatalogResolver;

//import org.springframework.util.Assert;

import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.XMLWriter;

//import sarapi.arsys.core.ArsSchemaHelper;
//import sarapi.arsys.core.EnumPair;
import com.tkmtwo.util.java.lang.Strings;

public class Dom4JUtil
{

  protected final Log logger = LogFactory.getLog(getClass());

  
  public static SAXReader getSAXReader(boolean b)
  {
    org.xml.sax.XMLReader xr = new ResolvingXMLReader();
    SAXReader sr = new SAXReader(xr, b);
    return sr;
  }

  /*
  public static Document getDocument(String s)
    throws DocumentException
  {
    return getSAXReader(false).read(new ByteArrayInputStream(s.getBytes("UTF-8")));
  }
  */
  
  
  public static String toXmlString(Document doc)
    throws IOException
  {
    Writer writer = new StringWriter();
    XMLWriter xmlWriter = new XMLWriter(writer, OutputFormat.createPrettyPrint());
    xmlWriter.write(doc);
    xmlWriter.flush();
    return writer.toString();
  }



  public static Document getDocument(String s)
    throws DocumentException, FileNotFoundException, URISyntaxException
  {
    return getDocument(new File(new URI(s)));
  }
    
  public static Document getDocument(File f)
    throws DocumentException, FileNotFoundException
  {
    return getDocument(new FileInputStream(f));
  }
  public static Document getDocument(InputStream is)
    throws DocumentException
  {
    return getSAXReader(false).read(is);
  }
  public static Document getDocument(String docRepresentation, String encoding)
    throws DocumentException, UnsupportedEncodingException
  {
    //byte[] bs = docRepresentation.getBytes(encoding);
    ByteArrayInputStream bais = new ByteArrayInputStream(docRepresentation.getBytes(encoding));
    return getDocument(bais);
  }


  public static String childTextTrim(Node n, String s)
  {
    Element e = null;
    if((e = (Element)n.selectSingleNode(s)) == null) {
      return null;
    }
    String childTextTrim = e.getTextTrim();
    if (Strings.isBlank(childTextTrim)) {
      return null;
    }
    return childTextTrim;
  }
  
  public static String childText(Node n, String s)
  {
    Element e = null;
    if((e = (Element)n.selectSingleNode(s)) == null) {
      return null;
    }
    
    String childText = e.getText();
    if (Strings.isBlank(childText)) {
      return null;
    }
    return childText;
  }



  public static boolean toBoolean(Node n, String s)
  {
    return BooleanUtils.toBoolean(childTextTrim(n, s));
  }
  public static Boolean toBooleanObject(Node n, String s)
  {
    return BooleanUtils.toBooleanObject(childTextTrim(n, s));
  }


  public static Integer toInteger(Node n, String s)
  {
    Integer i = null;
    try {
      i = new Integer(childTextTrim(n, s));
    } catch (Exception ex) {
      i = null;
    }
    return i;
  }
  
  public static BigDecimal toBigDecimal(Node n, String s)
  {
    BigDecimal bd = null;
    try {
      bd = new BigDecimal(childTextTrim(n, s));
    } catch (Exception ex) {
      bd = null;
    }
    return bd;
  }
  
  
  
  
  public static void addIfNotBlank(Element parent, String childName, String childText)
  {
    if (Strings.isNotBlank(childText)) {
      parent
        .addElement(childName)
        .addText(childText);
    }
  }


  public static void addIfNotNull(Element parent, String childName, Integer childInteger)
  {
    if (childInteger != null) {
      parent
        .addElement(childName)
        .addText(childInteger.toString());
    }
  }
  
  
  public static void addIfTrue(Element parent, String childName, Boolean childBoolean)
  {
    if (BooleanUtils.toBoolean(childBoolean)) {
      parent
        .addElement(childName)
        .addText("true");
    }

  }
  
  
  












  public static Document styleDocument(Document document, 
                                       String stylesheet)
    throws IOException, TransformerConfigurationException, TransformerException
  {
    CatalogResolver catalogResolver = new CatalogResolver();
    System.out.println("XXXXXX NOW: " + catalogResolver.getCatalog().toString());
    System.out.println("XXXXXX NIW: "
                       + catalogResolver
                       .getCatalog()
                       .resolveURI("http://itsmconfig.accenture.com/xml/xsl/itsm-validation-report.xsl"));

    TransformerFactory factory = TransformerFactory.newInstance();
    factory.setURIResolver(catalogResolver);

    System.out.println("FACTORY IS A: " + factory.getClass().toString());

    ResolvingXMLReader xmlReader = new ResolvingXMLReader();
    xmlReader.setEntityResolver(catalogResolver);
    System.out.println("XXXXXX NIX: "
                       + xmlReader
                       .getCatalog()
                       .resolveURI("http://itsmconfig.accenture.com/xml/xsl/itsm-validation-report.xsl"));
    System.out.println("XXXXXX NIX2: "
                       + xmlReader
                       .getCatalog()
                       .resolveSystem("http://itsmconfig.accenture.com/xml/xsl/itsm-validation-report.xsl"));



    SAXSource saxSource = new SAXSource(new InputSource(stylesheet));
    //SAXSource saxSource = new SAXSource(new InputSource(xmlReader.getCatalog().resolveURI(stylesheet)));
    saxSource.setXMLReader(xmlReader);
    //Transformer transformer = factory.newTransformer(saxSource);
    //Transformer transformer = factory.newTransformer(new StreamSource(new File(xmlReader.getCatalog().resolveURI(stylesheet))));


    Transformer transformer = factory.newTransformer(new StreamSource(new File(stylesheet)));

    
    System.out.println("TRANSFORMER IS A: " + transformer.getClass().toString());

    
    DocumentSource source = new DocumentSource( document );
    DocumentResult result = new DocumentResult();
    transformer.transform( source, result );
    
    Document transformedDoc = result.getDocument();
    return transformedDoc;
  }

  @SuppressWarnings("unchecked")
  public static String extractHtmlBodyToString(Document document)
    throws IOException
  {
    Writer writer = new StringWriter();
    HTMLWriter htmlWriter = new HTMLWriter(writer, OutputFormat.createPrettyPrint());

    /*
    htmlWriter.write(document);
    htmlWriter.flush();
    return writer.toString();
    */

    /*    
    Element rootElement = document.getRootElement();
    List<Node> nodes = (List<Node>) rootElement.selectNodes("body/*");
    */


    //Element rootElement = document.getRootElement();
    List<Node> nodes = (List<Node>) document.selectNodes("/html/body/*");

    for (Node node : nodes) {
      htmlWriter.write(node);
    }
    htmlWriter.flush();
    return writer.toString();

  }


  
  
}

