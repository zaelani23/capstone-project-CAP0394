package com.cap0394.sahabattani;

import java.util.ArrayList;

public class TengkulakData {

    private static String[] tengkulakName = {
            "Drajat Santoso",
            "Damu Halim",
            "Capa Januar",
            "Dacin Januar",
            "Dimas Prabowo"
    };

    public static ArrayList<Tengkulak> getListData() {
        ArrayList<Tengkulak> list = new ArrayList<>();
        for (int position = 0; position < tengkulakName.length; position++) {
            Tengkulak tengkulak = new Tengkulak();
            tengkulak.setName(tengkulakName[position]);
            list.add(tengkulak);
        }
        return list;
    }
}
