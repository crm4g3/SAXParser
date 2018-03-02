/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author codyioslaptop
 */
public class SAXLoader {
    
    private static Element root;
    
    private static Element currentElement;
    private static Stack<Element> stack;
    private static String outPut;
    
    public static Element load(File file) throws Exception {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                @Override
                public void startDocument() throws SAXException {
                    root = null;
                    stack = new Stack<>();
                }
                 
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    Element node = new Element();
                    node.name = qName;                                      
                    outPut += node.name;
                    outPut += "\n";
                    node.attributes = new ArrayList();

                    for (int i = 0; i < attributes.getLength(); i++) {
                       Attribute attribute = new Attribute(attributes.getQName(i),attributes.getValue(i));
                       node.attributes.add(attribute);
                    }
                    stack.push(node);
                    
                    if (currentElement != null) {
                        if (currentElement.children == null) {
                            currentElement.children = new ArrayList();
                        }
                            currentElement.children.add(node);
                    }
                    currentElement = node; 
                 }
                 
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    currentElement.value = new String(ch, start, length);
                    outPut += " ";
                    outPut += currentElement.value;
                    outPut += "\n";
                    System.out.println(currentElement.value);
                }
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    Element topElement = stack.pop();
                    if (topElement != null) {
                        if (stack.empty()) {
                            root = topElement;
                        } else {
                            currentElement = stack.peek();
                        }
                    }
                }
             };
             
            saxParser.parse(file.getAbsoluteFile(), handler);
             
        } catch (Exception e) {
            throw e;
        }
        return root;
    }
    
    public static String getOutPut(){
        return outPut;
    }
}


