/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.utils;

import model.semantico.resources.TiposExpressoes;
import static model.semantico.resources.TiposExpressoes.INT64;

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

    public static String declararIdentificador(TiposExpressoes tipoIdentificador, String identificador) {
        String tipo = converterTipoExpressaoParaString(tipoIdentificador);
        String traduzido = ".locals(" + tipo + " " + identificador + ")";

        return traduzido;
    }
    
    public static String carregarValorConstanteInt(String lexema) {
        String traduzido = "ldc.i8 " + lexema;

        return traduzido;
    }


    public static String carregarValorConstanteFloat(String lexema) {
        String traduzido = "ldc.r8 " + lexema.replaceAll(",",".");

        return traduzido;
    }

    public static String carregarValorConstanteString(String lexema) {
        String traduzido = "ldstr " + lexema;

        return traduzido;
    }

    public static String carregarValorConstanteBoolean(Boolean valorBooleano) {
        String traduzido = "ldc.i4.";
        if (valorBooleano) {
            traduzido += "1";
        } else {
            traduzido += "0";
        }

        return traduzido;
    }

    public static String gerarIgualA() {
        String traduzido = "ceq";

        return traduzido;
    }

    public static String gerarMenorQue() {
        String traduzido = "clt";

        return traduzido;
    }

    public static String gerarMaiorQue() {
        String traduzido = "cgt";

        return traduzido;
    }

    public static String gerarOuExclusivo() {
        String traduzido = "xor";

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

    public static String gerarSubtracao() {
        String traduzido = "sub";

        return traduzido;
    }

    public static String gerarMultiplicacao() {
        String traduzido = "mul";

        return traduzido;
    }

    public static String gerarDivisao() {
        String traduzido = "div";

        return traduzido;
    }

    public static String gerarOperadorE() {
        String traduzido = "and";

        return traduzido;
    }

    public static String gerarOperadorOu() {
        String traduzido = "or";

        return traduzido;
    }

    public static String carregarIdentificador(String lexema) {
        String traduzido = "ldloc " + lexema;

        return traduzido;
    }

    public static String escreverNoConsole(TiposExpressoes tipoExpressao) {
        String tipo = converterTipoExpressaoParaString(tipoExpressao);
        String traduzido = "call void [mscorlib]System.Console::Write(" + tipo + ")";

        return traduzido;
    }

    public static String lerEntrada() {
        String traduzido = "call string [mscorlib]System.Console::ReadLine()";

        return traduzido;
    }

    public static String duplicarTopoDaPilha() {
        String traduzido = "dup";

        return traduzido;
    }

    public static String armazenarValorNoIdentificador(String identificador) {
        String traduzido = "stloc " + identificador;

        return traduzido;
    }

    public static String escreverNoConsoleQuebraLinha(TiposExpressoes tipoExpressao) {
        String tipo = converterTipoExpressaoParaString(tipoExpressao);
        String traduzido = "call void [mscorlib]System.Console::WriteLine(" + tipo + ")";

        return traduzido;
    }

    public static String fazerParseString(TiposExpressoes tipoExpressao) {
        String tipo = converterTipoExpressaoParaString(tipoExpressao);
        String classe = converterTipoExpressaoParaClasse(tipoExpressao);
        String traduzido = "call " + tipo + " [mscorlib]System." + classe + "::Parse(string)";

        return traduzido;
    }

    public static String retornarMetodo() {
        String traduzido = "ret";

        return traduzido;
    }
    
    public static String compararTrue(String br) {
        String traduzido = "brtrue " + br;
        
        return traduzido;
    }

    private static String converterTipoExpressaoParaString(TiposExpressoes tipoExpressao) {
        String tipo = tipoExpressao.toString().toLowerCase();
        return tipo;
    }

    private static String converterTipoExpressaoParaClasse(TiposExpressoes tipoExpressao) {
        String tipo = "";

        switch (tipoExpressao) {
            case INT64:
                tipo = "Int64";
                break;
            case FLOAT64:
                tipo = "Double";
                break;
            case BOOL:
                tipo = "Boolean";
                break;
            default:
                throw new AssertionError();
        }
        return tipo;
    }

    public static String compararFalse(String br) {
        String traduzido = "brfalse " + br;
        
        return traduzido;
    }
    
    public static String adicionarRotulo(String rotulo) {
        String traduzido = rotulo + ": ";
        
        return traduzido;
    }
    
    public static String pularParaRotulo(String rotulo) {
        String traduzido = "br " + rotulo;
        
        return traduzido;
    }
}
