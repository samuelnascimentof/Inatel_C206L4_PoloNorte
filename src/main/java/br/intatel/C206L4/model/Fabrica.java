package br.intatel.C206L4.model;

import br.intatel.C206L4.Exception.CriancaNaoEncontradaException;
import br.intatel.C206L4.controller.FileManager;

import java.util.ArrayList;
import java.util.List;

public class Fabrica {

    private List<Crianca> criancas = new ArrayList<>();

    private List<Elfo> elfos = new ArrayList<>();
    private List<Crianca> listaBonzinhos = new ArrayList<>();

    public void novoElfo(Elfo elfo) {
        this.elfos.add(elfo);
    }

    public void novaCrianca(Crianca crianca) {
        this.criancas.add(crianca);
    }

    public void addBonzinho(String nomeCrianca) throws CriancaNaoEncontradaException, IllegalArgumentException {
        // Busca pela criança e pelo índice correspondente à criança em questão
        int counter = 0;
        int index = -1;
        for (Crianca crianca : criancas) {
            if (crianca.getNome().equals(nomeCrianca)) {
                index = counter;
                break;
            }
            counter++;
        }
        if(index == -1){
            throw new CriancaNaoEncontradaException("Criança não encontrada.");
        } else {
            // Verifica se a crianca já foi adicionada na lista de bonzinhos
            listaBonzinhos.forEach(crianca -> {
                if (crianca.getNome().equals(nomeCrianca)) {
                    throw new IllegalArgumentException("Criança já adicionada na lista de bonzinhos.");
                }
            });
            // Adiciona a criança na lista de bonzinhos
            listaBonzinhos.add(criancas.get(index));
            System.out.println(criancas.get(index).getNome().concat(" adicionado à lista de crianças boazinhas."));
        }
    }

    public void listarBonzinhos() {
        System.out.println("Escrevendo arquivo de crianças boazinhas");
        String OUTPUT_FILE = "criancas-boazinhas.txt";
        if(!listaBonzinhos.isEmpty()){
            FileManager.clearOutputFile(OUTPUT_FILE);
            FileManager.writeTxt(OUTPUT_FILE, "Crianças boazinhas:");
            int index = 0;
            for (Crianca crianca : listaBonzinhos) {
                FileManager.writeTxt(OUTPUT_FILE, "Nome: ".concat(listaBonzinhos.get(index).getNome()));
                FileManager.writeTxt(OUTPUT_FILE, "Idade: " + listaBonzinhos.get(index).getIdade());
                FileManager.writeTxt(OUTPUT_FILE, "Nacionalidade: ".concat(listaBonzinhos.get(index).getNacionalidade()));
                FileManager.writeTxt(OUTPUT_FILE, "Presente desejado: ".concat(listaBonzinhos.get(index).getPresenteDesejado()));
                index++;
            }
            System.out.println("Arquivo escrito com sucesso!");
        } else {
            FileManager.clearOutputFile(OUTPUT_FILE);
            FileManager.writeTxt(OUTPUT_FILE, "Não há crianças boazinhas!");
            System.out.println("Não há crianças boazinhas para serem adicionadas.");
        }
    }
    public List<Elfo> getElfos() {
        return elfos;
    }

    public List<Crianca> getCriancas() {
        return criancas;
    }
}
