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
import model.semantico.resources.Semantico;
import model.semantico.resources.SemanticoHandler;
import model.tratadordeerro.TratadorErroSintatico;
import model.sintatico.resources.SintaticoExtendido;

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
    
    public ControladorBotaoCompilar(String editorText) {
        this.editorText = editorText;
        lexico = new LexicoExtendido();
        sintatico = new SintaticoExtendido();
        semantico = new SemanticoHandler();
        tratadorErroLexico = new TratadorErroLexico(editorText);
        tratadorErroSintatico = new TratadorErroSintatico(editorText);
    }
    
    public String enviarEstadoDaCompilacao() {
        try {
            lexico.setInput(editorText);
            sintatico.parse(lexico, semantico);
            return semantico.transcreverCodigoObjeto();
        } catch (LexicalError e) {
            return tratadorErroLexico.gerarMensagemDeErro(e);
        } catch (SyntaticError e) {
            return tratadorErroSintatico.gerarMensagemDeErro(e);	
        } catch (SemanticError e) {
            //Trata erros semânticos
        }        
        return "programa compilado com sucesso";
    }

    /*
                Lexico lexico = new Lexico();
		Sintatico sintatico = new Sintatico();
		Semantico semantico = new Semantico();
		lexico.setInput(  [entrada] );
		try
		{
			sintatico.parse(lexico, semantico);    // tradução dirigida pela sintaxe
		}
		// mensagem: programa compilado com sucesso - área reservada para mensagens
		
		catch ( LexicalError e )
		{
			//Trata erros léxicos, conforme especificação da parte 2 - do compilador
		}
		catch ( SyntaticError e )
		{
		       System.out.println(e.getPosition() + " símbolo encontrado: na entrada " + e.getMessage()); 
			 
			//Trata erros sintáticos
			//linha 			      sugestão: converter getPosition em linha
			//símbolo encontrado    sugestão: implementar um método getToken no sintatico
			//símbolos esperados,   alterar ParserConstants.java, String[] PARSER_ERROR
                        // consultar os símbolos esperados no GALS (em Documentação > Tabela de Análise Sintática): 		
		}
		catch ( SemanticError e )
		{
			//Trata erros semânticos
		}*/
}
