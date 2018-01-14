package service;


import starter.dto.CsvValidData;

import java.util.ArrayList;
import java.util.List;

public  class MockData {
    public static  List<CsvValidData> getExistingDataMock() {
        List<CsvValidData> list = new ArrayList<>();
        CsvValidData csvValidData = new CsvValidData();
        csvValidData.setCsvOrderAmount("1000");
        csvValidData.setCsvOrderCurrency("USD");
        csvValidData.setCsvOrderFilename("SampCSVFile_.csv");
        csvValidData.setCsvOrderId("1");
        csvValidData.setCsvOrderingCurrency("INR");
        list.add(csvValidData);
        return list;
    }

    public static  List<CsvValidData> getNewDataMock() {
        List<CsvValidData> list = new ArrayList<>();
        return list;
    }

    public static  List<String> getfileNames() {
        List<String> list = new ArrayList<>();
        list.add("SampCSVFile1_.csv");
        list.add("SampCSVFile2_.csv");
        list.add("SampCSVFile3_.csv");
        return list;
    }

    public static  List<String> getEmptyfileNames() {
        List<String> list = new ArrayList<>();
        return list;
    }
}
