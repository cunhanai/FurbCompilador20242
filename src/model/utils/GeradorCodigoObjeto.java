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
    private int quantidadeRotulos;
    private List<Token> listaIdentificadores;
    private List<Token> tabelaSimbolos;
    private boolean writePrecisaPularLinha;

    public GeradorCodigoObjeto() {
        operadorRelacional = "";
        codigoObjeto = new LinkedList<>();
        pilhaTipos = new Stack<>();
        pilhaRotulos = new Stack<>();
        quantidadeRotulos = 0;
        listaIdentificadores = new LinkedList<>();
        tabelaSimbolos = new LinkedList<>();
        writePrecisaPularLinha = false;
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

    // #102
    public void inserirIdentificadorNaLista() throws SemanticError {
        for (Token identificador : listaIdentificadores) {
            String lexema = identificador.getLexeme();

            if (identificadorJaExiste(identificador)) {
                throw new SemanticError(lexema + " já declarado", identificador.getPosition());
            } else {
                tabelaSimbolos.add(identificador);
                TiposExpressoes tipoIdentificador = getTipoIdentificador(identificador);
                codigoObjeto.add(TradutorCodigoObjeto.declararIdentificador(tipoIdentificador, lexema));
            }
        }
        listaIdentificadores.clear();
    }

    // #103
    public void armazenarValorNoIdentificador() throws SemanticError {
        TiposExpressoes tipo = pilhaTipos.pop();
        if (tipo == TiposExpressoes.INT64) {
            codigoObjeto.add(TradutorCodigoObjeto.converterFloatParaInt());
        }
        int quantidadeIdentificadores = listaIdentificadores.size();

        for (int i = 0; i < quantidadeIdentificadores - 1; i++) {
            codigoObjeto.add(TradutorCodigoObjeto.duplicarTopoDaPilha());
        }

        for (Token identificador : listaIdentificadores) {
            if (identificadorJaExiste(identificador)) {
                codigoObjeto.add(TradutorCodigoObjeto.armazenarValorNoIdentificador(identificador.getLexeme()));
            } else {
                throw new SemanticError(identificador.getLexeme() + " não declarado", identificador.getPosition());
            }
        }
        listaIdentificadores.clear();
    }

    // #105
    public void armazenarEntradaNoIdentificador(Token identificador) throws SemanticError {
        if (identificadorJaExiste(identificador)) {
            codigoObjeto.add(TradutorCodigoObjeto.lerEntrada());
            TiposExpressoes tipoIdentificador = getTipoIdentificador(identificador);
            if (tipoIdentificador != TiposExpressoes.STRING) {
                codigoObjeto.add(TradutorCodigoObjeto.fazerParseString(tipoIdentificador));
            }
            codigoObjeto.add(TradutorCodigoObjeto.armazenarValorNoIdentificador(identificador.getLexeme()));
        } else {
            throw new SemanticError(identificador.getLexeme() + " não declarado", identificador.getPosition());
        }
    }

    // # 106
    public void enviarMensagemNaLeitura(Token token) {
        String lexema = token.getLexeme();
        codigoObjeto.add(TradutorCodigoObjeto.carregarValorConstanteString(lexema));
        codigoObjeto.add(TradutorCodigoObjeto.escreverNoConsole(TiposExpressoes.STRING));
    }

    // #104
    public void guardarIdentificador(Token token) {
        listaIdentificadores.add(token);
    }

    // #107
    public void escreverNoConsoleQuebraLinha() {
        if (writePrecisaPularLinha) {
            String escreverNoConsole = codigoObjeto.removeLast();
            escreverNoConsole = escreverNoConsole.replaceAll("Write", "WriteLine");

            codigoObjeto.add(escreverNoConsole);
        }
    }

    // #108
    public void escreverNoConsole() {
        TiposExpressoes tipo = pilhaTipos.pop();
        if (tipo == TiposExpressoes.INT64) {
            codigoObjeto.add(TradutorCodigoObjeto.converterFloatParaInt());
        }
        codigoObjeto.add(TradutorCodigoObjeto.escreverNoConsole(tipo));
    }

    // #109
    public void gerarIf() {
        String novoRotulo1 = gerarNovoRotulo();
        pilhaRotulos.push(novoRotulo1);
        String novoRotulo2 = gerarNovoRotulo();

        codigoObjeto.add(TradutorCodigoObjeto.compararFalse(novoRotulo2));
        pilhaRotulos.push(novoRotulo2);
    }

    // #110
    public void gerarElif() {
        String rotuloDesempilhado2 = pilhaRotulos.pop();
        String rotuloDesempilhado1 = pilhaRotulos.pop();

        codigoObjeto.add(TradutorCodigoObjeto.pularParaRotulo(rotuloDesempilhado1));

        pilhaRotulos.push(rotuloDesempilhado1);

        codigoObjeto.add(TradutorCodigoObjeto.adicionarRotulo(rotuloDesempilhado2));

    }

    // #111
    public void gerarElse() {
        String rotulo_desempilhado = pilhaRotulos.pop();

        codigoObjeto.add(TradutorCodigoObjeto.adicionarRotulo(rotulo_desempilhado));
    }

    // #112
    public void criarRotulo() {
        String rotulo = gerarNovoRotulo();

        codigoObjeto.add(TradutorCodigoObjeto.compararFalse(rotulo));

        pilhaRotulos.push(rotulo);
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
        if (identificadorJaExiste(token)) {
            codigoObjeto.add(TradutorCodigoObjeto.carregarIdentificador(lexema));
            TiposExpressoes tipoIdentificador = getTipoIdentificador(token);

            switch (tipoIdentificador) {
                case INT64:
                    pilhaTipos.push(TiposExpressoes.INT64);
                    codigoObjeto.add(TradutorCodigoObjeto.converterIntParaFloat());
                    break;
                case FLOAT64:
                    pilhaTipos.push(TiposExpressoes.FLOAT64);
                    break;
                case BOOL:
                    pilhaTipos.push(TiposExpressoes.BOOL);
                    break;
                case STRING:
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

    // #132
    public void salvarTipoWrite(Token token) {
        if (token.getLexeme().equals("write")) {
            writePrecisaPularLinha = false;
        } else {
            writePrecisaPularLinha = true;
        }
    }

    private TiposExpressoes calcularTipoResultante(TiposExpressoes operando1, TiposExpressoes operando2) {
        if (operando1 == TiposExpressoes.FLOAT64 || operando2 == TiposExpressoes.FLOAT64) {
            return TiposExpressoes.FLOAT64;
        } else {
            return TiposExpressoes.INT64;
        }
    }

    private boolean identificadorJaExiste(Token identificador) {
        for (Token identificadorDeclarado : tabelaSimbolos) {
            String lexemaIdentificadorNovo = identificador.getLexeme();
            String lexemaIdentificadorDeclarado = identificadorDeclarado.getLexeme();
            if (lexemaIdentificadorNovo.equals(lexemaIdentificadorDeclarado)) {
                return true;
            }
        }
        return false;
    }

    private TiposExpressoes getTipoIdentificador(Token identificador) {
        String lexema = identificador.getLexeme();
        switch (lexema.charAt(0)) {
            case 'i':
                return TiposExpressoes.INT64;
            case 'f':
                return TiposExpressoes.FLOAT64;
            case 'b':
                return TiposExpressoes.BOOL;
            case 's':
                return TiposExpressoes.STRING;
        }
        return null;
    }

    private String gerarNovoRotulo() {
        String rotulo = "L" + String.valueOf(quantidadeRotulos + 1);
        quantidadeRotulos++;
        return rotulo;
    }
}
