package com.cap0394.sahabattani;

import java.util.ArrayList;

public class MarketplaceData {
    private static String[] productTitle = {
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit",
            "Lorem ipsum dolor sit amet, consectetur adipisicing elit"
    };

    public static ArrayList<Marketplace> getListData() {
        ArrayList<Marketplace> list = new ArrayList<>();
        for (int position = 0; position < productTitle.length; position++) {
            Marketplace product = new Marketplace();
            product.setProductTitle(productTitle[position]);
            list.add(product);
        }
        return list;
    }
}
