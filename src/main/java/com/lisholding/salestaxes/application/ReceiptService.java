package com.lisholding.salestaxes.application;

import com.lisholding.salestaxes.domain.Item;
import com.lisholding.salestaxes.domain.CalculatedItemPrice;
import com.lisholding.salestaxes.domain.ItemType;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class ReceiptService {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final DecimalFormat df1 = new DecimalFormat("0.0");

    private static final List<ItemType> TAX_EXEMPTED_ITEM_TYPES =
            Arrays.asList(ItemType.BOOK, ItemType.FOOD, ItemType.MEDICAL);

    public void printReceipt(List<Item> items){
        double totalPrice = 0d;
        double salesTaxes = 0d;
        for(Item item : items){
            CalculatedItemPrice calculatedItemPrice = calculateItemPrice(item);
            StringBuilder receiptLine = new StringBuilder("1 ");
            if(item.isImported()){
                receiptLine.append("imported ");
            }
            receiptLine.append(item.getName()).append(" at ").append(df.format(calculatedItemPrice.getFinalPrice()));
            System.out.println(receiptLine);
            totalPrice += calculatedItemPrice.getFinalPrice();
            salesTaxes += calculatedItemPrice.getTotalTaxes();
        }
        StringBuilder salesTaxesLine = new StringBuilder("Sales Taxes: ").append(df.format(salesTaxes));
        System.out.println(salesTaxesLine);
        StringBuilder totalPriceLine = new StringBuilder("Total: ").append(df.format(totalPrice));
        System.out.println(totalPriceLine);
    }

    private CalculatedItemPrice calculateItemPrice(Item item){
        double finalPrice = item.getPrice();
        double totalTaxes = 0d;
        if(item.isImported()){
            Double tax = item.getPrice()*0.05;
            totalTaxes += tax;
        }
        if(!isTaxExemptedItem(item)){
            double tax = item.getPrice()*0.1;
            totalTaxes += tax;
        }


        double beforeFormat = totalTaxes;

        totalTaxes = Double.valueOf(df1.format(totalTaxes));

        if(beforeFormat>totalTaxes){
            totalTaxes += 0.05;
        }

        finalPrice += totalTaxes;

        return CalculatedItemPrice.builder().finalPrice(finalPrice).totalTaxes(totalTaxes).build();
    }

    private boolean isTaxExemptedItem(Item item){
        return TAX_EXEMPTED_ITEM_TYPES.contains(item.getItemType());
    }
}