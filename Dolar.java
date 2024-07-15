import java.util.Map;

public class Dolar extends Moeda {

    public Dolar(double valor, Map<String, Double> taxasCambio) {
        super(valor, taxasCambio);
    }

    @Override
    public double paraReal() {
        return valor * (1 / taxasCambio.get("USD"));
    }

    @Override
    public double paraDolar() {
        return valor;
    }

    @Override
    public double paraEuro() {
        return valor * (taxasCambio.get("EUR") / taxasCambio.get("USD"));
    }
}
