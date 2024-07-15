import java.util.Map;

    abstract class Moeda {
    protected double valor;
    protected Map<String, Double> taxasCambio;

    public Moeda(double valor, Map<String, Double> taxasCambio) {
        this.valor = valor;
        this.taxasCambio = taxasCambio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public abstract double paraReal();
    public abstract double paraDolar();
    public abstract double paraEuro();
}

