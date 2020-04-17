package eplrankingsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author Sichen & Xianling
 * This ReadCsvFile aims to read raw .CSV file and store into Arraylist in order to analyse
 */

public class ReadCsvFile {
    private BufferedReader bfReader;
    private String[] csvHeader;

    public ReadCsvFile(String filePath) throws Exception {
        if (isCsv(filePath)) {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("Not found the file by" + filePath + "you provided");
            }
            bfReader = new BufferedReader(new FileReader(file));
        } else {
            System.out.println("Please provide right format .csv file!");
        }
    }

    public String[] getCsvHeader() throws Exception {
        if (csvHeader == null) {
            String line = bfReader.readLine();
            if (line != null) {
                String[] temp = line.split(",");
                csvHeader = temp;
            }
        }
        return csvHeader;
    }

    public String[] getNextRow() throws Exception {
        if (csvHeader == null) {
            csvHeader = getCsvHeader();
        }
        String line = bfReader.readLine();
        if (line != null) {
            String[] temp = line.split(",");
            return temp;
        }
        return null;
    }

    // Determine whether it is a csv file
    private boolean isCsv(String fileName) {
        return fileName.matches("^.+\\.(?i)(csv)$");
    }
}
