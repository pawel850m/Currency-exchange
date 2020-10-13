package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        CurrencyService currencyService = new CurrencyService();

        Currency sourceCurrency = currencyService.getCurrency("HUF");
        Currency targetCurrency = currencyService.getCurrency("USD");
        System.out.println(currencyService.currencyExchange(sourceCurrency, targetCurrency,500));
    }
}