/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import model.erro.SemanticError;
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
        tabelaSimbolos = new LinkedList<>();
    }

    public List<String> getCodigoObjeto() {
        return codigoObjeto;
    }

    // #100
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

    // #101
    public void finalizarPrograma() {
        codigoObjeto.add(TradutorCodigoObjeto.retornarMetodo());
        codigoObjeto.add("}");
        codigoObjeto.add("}");
    }

    // #103
    public void guardarValorNoIdentificador() throws SemanticError {
        TiposExpressoes tipo = pilhaTipos.pop();
        if (tipo == TiposExpressoes.INT64) {
            codigoObjeto.add(TradutorCodigoObjeto.converterIntParaFloat());
            tipo = TiposExpressoes.FLOAT64;
        }
        int quantidadeIdentificadores = listaIdentificadores.size();
        
        for (int i = 0; i < quantidadeIdentificadores - 1; i++) {
            codigoObjeto.add(TradutorCodigoObjeto.duplicarTopoDaPilha());
        }
        
        for (String identificador : listaIdentificadores) {
            if (identificadorJaExiste(identificador)) {
                codigoObjeto.add(TradutorCodigoObjeto.armazenarValorNoIdentificador(identificador));
            } else {
                throw new SemanticError(identificador + " não declarado", 0); //CORRIGIR POSICAO
            }
        }
    }

    // #104
    public void guardarIdentificador(Token token) {
        listaIdentificadores.add(token.getLexeme());
    }

    // #107
    public void escreverNoConsoleQuebraLinha() {
        String escreverNoConsole = codigoObjeto.removeLast();
        escreverNoConsole = escreverNoConsole.replaceAll("Write", "WriteLine");

        codigoObjeto.add(escreverNoConsole);
    }

    // #108
    public void escreverNoConsole() {
        TiposExpressoes tipo = pilhaTipos.pop();
        if (tipo == TiposExpressoes.INT64) {
            codigoObjeto.add(TradutorCodigoObjeto.converterIntParaFloat());
            tipo = TiposExpressoes.FLOAT64;
        }
        codigoObjeto.add(TradutorCodigoObjeto.escreverNoConsole(tipo));
    }

    // #116
    public void gerarOperacaoE() {
        TiposExpressoes tipoOperador1 = pilhaTipos.pop();
        TiposExpressoes tipoOperador2 = pilhaTipos.pop();
        TiposExpressoes tipoResultante = calcularTipoResultante(tipoOperador1, tipoOperador2);
        pilhaTipos.push(tipoResultante);

        codigoObjeto.add(TradutorCodigoObjeto.gerarOperadorE());
    }

    // #117
    public void gerarOperacaoOu() {
        TiposExpressoes tipoOperador1 = pilhaTipos.pop();
        TiposExpressoes tipoOperador2 = pilhaTipos.pop();
        TiposExpressoes tipoResultante = calcularTipoResultante(tipoOperador1, tipoOperador2);
        pilhaTipos.push(tipoResultante);

        codigoObjeto.add(TradutorCodigoObjeto.gerarOperadorOu());
    }

    // #118 #119
    public void empilharBoolean(Boolean valorBooleano) {
        pilhaTipos.push(TiposExpressoes.BOOL);
        codigoObjeto.add(TradutorCodigoObjeto.carregarValorConstanteBoolean(valorBooleano));
    }

    // #120
    public void realizarNegacao() {
        codigoObjeto.add(TradutorCodigoObjeto.carregarValorConstanteBoolean(true));
        codigoObjeto.add(TradutorCodigoObjeto.gerarOuExclusivo());
    }

    // #121
    public void guardarOperadorRelacional(Token token) {
        operadorRelacional = token.getLexeme();
    }

    // #122
    public void realizarOperacaoRelacional() {
        TiposExpressoes tipoOperador1 = pilhaTipos.pop();
        TiposExpressoes tipoOperador2 = pilhaTipos.pop();
        TiposExpressoes tipoResultante = calcularTipoResultante(tipoOperador1, tipoOperador2);
        pilhaTipos.push(tipoResultante);

        switch (operadorRelacional) {
            case "==":
                codigoObjeto.add(TradutorCodigoObjeto.gerarIgualA());
                break;
            case "!=":
                codigoObjeto.add(TradutorCodigoObjeto.gerarIgualA());
                codigoObjeto.add(TradutorCodigoObjeto.carregarValorConstanteBoolean(false));
                codigoObjeto.add(TradutorCodigoObjeto.gerarIgualA());
                break;
            case ">":
                codigoObjeto.add(TradutorCodigoObjeto.gerarMaiorQue());
                break;
            case "<":
                codigoObjeto.add(TradutorCodigoObjeto.gerarMenorQue());
                break;
        }

        operadorRelacional = "";
    }

    // #123
    public void gerarAdicao() {
        TiposExpressoes operando1 = pilhaTipos.pop();
        TiposExpressoes operando2 = pilhaTipos.pop();
        TiposExpressoes operadorResultante = calcularTipoResultante(operando1, operando2);
        pilhaTipos.push(operadorResultante);

        codigoObjeto.add(TradutorCodigoObjeto.gerarAdicao());
    }

    // #124
    public void gerarSubtracao() {
        TiposExpressoes operando1 = pilhaTipos.pop();
        TiposExpressoes operando2 = pilhaTipos.pop();
        TiposExpressoes operadorResultante = calcularTipoResultante(operando1, operando2);
        pilhaTipos.push(operadorResultante);

        codigoObjeto.add(TradutorCodigoObjeto.gerarSubtracao());
    }

    // #125
    public void gerarMultiplicacao() {
        TiposExpressoes operando1 = pilhaTipos.pop();
        TiposExpressoes operando2 = pilhaTipos.pop();
        TiposExpressoes operadorResultante = calcularTipoResultante(operando1, operando2);
        pilhaTipos.push(operadorResultante);

        codigoObjeto.add(TradutorCodigoObjeto.gerarMultiplicacao());
    }

    // #126
    public void gerarDivisao() {
        TiposExpressoes operando1 = pilhaTipos.pop();
        TiposExpressoes operando2 = pilhaTipos.pop();
        TiposExpressoes operadorResultante = calcularTipoResultante(operando1, operando2);
        pilhaTipos.push(operadorResultante);

        codigoObjeto.add(TradutorCodigoObjeto.gerarDivisao());
    }

    // #127
    public void carregarIdentificador(Token token) throws SemanticError {
        String lexema = token.getLexeme();
        if (identificadorJaExiste(lexema)) {
            codigoObjeto.add(TradutorCodigoObjeto.carregarIdentificador(lexema));

            switch (lexema.charAt(0)) {
                case 'i':
                    pilhaTipos.push(TiposExpressoes.INT64);
                    codigoObjeto.add(TradutorCodigoObjeto.converterIntParaFloat());
                    break;
                case 'f':
                    pilhaTipos.push(TiposExpressoes.FLOAT64);
                    break;
                case 'b':
                    pilhaTipos.push(TiposExpressoes.BOOL);
                    break;
                case 's':
                    pilhaTipos.push(TiposExpressoes.STRING);
                    break;
            }

        } else {
            throw new SemanticError(lexema + " não declarado", token.getPosition());
        }
    }

    // #128
    public void empilharInt64(Token token) {
        pilhaTipos.push(TiposExpressoes.INT64);
        codigoObjeto.add(TradutorCodigoObjeto.carregarValorConstanteFloat(token.getLexeme()));
        codigoObjeto.add(TradutorCodigoObjeto.converterIntParaFloat());
    }

    // #129
    public void empilharFloat64(Token token) {
        pilhaTipos.push(TiposExpressoes.FLOAT64);
        codigoObjeto.add(TradutorCodigoObjeto.carregarValorConstanteFloat(token.getLexeme()));
    }

    // #130
    public void empilharString(Token token) {
        pilhaTipos.push(TiposExpressoes.STRING);
        codigoObjeto.add(TradutorCodigoObjeto.carregarValorConstanteString(token.getLexeme()));
    }

    // #131
    public void transformarEmNegativo() {
        TiposExpressoes tipo = pilhaTipos.pop();
        pilhaTipos.push(tipo);
        codigoObjeto.add(TradutorCodigoObjeto.carregarValorConstanteFloat("-1"));
        codigoObjeto.add(TradutorCodigoObjeto.gerarMultiplicacao());
    }

    public TiposExpressoes calcularTipoResultante(TiposExpressoes operando1, TiposExpressoes operando2) {
        if (operando1 == TiposExpressoes.FLOAT64 || operando2 == TiposExpressoes.FLOAT64) {
            return TiposExpressoes.FLOAT64;
        } else {
            return TiposExpressoes.INT64;
        }
    }

    public boolean identificadorJaExiste(String lexema) {
        for (String identificadoresRegistrados : tabelaSimbolos) {
            if (lexema.equals(identificadoresRegistrados)) {
                return true;
            }
        }
        return false;
    }

}
