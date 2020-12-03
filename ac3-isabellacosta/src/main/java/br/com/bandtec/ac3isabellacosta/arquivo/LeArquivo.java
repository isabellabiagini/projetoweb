package br.com.bandtec.ac3isabellacosta.arquivo;

import br.com.bandtec.ac3isabellacosta.classes.Doce;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeArquivo {
    public static List<Doce> leArquivo(String nomeArq) {
        BufferedReader entrada = null;
        String registro;
        String tipoRegistro;
        String nome, tipo,id;
        double valor;
        int contRegistro=0;

        List<Doce> listaDoce = new ArrayList<Doce>();


        // Abre o arquivo
        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        // Lê os registros do arquivo
        try {
            // Lê um registro
            registro = entrada.readLine();

            while (registro != null) {
                // Obtém o tipo do registro
                tipoRegistro = registro.substring(0, 2); // obtém os 2 primeiros caracteres do registro

                if (tipoRegistro.equals("00")) {
                    System.out.println( registro.substring(3, 28));
                }
                else if (tipoRegistro.equals("01")) {
                    System.out.println(registro.substring(3,47));
                }
                else if (tipoRegistro.equals("02")) {
                    Doce doce = new Doce();
                    id = registro.substring(3,5);
                    nome = registro.substring(6,16);
                    tipo = registro.substring(17,21);
                    valor = Double.parseDouble(registro.substring(22,27).replace(',','.'));

                    doce.setId(id);
                    doce.setNome(nome);
                    doce.setTipo(tipo);
                    doce.setValor(valor);

                    listaDoce.add(doce);

                    System.out.printf("%-5s %-8s %-10s %5.2f \n", id,nome,tipo,valor);
                    contRegistro++;
                }
                else {
                    System.out.println("Tipo de registro inválido");
                }

                // lê o próximo registro
                registro = entrada.readLine();
            }

            // Fecha o arquivo
            entrada.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }
        return listaDoce;

    }

    public static void main(String[] args) {
        String nomeArq = "doce.txt";
        leArquivo(nomeArq);


    }
}
