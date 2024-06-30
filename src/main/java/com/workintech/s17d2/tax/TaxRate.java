package com.workintech.s17d2.tax;

public enum TaxRate {
    SIMPLE_TAX_RATE(15.00),
    MIDDLE_TAX_RATE(25.00),
    UPPER_TAX_RATE(35.00);

    private final double rate;


    TaxRate(double rate) {
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }
}
