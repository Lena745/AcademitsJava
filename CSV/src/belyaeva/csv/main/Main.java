package belyaeva.csv.main;

import belyaeva.csv.task.CSV;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("It must be 2 arguments");
        } else {
            CSV csv = new CSV();
            String fileToRead = args[0];
            String fileToWrite = args[1];
            csv.convertCSVtoHTML(fileToRead, fileToWrite);
        }
    }
}
