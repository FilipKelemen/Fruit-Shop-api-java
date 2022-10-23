package com.FruitShopbackend.FruitShopapi.utility;

public class PriceUtility {

    private PriceUtility(){}

    public static String getFormattedPrice(final long priceValue,final String currency) {
        final int NUMBER_OF_DECIMALS = 2;
        final String stringifiedPrice = Long.toString(priceValue);
        String numberOfCurrencyUnits = "0";
        String decimalPart = "0";
        //this handles 0.123...;0.0000...123
        if(stringifiedPrice.length() <= NUMBER_OF_DECIMALS) {
            decimalPart = "0".repeat(NUMBER_OF_DECIMALS - stringifiedPrice.length()) + stringifiedPrice;
        }
        //this handles at least one currency unit
        if(stringifiedPrice.length() > NUMBER_OF_DECIMALS){
            numberOfCurrencyUnits = stringifiedPrice.substring(0,stringifiedPrice.length() - NUMBER_OF_DECIMALS);
            decimalPart = stringifiedPrice.substring(stringifiedPrice.length() - NUMBER_OF_DECIMALS);
        }
        return currency + numberOfCurrencyUnits + ',' + decimalPart;
    }
}