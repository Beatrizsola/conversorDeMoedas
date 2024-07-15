import java.util.Map;

public class Euro extends Moeda {

    public Euro(double valor, Map<String, Double> taxasCambio) {
        super(valor, taxasCambio);
    }

    @Override
    public double paraReal() {
        return valor * (1 / taxasCambio.get("EUR"));
    }

    @Override
    public double paraDolar() {
        return valor * (taxasCambio.get("USD") / taxasCambio.get("EUR"));
    }

    @Override
    public double paraEuro() {
        return valor;
    }
}
