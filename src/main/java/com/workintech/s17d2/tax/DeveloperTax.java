package com.workintech.s17d2.tax;


import org.springframework.stereotype.Component;

@Component
public class DeveloperTax implements Taxable{

    @Override
    public double getSimpleTaxRate() {
        return TaxRate.SIMPLE_TAX_RATE.getRate();
    }

    @Override
    public double getMiddleTaxRate() {
       return TaxRate.MIDDLE_TAX_RATE.getRate();
    }

    @Override
    public double getUpperTaxRate() {
        return TaxRate.UPPER_TAX_RATE.getRate();
    }
}
