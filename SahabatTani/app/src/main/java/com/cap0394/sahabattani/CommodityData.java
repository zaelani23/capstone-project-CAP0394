package com.cap0394.sahabattani;

import java.util.ArrayList;

public class CommodityData {
    private static String[] commodityNames = {
            "Gabah",
            "Beras",
            "Jagung",
            "Cabai Rawit",
            "Bawang Merah"
    };

    private static int[] commodityImages = {
            R.drawable.ic_wheat_sack,
            R.drawable.ic_rice,
            R.drawable.ic_corn,
            R.drawable.ic_chili,
            R.drawable.ic_shallot
    };

    public static ArrayList<Commodity> getListData() {
        ArrayList<Commodity> list = new ArrayList<>();
        for (int position = 0; position < commodityNames.length; position++) {
            Commodity commodity = new Commodity();
            commodity.setName(commodityNames[position]);
            commodity.setImage(commodityImages[position]);
            list.add(commodity);
        }
        return list;
    }
}
