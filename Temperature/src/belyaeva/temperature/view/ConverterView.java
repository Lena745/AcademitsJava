package belyaeva.temperature.view;

import belyaeva.temperature.controller.ConverterController;

import javax.swing.*;
import java.awt.*;

public class ConverterView {
    private final JFrame frame = new JFrame("Перевод температур");
    private final JPanel panel = new JPanel(new GridBagLayout());
    private final String[] scalesNames = new String[]{"Цельсий", "Кельвин", "Фаренгейт"};
    private final JComboBox<String> inScales = new JComboBox<>(scalesNames);
    private final JComboBox<String> outScales = new JComboBox<>(scalesNames);
    private final JTextField entryTemperature = new JTextField("", 10);
    private final JButton calculateButton = new JButton("Рассчитать");
    private final JTextField resultTemperature = new JTextField("", 10);
    private final JButton clearButton = new JButton("Очистить");

    private void initFrame() {
        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(320, 270);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);

            frame.add(panel);

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.BOTH;
            c.gridx = 0;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 1;
            c.gridheight = 1;
            c.insets = new Insets(7, 5, 8, 5);
            panel.add(new JLabel("Выберете шкалу для ввода:"), c);

            c.gridx = 3;
            c.gridy = 1;
            panel.add(inScales, c);

            c.gridx = 0;
            c.gridy = 2;
            panel.add(new JLabel("Выберете шкалу для вывода:"), c);

            c.gridx = 3;
            c.gridy = 2;
            panel.add(outScales, c);

            c.gridx = 0;
            c.gridy = 3;
            panel.add(new JLabel("Введите значение температуры:"), c);

            c.gridx = 3;
            c.gridy = 3;
            panel.add(entryTemperature, c);

            c.gridx = 0;
            c.gridy = 4;
            c.insets = new Insets(7, 105, 8, 0);
            panel.add(calculateButton, c);

            c.gridx = 0;
            c.gridy = 5;
            c.insets = new Insets(7, 5, 8, 5);
            panel.add(new JLabel("Результат:"), c);

            c.gridx = 3;
            c.gridy = 5;
            resultTemperature.setEditable(false);
            panel.add(resultTemperature, c);

            c.gridx = 0;
            c.gridy = 6;
            c.insets = new Insets(7, 107, 8, 0);
            panel.add(clearButton, c);
        });
    }

    private String getInScale() {
        return (String) inScales.getSelectedItem();
    }

    private String getOutScale() {
        return (String) outScales.getSelectedItem();
    }

    private String getEntryTemperature() {
        return entryTemperature.getText();
    }

    private void setResultTemperature(double result) {
        resultTemperature.setText(String.format("%.2f", result));
    }

    private void getErrorMessage() {
        JOptionPane.showMessageDialog(frame, "Введено не число", "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
    }

    private void initEvents() {
        calculateButton.addActionListener(event -> {
            try {
                String inScale = getInScale();
                String outScale = getOutScale();
                double inTemperature = Double.parseDouble(getEntryTemperature());
                double outTemperature = new ConverterController().getResult(inScale, outScale, inTemperature);
                setResultTemperature(outTemperature);
                inScales.setEnabled(false);
                outScales.setEnabled(false);
                entryTemperature.setEditable(false);
            } catch (NumberFormatException e) {
                getErrorMessage();
            }
        });

        clearButton.addActionListener(event -> {
            inScales.setEnabled(true);
            inScales.setSelectedIndex(0);
            outScales.setEnabled(true);
            outScales.setSelectedIndex(0);
            entryTemperature.setEditable(true);
            entryTemperature.setText("");
            resultTemperature.setText("");
        });
    }

    public void run() {
        initFrame();
        initEvents();
    }
}


