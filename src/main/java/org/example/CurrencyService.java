package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrencyService {
    private final String TAGNAME = "pozycja";
    private Currency currency;
    public Currency getCurrency(String code){
        try {
            URL url = new URL("https://www.nbp.pl/kursy/xml/lasta.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(String.valueOf(url));
            NodeList nodeList = document.getElementsByTagName(TAGNAME);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    NodeList nodeList1 = node.getChildNodes();
                    for (int j = 0; j < nodeList1.getLength(); j++) {
                        if (nodeList1.item(j).getNodeType() == Node.ELEMENT_NODE && nodeList1.item(j).getTextContent().equals(code)) {
                            currency = new Currency();
                            currency.setName(nodeList1.item(j - 4).getTextContent());
                            currency.setConverter(nodeList1.item(j - 2).getTextContent());
                            currency.setCode(nodeList1.item(j ).getTextContent());
                            currency.setExchangeRate(nodeList1.item(j + 2).getTextContent());
                            return currency;
                        }
                    }
                }
            }

        } catch (ParserConfigurationException | MalformedURLException| SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public double currencyExchange(Currency firstCurrency, Currency secondCurrency, double amount){
        double result = firstCurrency.getExchangeRate()/secondCurrency.getExchangeRate()/firstCurrency.getConverter()*secondCurrency.getConverter()*amount;
        return (double)Math.round(result*100)/100.0;
    }

}
