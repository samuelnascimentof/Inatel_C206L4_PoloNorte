package br.intatel.C206L4.helper;

import br.intatel.C206L4.controller.FileManager;
import br.intatel.C206L4.model.Crianca;
import br.intatel.C206L4.model.Elfo;
import br.intatel.C206L4.model.Fabrica;

public abstract class DataPopulator {
    public static Fabrica populate() {
        Fabrica fabrica = new Fabrica();
        System.out.println(FileManager.lineSeparator);
        fabrica.novoElfo(new Elfo("Cressidus Duskram", "Embrulhador de presentes"));
        fabrica.novoElfo(new Elfo("Niqol Nightwalker", "Funileiro"));
        fabrica.novoElfo(new Elfo("Teucrium Plumbite", "Ajudante do papai noel"));
        fabrica.novoElfo(new Elfo("Hanson Hillsong", "Modelador de carrinhos"));
        fabrica.novoElfo(new Elfo("Konell Longstycke", "Gerente geral"));

        fabrica.novaCrianca(new Crianca("João Gabriel Pereira", 12, "Paraguaio", "Bicicleta"));
        fabrica.novaCrianca(new Crianca("Robersvaldo Valério de Castro", 8, "Venezuelano", "Pião"));
        fabrica.novaCrianca(new Crianca("Junelson Lagoa Parda", 10, "Argentino", "Bambolê"));
        fabrica.novaCrianca(new Crianca("Euclides Rosendo", 5, "Chileno", "Pipa"));
        fabrica.novaCrianca(new Crianca("Eduardo Juclécio Santos", 9, "Brasileiro", "Preistêixo"));

        System.out.println("Elfos cadastrados: \n");
        fabrica.getElfos().forEach(elfo -> {
            System.out.println("Nome: ".concat(elfo.getNome()));
            System.out.println("Função: ".concat(elfo.getFuncao()).concat("\n"));
        });
        System.out.println(FileManager.lineSeparator);
        System.out.println("Crianças cadastradas: \n");
        fabrica.getCriancas().forEach(crianca -> {
            System.out.println("Nome: ".concat(crianca.getNome()));
            System.out.println("Idade: " + crianca.getIdade());
            System.out.println("Nacionalidade: ".concat(crianca.getNacionalidade()));
            System.out.println("Presente desejado: ".concat(crianca.getPresenteDesejado()).concat("\n"));
        });
        System.out.println(FileManager.lineSeparator);
        return fabrica;
    }
}
