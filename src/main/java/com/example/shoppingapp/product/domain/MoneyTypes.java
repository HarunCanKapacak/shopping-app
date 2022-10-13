package com.example.shoppingapp.product.domain;

public enum MoneyTypes {
    USD("Dolar","$"),
    EUR("Euro","E"),
    TL("Turk Lirası","T");

    private String label;
    private String symbol;

    MoneyTypes(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
