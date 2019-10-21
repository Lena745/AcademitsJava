package belyaeva.csv.task;

import java.io.*;
import java.util.Scanner;

public class CSV {
    public void convertCSVtoHTML(String readPath, String writePath) {
        try (Scanner scanner = new Scanner(new FileInputStream(readPath), "windows-1251");
             PrintWriter writer = new PrintWriter(writePath)) {
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
            char comma = ',';
            char quotes = '"';

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (isNewLine) {
                    writer.println("<tr>");
                    writer.println("<td>");
                }
                for (int j = 0; j < line.length(); j++) {
                    char symbol = line.charAt(j);

                    if (symbol == quotes) {
                        if (j != line.length() - 1) {
                            if (line.charAt(j + 1) == quotes) {
                                if (isInQuotes) {
                                    if (!isDoubleQuotes) {
                                        writer.print(symbol);
                                        isDoubleQuotes = true;
                                    } else {
                                        isDoubleQuotes = false;
                                    }
                                } else {
                                    isInQuotes = true;
                                }
                            } else {
                                if (!isDoubleQuotes) {
                                    isInQuotes = !isInQuotes;
                                } else {
                                    isDoubleQuotes = false;
                                }
                            }
                        } else {
                            isInQuotes = !isInQuotes;
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

                    if (j == line.length() - 1) {
                        if (isInQuotes) {
                            writer.println("<br/>");
                            isNewLine = false;
                        } else {
                            writer.println("</td>");
                            writer.println("</tr>");
                            isNewLine = true;
                        }
                    }
                }
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Can't read/write file");
        }
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
}