package org.codelogger.utils;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.codelogger.utils.beans.StorageComponent;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtils {

    public static Document getXML(final String xmlPath) {

        Document document = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(new FileInputStream(xmlPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    public static StorageComponent<String, String> getXMLData(final String xmlPath) {

        Document document = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(new FileInputStream(xmlPath));
            return parse(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static StorageComponent<String, String> parse(final Document xml) {

        StorageComponent<String, String> xmlMap = new StorageComponent<String, String>();
        Element documentElement = xml.getDocumentElement();
        if (documentElement.hasChildNodes()) {

            NodeList childNodes = documentElement.getChildNodes();
            parse(xmlMap, childNodes);
        }

        return xmlMap;
    }

    private static void parse(final StorageComponent<String, String> xmlMap, final NodeList nodeList) {

        int nodeListLength = nodeList.getLength();
        for (int index = 0; index < nodeListLength; index++) {
            Node node = nodeList.item(index);
            short nodeType = node.getNodeType();
            if (nodeType != 1) {
                continue;
            }
            String nodeName = node.getNodeName();
            String textContent = node.getTextContent();
            String nodeValue = node.getNodeValue();
            if (node.hasAttributes()) {
                StorageComponent<String, String> storageComponent = xmlMap
                        .getStorageComponent(nodeName);
                parse(storageComponent.getStorageComponent(String.valueOf(storageComponent
                        .componentSize())), node.getAttributes());
            }
            if (node.hasChildNodes()) {
                if (node.getChildNodes().getLength() == 1
                        && node.getChildNodes().item(0).getAttributes() == null) {
                    xmlMap.put(nodeValue == null ? textContent : nodeValue, nodeName);
                } else {
                    StorageComponent<String, String> storageComponent = xmlMap
                            .getStorageComponent(nodeName);
                    parse(storageComponent.getStorageComponent(String.valueOf(Math.max(
                            storageComponent.componentSize() - 1, 0))), node.getChildNodes());
                }
            }
        }
    }

    private static void parse(final StorageComponent<String, String> xmlMap,
            final NamedNodeMap namedNodeMap) {

        int attributesLength = namedNodeMap.getLength();
        for (int index = 0; index < attributesLength; index++) {
            Node node = namedNodeMap.item(index);
            String nodeName = node.getNodeName();
            String nodeValue = node.getNodeValue();
            if (node.hasAttributes()) {
                StorageComponent<String, String> storageComponent = xmlMap
                        .getStorageComponent(nodeName);
                parse(storageComponent, node.getAttributes());
            }
            if (nodeValue == null) {
                nodeValue = node.getTextContent();
            }
            xmlMap.put(nodeValue, nodeName);
        }
    }
}
