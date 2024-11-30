package model.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author thomh
 */
public class GerenciadorDeArquivoIl {

    private String localArquivo;

    public GerenciadorDeArquivoIl(String localArquivo) {
        this.localArquivo = getArquivoIl(localArquivo);
    }

    public void salvarArquivoIl(String programa) {
        File arquivoIl = new File(localArquivo);
        escreverNoArquivo(arquivoIl, programa);
    }
    
    private String getArquivoIl(String localArquivo){
        String nomeArquivo = localArquivo;
        nomeArquivo = nomeArquivo.replaceAll(".txt",".il");
        return nomeArquivo;
    }

    private void escreverNoArquivo(File arquivo, String programa) {
        try {
            FileWriter arquivoWriter = new FileWriter(arquivo);
            arquivoWriter.write(programa);
            arquivoWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorDeArquivoIl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
