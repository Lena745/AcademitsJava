package belyaeva.csv.task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CSV {
    private ArrayList<String> readCSV(String filepath) {
        ArrayList<String> stringList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(filepath), "windows-1251")) {
            while (scanner.hasNextLine()) {
                stringList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return stringList;
    }

    private void print(PrintWriter writer, char symbol) {
        if (symbol == '<') {
            writer.print("&lt");
        } else if (symbol == '>') {
            writer.print("&gt");
        } else if (symbol == '&') {
            writer.print("&amp");
        } else {
            writer.print(symbol);
        }
    }

    public void writeCSV(String readPath, String writePath) {
        try (PrintWriter writer = new PrintWriter(writePath)) {
            writer.println("<table border = 1>");

            boolean isInQuotes = false;
            boolean isDoubleQuotes = false;
            boolean isNewLine = true;
            char comma = ',';
            char quotes = '"';

            ArrayList<String> lines = readCSV(readPath);

            for (String line : lines) {
                if (isNewLine) {
                    writer.println("<tr>");
                    writer.println("<td>");
                }
                for (int j = 0; j < line.length(); j++) {
                    char symbol = line.charAt(j);

                    if (j == line.length() - 1) {
                        if (!isDoubleQuotes && symbol != comma) {
                            print(writer, symbol);
                        }

                        if (isInQuotes) {
                            writer.println("<br/>");
                            isNewLine = false;
                        } else {
                            writer.println("</td>");
                            writer.println("</tr>");
                            isNewLine = true;
                        }
                    } else {
                        if (symbol == quotes) {
                            if (line.charAt(j + 1) == quotes) {
                                isDoubleQuotes = true;
                                writer.print(symbol);
                            } else {
                                if (!isDoubleQuotes) {
                                    isInQuotes = !isInQuotes;
                                } else {
                                    isDoubleQuotes = false;
                                }
                            }
                        } else if (symbol == comma) {
                            if (!isInQuotes) {
                                writer.println("</td>");
                                writer.println("<td>");
                            } else {
                                writer.print(symbol);
                            }
                        } else {
                            print(writer, symbol);
                        }
                    }
                }
            }

            writer.println("</table>");
        } catch (FileNotFoundException e) {
            System.out.println("Can't write file");
        }
    }
}