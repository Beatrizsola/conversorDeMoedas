import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConversorDeMoedas {
    private static final String API_KEY = "7ff66bef4674e6c2ad98dc40";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/BRL";

    public static void main(String[] args) {
        Map<String, Double> taxasCambio = obterTaxasCambio();
        if (taxasCambio == null) {
            System.out.println("Erro ao obter as taxas de câmbio.");
            return;
        }

        Scanner leitura = new Scanner(System.in);

        System.out.println("Conversor de Moedas");
        System.out.println("1: Real (BRL)");
        System.out.println("2: Dólar (USD)");
        System.out.println("3: Euro (EUR)");
        System.out.print("Escolha a moeda de origem (1-3): ");
        int origem = leitura.nextInt();

        System.out.print("Escolha a moeda de destino (1-3): ");
        int destino = leitura.nextInt();

        System.out.print("Digite o valor a ser convertido: ");
        double valor = leitura.nextDouble();

        Moeda moedaOrigem;

        switch (origem) {
            case 1:
                moedaOrigem = new Real(valor, taxasCambio);
                break;
            case 2:
                moedaOrigem = new Dolar(valor, taxasCambio);
                break;
            case 3:
                moedaOrigem = new Euro(valor, taxasCambio);
                break;
            default:
                System.out.println("Moeda de origem inválida.");
                return;
        }

        double valorConvertido = 0;

        switch (destino) {
            case 1:
                valorConvertido = moedaOrigem.paraReal();
                break;
            case 2:
                valorConvertido = moedaOrigem.paraDolar();
                break;
            case 3:
                valorConvertido = moedaOrigem.paraEuro();
                break;
            default:
                System.out.println("Moeda de destino inválida.");
                return;
        }

        System.out.printf("Valor convertido: %.2f%n", valorConvertido);
    }

    private static Map<String, Double> obterTaxasCambio() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            Map<String, Double> taxasCambio = new HashMap<>();
            taxasCambio.put("BRL", 1.0);
            taxasCambio.put("USD", rates.get("USD").getAsDouble());
            taxasCambio.put("EUR", rates.get("EUR").getAsDouble());

            return taxasCambio;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}