package belyaeva.temperature.model;

public class Kelvin implements Scale {
    @Override
    public String toString() {
        return "Кельвин";
    }

    @Override
    public double convertFromCelsius(double inTemperature) {
        return inTemperature + 273.15;
    }

    @Override
    public double convertToCelsius(double inTemperature) {
        return inTemperature - 273.15;
    }
}
