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
            writer.print("&lt;");
        } else if (symbol == '>') {
            writer.print("&gt;");
        } else if (symbol == '&') {
            writer.print("&amp;");
        } else {
            writer.print(symbol);
        }
    }

    public void writeCSV(String readPath, String writePath) {
        try (PrintWriter writer = new PrintWriter(writePath)) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>CSV</title>");
            writer.println("<meta charset=\"utf-8\">");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table border=\"1\">");

            boolean isInQuotes = false;
            boolean isDoubleQuotes = false;
            boolean isNewLine = true;
            int doubleQuotesCount = 0;
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
                        if (symbol == quotes) {
                            if (isDoubleQuotes) {
                                isDoubleQuotes = false;
                                doubleQuotesCount = 0;
                            } else if (isInQuotes) {
                                isInQuotes = false;
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
                                doubleQuotesCount++;
                                isDoubleQuotes = true;
                                if (doubleQuotesCount % 2 != 0) {
                                    writer.print(symbol);
                                }
                            } else {
                                if (!isDoubleQuotes) {
                                    isInQuotes = !isInQuotes;
                                } else {
                                    isDoubleQuotes = false;
                                    doubleQuotesCount = 0;
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
            writer.println("</body>");
            writer.println("</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Can't write file");
        }
    }
}