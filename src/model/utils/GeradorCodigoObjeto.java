/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import model.lexico.resources.Token;
import model.semantico.resources.TiposExpressoes;

/**
 *
 * @author thomh
 */
public class GeradorCodigoObjeto {

    private String operadorRelacional;
    private List<String> codigoObjeto;
    private Stack<TiposExpressoes> pilhaTipos;
    private Stack<String> pilhaRotulos;
    private List<String> listaIdentificadores;
    private List<String> tabelaSimbolos;
    
    public GeradorCodigoObjeto() {
        codigoObjeto = new LinkedList<>();
        pilhaTipos = new Stack<>();
    }

    public List<String> getCodigoObjeto() {
        return codigoObjeto;
    }

    public void gerarCabecalho(String nomeArquivo, String nomeClasse, String nomeMetodoPrincipal) {
        codigoObjeto.add(TradutorCodigoObjeto.declararMscorlib());
        codigoObjeto.add(TradutorCodigoObjeto.declararNomeCodigo(nomeArquivo));
        codigoObjeto.add(TradutorCodigoObjeto.definirArquivoExecutavel(nomeArquivo));
        codigoObjeto.add("");
        codigoObjeto.add(TradutorCodigoObjeto.definirClasse(nomeClasse));
        codigoObjeto.add("");
        codigoObjeto.add(TradutorCodigoObjeto.iniciarMetodoPrincipal(nomeMetodoPrincipal));
        codigoObjeto.add(TradutorCodigoObjeto.definirEntryPoint());
        codigoObjeto.add("");
    }

    public void empilharInt64(Token token) {
        pilhaTipos.push(TiposExpressoes.INT64);
        codigoObjeto.add(TradutorCodigoObjeto.carregarValorConstante(token));
        codigoObjeto.add(TradutorCodigoObjeto.converterIntParaFloat());
    }

    public void empilharFloat64(Token token) {
        pilhaTipos.push(TiposExpressoes.FLOAT64);
        codigoObjeto.add(TradutorCodigoObjeto.carregarValorConstante(token));
    }

    public void gerarAdicao() {
        TiposExpressoes operando1 = pilhaTipos.pop();
        TiposExpressoes operando2 = pilhaTipos.pop();
        TiposExpressoes operadorResultante = calcularTipoResultante(operando1, operando2);
        pilhaTipos.push(operadorResultante);
        
        codigoObjeto.add(TradutorCodigoObjeto.gerarAdicao());
    }

    public void escreverNoConsole() {
        TiposExpressoes tipo = pilhaTipos.pop();
        if (tipo == tipo.INT64) {
            codigoObjeto.add(TradutorCodigoObjeto.converterIntParaFloat());
            tipo = TiposExpressoes.FLOAT64;
        }
        codigoObjeto.add(TradutorCodigoObjeto.escreverNoConsole(tipo));
    }

    public void finalizarPrograma() {
        codigoObjeto.add(TradutorCodigoObjeto.retornarMetodo());
        codigoObjeto.add("}");
        codigoObjeto.add("}");
    }

    public TiposExpressoes calcularTipoResultante(TiposExpressoes operando1, TiposExpressoes operando2) {
        if (operando1 == TiposExpressoes.FLOAT64 || operando2 == TiposExpressoes.FLOAT64) {
            return TiposExpressoes.FLOAT64;
        } else {
            return TiposExpressoes.INT64;
        }
    }
}
