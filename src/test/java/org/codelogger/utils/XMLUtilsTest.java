package org.codelogger.utils;

import static org.codelogger.utils.PrintUtils.println;

import org.codelogger.utils.XMLUtils;
import org.codelogger.utils.beans.StorageComponent;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtilsTest {

    @Test
    public void getXML() {

        Document xml = XMLUtils.getXML("FileUtilsTest/test.xml");

        StorageComponent<String, String> parse = parse(xml);
        println("================================");
        println(parse);
    }

    private StorageComponent<String, String> parse(final Document xml) {

        println("=========begain to parse xml============");
        StorageComponent<String, String> xmlMap = new StorageComponent<String, String>();
        Element documentElement = xml.getDocumentElement();
        String nodeName = documentElement.getNodeName();
        println(String.format("%s:%s", "getNodeName", nodeName));
        println("===parse node " + nodeName + "===");
        short nodeType = documentElement.getNodeType();
        println(String.format("%s:%s", "getNodeType", nodeType));

        if (documentElement.hasChildNodes()) {

            NodeList childNodes = documentElement.getChildNodes();
            parse(xmlMap, childNodes, nodeName);
        }

        return xmlMap;
    }

    private void parse(final StorageComponent<String, String> xmlMap, final NodeList nodeList,
            final String parentName) {

        println("=========begain to parse nodeList fro " + parentName + "============");
        int nodeListLength = nodeList.getLength();
        println(String.format("%s:%s", "nodeList size", nodeListLength));
        for (int index = 0; index < nodeListLength; index++) {
            Node node = nodeList.item(index);
            short nodeType = node.getNodeType();
            if (nodeType != 1) {
                continue;
            }
            String nodeName = node.getNodeName();
            println(String.format("%s:%s", "getNodeName", nodeName));
            println("===parse node " + nodeName + "===");
            println(String.format("%s:%s", "getNodeType", nodeType));
            String textContent = node.getTextContent();
            println(String.format("%s:%s", "getTextContent", textContent));
            String nodeValue = node.getNodeValue();
            println(String.format("%s:%s", "getNodeValue", nodeValue));
            if (node.hasAttributes()) {
                println("===hasAttributes for node " + nodeName + "===");
                StorageComponent<String, String> storageComponent = xmlMap
                        .getStorageComponent(nodeName);
                parse(storageComponent.getStorageComponent(String.valueOf(storageComponent
                        .componentSize())), node.getAttributes());
            }
            if (node.hasChildNodes()) {
                println("===hasChildNodes for node " + nodeName + "===");
                if (node.getChildNodes().getLength() == 1
                        && node.getChildNodes().item(0).getAttributes() == null) {
                    xmlMap.put(nodeValue == null ? textContent : nodeValue, nodeName);
                } else {
                    StorageComponent<String, String> storageComponent = xmlMap
                            .getStorageComponent(nodeName);
                    parse(storageComponent.getStorageComponent(String.valueOf(Math.max(
                            xmlMap.componentSize() - 1, 0))), node.getChildNodes(), nodeName);
                }
            }
            println("===parse node " + nodeName + " end ===");
        }
    }

    private void parse(final StorageComponent<String, String> xmlMap,
            final NamedNodeMap namedNodeMap) {

        println("=====parse namedNodeMap=====");
        int attributesLength = namedNodeMap.getLength();
        for (int index = 0; index < attributesLength; index++) {
            Node node = namedNodeMap.item(index);
            String nodeName = node.getNodeName();
            println(String.format("%s:%s", "getNodeName", nodeName));
            println("=parse node " + nodeName + "=");
            String textContent = node.getTextContent();
            println(String.format("%s:%s", "getTextContent", textContent));
            String nodeValue = node.getNodeValue();
            println(String.format("%s:%s", "getNodeValue", nodeValue));
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
