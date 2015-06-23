// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.


package br.ufg.inf.mestrado.hermesbase.coreDX.configuracao;

public class ConfiguracaoType {
  
  // instance variables
  public String tipo_inferencia;
  public String[] topicos;
  
  // constructors
  public ConfiguracaoType() {}
  public ConfiguracaoType( String __f1, String[] __f2 ) {
    tipo_inferencia = __f1;
    topicos = __f2;
  }
  
  public void clear() {
    tipo_inferencia = null;
    topicos = null;
  }
  
  public void copy( ConfiguracaoType from ) {
    this.tipo_inferencia = from.tipo_inferencia;
    this.topicos = from.topicos;
  }
  
}; // ConfiguracaoType
