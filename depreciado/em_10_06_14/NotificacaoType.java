// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.


package br.ufg.inf.mestrado.coreDX;

public class NotificacaoType {
  
  // instance variables
  public String id_entidade;
  public String caminho_ontologia;
  public byte[] rdfSerializado;
  public String tipo_serializacao;
  public String tipo_inferencia;
  
  // constructors
  public NotificacaoType() {}
  public NotificacaoType( String __f1, String __f2, byte[] __f3, String __f4, String __f5 ) {
    id_entidade = __f1;
    caminho_ontologia = __f2;
    rdfSerializado = __f3;
    tipo_serializacao = __f4;
    tipo_inferencia = __f5;
  }
  
  public void clear() {
    id_entidade = null;
    caminho_ontologia = null;
    rdfSerializado = null;
    tipo_serializacao = null;
    tipo_inferencia = null;
  }
  
  public void copy( NotificacaoType from ) {
    this.id_entidade = from.id_entidade;
    this.caminho_ontologia = from.caminho_ontologia;
    this.rdfSerializado = from.rdfSerializado;
    this.tipo_serializacao = from.tipo_serializacao;
    this.tipo_inferencia = from.tipo_inferencia;
  }
  
}; // NotificacaoType
