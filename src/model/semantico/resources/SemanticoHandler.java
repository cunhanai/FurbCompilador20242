/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.semantico.resources;

import java.util.List;
import model.erro.SemanticError;
import model.lexico.resources.Token;
import model.utils.GeradorCodigoObjeto;

/**
 *
 * @author tahaskel
 */
public class SemanticoHandler extends Semantico {

    private GeradorCodigoObjeto geradorCodigoObjeto;

    public SemanticoHandler() {
        super();
        geradorCodigoObjeto = new GeradorCodigoObjeto();
    }

    @Override
    public void executeAction(int action, Token token) throws SemanticError {
        super.executeAction(action, token);
        switch (action) {
            case 100:
                geradorCodigoObjeto.gerarCabecalho("_codigo_objeto", "UNICA", "_principal");
                break;
            case 101:
                geradorCodigoObjeto.finalizarPrograma();
                break;
            case 102:
                geradorCodigoObjeto.inserirIdentificadorNaLista();
                break;
            case 103:
                geradorCodigoObjeto.armazenarValorNoIdentificador();
                break;
            case 104:
                geradorCodigoObjeto.guardarIdentificador(token);
                break;
            case 105:
                geradorCodigoObjeto.armazenarEntradaNoIdentificador(token);
                break;
            case 106:
                geradorCodigoObjeto.enviarMensagemNaLeitura(token);
                break;
            case 107:
                geradorCodigoObjeto.escreverNoConsoleQuebraLinha();
                break;
            case 108:
                geradorCodigoObjeto.escreverNoConsole();
                break;
            case 109:
                geradorCodigoObjeto.gerarIf();
                break;
            case 110:
                geradorCodigoObjeto.gerarElif();
                break;
            case 111:
                geradorCodigoObjeto.gerarElse();
                break;
            case 112:
                geradorCodigoObjeto.criarRotulo();
                break;
            case 113:
                // PARTE 14 e 15
                break;
            case 114:
                // PARTE 14
                break;
            case 115:
                // PARTE 15
                break;
            case 116:
               geradorCodigoObjeto.gerarOperacaoE();
                break;
            case 117:
                geradorCodigoObjeto.gerarOperacaoOu();
                break;
            case 118:
                geradorCodigoObjeto.empilharBoolean(true);
                break;
            case 119:
                geradorCodigoObjeto.empilharBoolean(false);
                break;
            case 120:
                geradorCodigoObjeto.realizarNegacao();
                break;
            case 121:
                geradorCodigoObjeto.guardarOperadorRelacional(token);
                break;
            case 122:
                geradorCodigoObjeto.realizarOperacaoRelacional();
                break;
            case 123:
                geradorCodigoObjeto.gerarAdicao();
                break;
            case 124:
                geradorCodigoObjeto.gerarSubtracao();
                break;
            case 125:
                geradorCodigoObjeto.gerarMultiplicacao();
                break;
            case 126:
                geradorCodigoObjeto.gerarDivisao();
                break;
            case 127:
                geradorCodigoObjeto.carregarIdentificador(token);
                break;
            case 128:
                geradorCodigoObjeto.empilharInt64(token);
                break;
            case 129:
                geradorCodigoObjeto.empilharFloat64(token);
                break;
            case 130:
                geradorCodigoObjeto.empilharString(token);
                break;
            case 131:
                geradorCodigoObjeto.transformarEmNegativo();
                break;
            case 132:
                geradorCodigoObjeto.salvarTipoWrite(token);
                break;
            default:
                System.out.println("acao ainda nao implementada");
        }
    }

    public String transcreverCodigoObjeto() {
        List<String> codigoObjeto = geradorCodigoObjeto.getCodigoObjeto();
        String codigoCompleto = "";

        for (String string : codigoObjeto) {
            codigoCompleto += string + "\n";
        }

        return codigoCompleto;
    }
}
