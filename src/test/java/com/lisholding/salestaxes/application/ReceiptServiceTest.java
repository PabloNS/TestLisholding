package com.lisholding.salestaxes.application;

import com.lisholding.salestaxes.domain.Item;
import com.lisholding.salestaxes.domain.ItemType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ReceiptServiceTest {

    private ReceiptService receiptService = new ReceiptService();

    @Test
    public void printReceipt1(){
        Item book = Item.builder().name("book").itemType(ItemType.BOOK).price(12.49).build();
        Item musicCD = Item.builder().name("musicCD").itemType(ItemType.MUSIC).price(14.99).build();
        Item chocolateBar = Item.builder().name("chocolate bar").itemType(ItemType.FOOD).price(0.85).build();
        List<Item> items = Arrays.asList(book, musicCD, chocolateBar);
        receiptService.printReceipt(items);
    }

    @Test
    public void printReceipt2(){
        Item importedBoxOfChocolates = Item.builder().imported(true).name("box of chocolates").itemType(ItemType.FOOD)
                .price(10.00).build();
        Item importedBottleOfPerfume = Item.builder().imported(true).name("bottle of perfume").itemType(ItemType.BEAUTY)
                .price(47.50).build();
        List<Item> items = Arrays.asList(importedBoxOfChocolates, importedBottleOfPerfume);
        receiptService.printReceipt(items);
    }

    @Test
    public void printReceipt3(){
        Item importedBottleOfPerfume = Item.builder().imported(true).name("bottle of perfume")
                .itemType(ItemType.BEAUTY).price(27.99).build();
        Item bottleOfPerfume = Item.builder().name("bottle of perfume").itemType(ItemType.BEAUTY)
                .price(18.99).build();
        Item packetOfHeadachePill = Item.builder().name("packet of headache pills").itemType(ItemType.MEDICAL)
                .price(9.75).build();
        Item importedBoxOfChocolates = Item.builder().imported(true).name("box of chocolates")
                .itemType(ItemType.MEDICAL).price(11.25).build();
        List<Item> items = Arrays.asList(importedBottleOfPerfume, bottleOfPerfume, packetOfHeadachePill, importedBoxOfChocolates);
        receiptService.printReceipt(items);
    }
}