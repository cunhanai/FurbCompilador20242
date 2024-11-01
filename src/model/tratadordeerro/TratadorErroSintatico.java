/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tratadordeerro;

import model.erro.SyntaticError;

/**
 *
 * @author thomh
 */
public class TratadorErroSintatico extends TratadorErro<SyntaticError>{

    public TratadorErroSintatico(String editorText) {
        super.setEditorText(editorText);
    }

    @Override
    public String gerarMensagemDeErro(SyntaticError e) {
        String linhaErro = super.gerarLinhaErro(e.getPosition());
        int tamanhoEspacamento = gerarTamanhoEspacamentoEsperado(linhaErro);

        return linhaErro + "encontrado " + super.getLexemaErro(e.getPosition()) + gerarTokensEsperados(tamanhoEspacamento, e.getMessage());
    }
    
    private int gerarTamanhoEspacamentoEsperado(String linhaErro){
        return linhaErro.length();
    }
    
    private String gerarTokensEsperados(int tamanhoEspacamento, String mensagemErro) {
        int tamanhoEspacamentoTotal = tamanhoEspacamento + mensagemErro.length();
        return "\n" + String.format("%" + tamanhoEspacamentoTotal + "s", mensagemErro);
    }
}
