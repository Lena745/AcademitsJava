package belyaeva.temperature.controller;

import belyaeva.temperature.model.ConverterModel;

public class ConverterController {
    private ConverterModel model = new ConverterModel();

    public double getResult(String inScale, String outScale, double inTemperature) {
        if (outScale.equals("Цельсий")) {
            return model.getCelsiusResult(inScale, inTemperature);
        }

        if (outScale.equals("Кельвин")) {
            return model.getKelvinResult(inScale, inTemperature);
        }

        return model.getFahrenheitResult(inScale, inTemperature);
    }
}



