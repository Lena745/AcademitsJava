package belyaeva.temperature.main;

import belyaeva.temperature.model.CelsiusScale;
import belyaeva.temperature.model.FahrenheitScale;
import belyaeva.temperature.model.KelvinScale;
import belyaeva.temperature.model.Scale;
import belyaeva.temperature.view.ConverterView;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = new Scale[]{new CelsiusScale(), new KelvinScale(), new FahrenheitScale()};
        ConverterView view = new ConverterView(scales);
        view.run();
    }
}
