/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.erro.LexicalError;
import model.erro.SemanticError;
import model.erro.SyntaticError;
import model.tratadordeerro.TratadorErroLexico;
import model.lexico.resources.LexicoExtendido;
import model.lexico.resources.Token;
import model.semantico.resources.SemanticoHandler;
import model.tratadordeerro.TratadorErroSintatico;
import model.sintatico.resources.SintaticoExtendido;
import model.utils.GerenciadorDeArquivoIl;

/**
 *
 * @author thomh
 */
public class ControladorBotaoCompilar {
    
    private String editorText;
    private LexicoExtendido lexico;
    private SintaticoExtendido sintatico;
    private SemanticoHandler semantico;
    private TratadorErroLexico tratadorErroLexico;
    private TratadorErroSintatico tratadorErroSintatico;
    private GerenciadorDeArquivoIl gerenciadorDeArquivoIl;
    
    public ControladorBotaoCompilar(String editorText, String localArquivo) {
        this.editorText = editorText;
        lexico = new LexicoExtendido();
        sintatico = new SintaticoExtendido();
        semantico = new SemanticoHandler();
        tratadorErroLexico = new TratadorErroLexico(editorText);
        tratadorErroSintatico = new TratadorErroSintatico(editorText);
        gerenciadorDeArquivoIl = new GerenciadorDeArquivoIl(localArquivo);
    }
    
    public String enviarEstadoDaCompilacao() {
        try {
            lexico.setInput(editorText);
            sintatico.parse(lexico, semantico);
            String programa = semantico.transcreverCodigoObjeto();
            gerenciadorDeArquivoIl.salvarArquivoIl(programa);
        } catch (LexicalError e) {
            return tratadorErroLexico.gerarMensagemDeErro(e);
        } catch (SyntaticError e) {
            Token currentToken = sintatico.getCurrentToken();
            tratadorErroSintatico.setCurrentToken(currentToken);
            return tratadorErroSintatico.gerarMensagemDeErro(e);	
        } catch (SemanticError e) {
            //Trata erros semânticos
        }        
        return "programa compilado com sucesso";
    }
}
