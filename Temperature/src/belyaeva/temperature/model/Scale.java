package belyaeva.temperature.model;

public interface Scale {
    double convertFromCelsius(double inTemperature);

    double convertToCelsius(double inTemperature);
}
