import java.util.Map;

public class Real extends Moeda {

    public Real(double valor, Map<String, Double> taxasCambio) {
        super(valor, taxasCambio);
    }

    @Override
    public double paraReal() {
        return valor;
    }

    @Override
    public double paraDolar() {
        return valor * taxasCambio.get("USD");
    }

    @Override
    public double paraEuro() {
        return valor * taxasCambio.get("EUR");
    }
}
    
