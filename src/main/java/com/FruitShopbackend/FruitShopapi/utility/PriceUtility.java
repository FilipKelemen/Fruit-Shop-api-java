package com.FruitShopbackend.FruitShopapi.utility;

public class PriceUtility {

    private PriceUtility(){}

    public static String getFormattedPrice(final long priceValue,final String currency) throws Exception {
        final int NUMBER_OF_DECIMALS = 2;
        final String stringifiedPrice = Long.toString(priceValue);
        if(stringifiedPrice.length() < NUMBER_OF_DECIMALS) {
            throw new Exception("Value not big enough to handle " + NUMBER_OF_DECIMALS + " decimals\n");
        }
        String numberOfCurrencyUnits = stringifiedPrice.substring(0,stringifiedPrice.length() - NUMBER_OF_DECIMALS);
        //handling case where price should be 0.123...
        if(numberOfCurrencyUnits.length() == 0) {
            numberOfCurrencyUnits = "0";
        }
        final String decimalPart = stringifiedPrice.substring(stringifiedPrice.length() - NUMBER_OF_DECIMALS);
        return currency + numberOfCurrencyUnits + ',' + decimalPart;
    }
}
