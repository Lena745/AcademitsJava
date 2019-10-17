package belyaeva.temperature.model;

public class ConverterModel {
    public double getFahrenheitResult(String scale, double inTemperature) {
        if (scale.equals("Цельсий")) {
            return inTemperature * 1.8 + 32;
        }

        if (scale.equals("Кельвин")) {
            return inTemperature * 1.8 - 459.67;
        }

        return inTemperature;
    }

    public double getKelvinResult(String scale, double inTemperature) {
        if (scale.equals("Цельсий")) {
            return inTemperature + 273.15;
        }

        if (scale.equals("Фаренгейт")) {
            return (inTemperature + 459.67) / 1.8;
        }

        return inTemperature;
    }

    public double getCelsiusResult(String scale, double inTemperature) {
        if (scale.equals("Фаренгейт")) {
            return (inTemperature - 32) / 1.8;
        }

        if (scale.equals("Кельвин")) {
            return inTemperature - 273.15;
        }

        return inTemperature;
    }
}
