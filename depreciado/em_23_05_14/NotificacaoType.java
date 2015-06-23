// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.


package br.ufg.inf.mestrado.coreDX;

public class NotificacaoType {
  
  // instance variables
  public String instancia_sujeito;
  public String instancia_predicado;
  public String instancia_objeto;
  public String tipo_sujeito;
  public String tipo_predicado;
  public String tipo_objeto;
  public byte[] rdfSerializado;
  public String tipo_serializacao;
  
  // constructors
  public NotificacaoType() {}
  public NotificacaoType( String __f1, String __f2, String __f3, String __f4, String __f5, String __f6, byte[] __f7, String __f8 ) {
    instancia_sujeito = __f1;
    instancia_predicado = __f2;
    instancia_objeto = __f3;
    tipo_sujeito = __f4;
    tipo_predicado = __f5;
    tipo_objeto = __f6;
    rdfSerializado = __f7;
    tipo_serializacao = __f8;
  }
  
  public void clear() {
    instancia_sujeito = null;
    instancia_predicado = null;
    instancia_objeto = null;
    tipo_sujeito = null;
    tipo_predicado = null;
    tipo_objeto = null;
    rdfSerializado = null;
    tipo_serializacao = null;
  }
  
  public void copy( NotificacaoType from ) {
    this.instancia_sujeito = from.instancia_sujeito;
    this.instancia_predicado = from.instancia_predicado;
    this.instancia_objeto = from.instancia_objeto;
    this.tipo_sujeito = from.tipo_sujeito;
    this.tipo_predicado = from.tipo_predicado;
    this.tipo_objeto = from.tipo_objeto;
    this.rdfSerializado = from.rdfSerializado;
    this.tipo_serializacao = from.tipo_serializacao;
  }
  
}; // NotificacaoType
