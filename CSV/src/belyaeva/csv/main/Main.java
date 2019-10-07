package belyaeva.csv.main;

import belyaeva.csv.task.CSV;

public class Main {
    public static void main(String[] args) {
        CSV csv = new CSV();
        String readFile = "in.csv";
        String writeFile = "out.html";
        csv.writeCSV(readFile, writeFile);
    }
}
