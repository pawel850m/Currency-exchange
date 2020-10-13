package org.example;

import org.w3c.dom.Node;

public class Currency {

    private String name;
    private Integer converter;
    private String code;
    private Double exchangeRate;

    public String getName() {
        return name;
    }

    public Integer getConverter() {
        return converter;
    }

    public String getCode() {
        return code;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConverter(String converter) {
        this.converter = Integer.parseInt(converter);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setExchangeRate(String exchangeRate) {
        exchangeRate = exchangeRate.replace(",",".");
        this.exchangeRate = Double.parseDouble(exchangeRate);
    }

    public Currency(String name, Integer converter, String code, Double exchangeRate) {
        this.name = name;
        this.converter = converter;
        this.code = code;
        this.exchangeRate = exchangeRate;
    }

    public Currency(){}

}
