/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.utils;

import model.lexico.resources.Token;
import model.semantico.resources.TiposExpressoes;

/**
 *
 * @author tahaskel
 */
public final class TradutorCodigoObjeto {

    public static String declararMscorlib() {
        String traduzido = ".assembly extern mscorlib {}";

        return traduzido;
    }

    public static String declararNomeCodigo(String nome) {
        nome = nome.toLowerCase().replaceAll(" ", "_");
        String traduzido = ".assembly " + nome + "{}";

        return traduzido;
    }

    public static String definirArquivoExecutavel(String nome) {
        nome = nome.replaceAll(" ", "_");
        if (!nome.endsWith(".exe")) {
            nome = nome + ".exe";
        }
        String traduzido = ".module " + nome;

        return traduzido;
    }

    public static String definirClasse(String nome) {
        nome = nome.replaceAll(" ", "_");
        String traduzido = ".class public " + nome + "{";

        return traduzido;
    }

    public static String iniciarMetodoPrincipal(String nome) {
        nome = nome.replaceAll(" ", "_");
        String traduzido = ".method static public void " + nome + "() {";

        return traduzido;
    }

    public static String definirEntryPoint() {
        String traduzido = ".entrypoint";

        return traduzido;
    }

    public static String carregarValorConstante(Token token) {
        String traduzido = "ldc.i8 " + token.getLexeme();

        return traduzido;
    }

    public static String converterIntParaFloat() {
        String traduzido = "conv.r8";

        return traduzido;
    }

    public static String converterFloatParaInt() {
        String traduzido = "conv.i8";

        return traduzido;
    }
    
    public static String gerarAdicao() {
        String traduzido = "add";
        
        return traduzido;
    }

    public static String escreverNoConsole(TiposExpressoes tipoExpressao) {
        String tipo = tipoExpressao.toString().toLowerCase();
        String traduzido = "call void [mscorlib]System.Console::Write(" + tipo + ")";

        return traduzido;
    }
    
    public static String retornarMetodo(){
        String traduzido = "ret";
        
        return traduzido;
    }

}
