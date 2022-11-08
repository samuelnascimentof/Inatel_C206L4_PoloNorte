package br.intatel.C206L4;

import br.intatel.C206L4.Exception.CriancaNaoEncontradaException;
import br.intatel.C206L4.controller.FileManager;
import br.intatel.C206L4.helper.DataPopulator;
import br.intatel.C206L4.model.Fabrica;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        System.out.println("Iniciando aplicação Polo Norte");

        Fabrica fabrica = DataPopulator.populate();

        try {
            System.out.println("Lendo arquivo de crianças boazinhas");
            List<String[]> criancasBoazinhas = FileManager.readTxt("adicionar-crianca-boazinha.txt");
            System.out.println("Adicionando crianças boazinhas à fábrica");
            criancasBoazinhas.forEach(criancaBoazinha -> {
                try {
                    fabrica.addBonzinho(criancaBoazinha[0]);
                } catch (CriancaNaoEncontradaException | IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (NoSuchFileException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(FileManager.lineSeparator);
        fabrica.listarBonzinhos();
        System.out.println(FileManager.lineSeparator);
        System.out.println("Fim da aplicação Polo Norte. Feliz natal!");
    }
}
