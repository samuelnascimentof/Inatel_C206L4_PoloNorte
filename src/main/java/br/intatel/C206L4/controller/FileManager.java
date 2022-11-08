package br.intatel.C206L4.controller;

import br.intatel.C206L4.Exception.EntradaInvalidaException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class FileManager {

    private static final String INPUT_FILE_HEADER = "Insira os dados das crianças boazinhas separados por pipe \"|\" no padrão:\nnome|idade|nacionalidade|presente";
    private static final String PIPE = "\\|";
    private static final int NUMBER_OF_FIELDS = 4;
    public static final String lineSeparator = "\n--------------------------------------------------\n";

    public static void writeTxt(String filePath, String content) {
        final Path path = Paths.get(filePath);
        try {
            Files.write(path, content.concat(System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo \"".concat(path.toString()).concat("\""));
        }
    }

    public static List<String[]> readTxt(String filePath) throws NoSuchFileException {
        final Path path = Paths.get(filePath);
        List<String> lines = null;
        String[] fields;
        List<String[]> lineFields = new ArrayList<>();
        try (Stream<String> stream = Files.lines(path)) {
            lines = stream.collect(Collectors.toList());
            if (!lines.isEmpty() && !(lines.size() == 2)) {
                for (String line : lines) {
                    if ((lines.get(0) != line) && (lines.get(1) != line)) {
                        if (line.split(PIPE).length != NUMBER_OF_FIELDS) {
                            throw new EntradaInvalidaException("Erro: O arquivo de entrada \"" + path.getFileName().toString() + "\" não foi preenchido corretamente.");
                        } else {
                            fields = line.split(PIPE);
                            lineFields.add(fields);
                        }
                    }

                }

                clearInputFile(filePath);
                return lineFields;
            }
        } catch (IOException | EntradaInvalidaException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
        if (lines.isEmpty() || lines.size() == 2) {
            throw new NoSuchFileException("Não há crianças boazinhas para serem adicionadas.");
        }
        return null;
    }

    public static void clearInputFile(String filePath) {
        final Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("");
            writer.flush();
            writeTxt(filePath, INPUT_FILE_HEADER);
        } catch (IOException e) {
            System.out.println("Erro ao deletar o conteúdo do arquivo ".concat(path.toString()));
        }
    }

    public static void clearOutputFile(String filePath) {
        final Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Erro ao deletar o conteúdo do arquivo ".concat(path.toString()));
        }
    }
}