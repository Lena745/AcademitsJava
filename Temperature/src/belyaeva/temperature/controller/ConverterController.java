package belyaeva.temperature.controller;

import belyaeva.temperature.model.*;

public class ConverterController {
    public double getResult(Scale inScale, Scale outScale, double inTemperature) {
        double celsiusTemperature = inScale.convertToCelsius(inTemperature);
        return outScale.convertFromCelsius(celsiusTemperature);
    }
}




