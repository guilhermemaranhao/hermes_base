// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.


package br.ufg.inf.mestrado.hermesbase.coreDX.notificacao;

public class NotificacaoType {
  
  // instance variables
  public String id_entidade;
  public String nome_topico;
  public String complemento_topico;
  public String caminho_ontologia;
  public byte[] rdfSerializado;
  public String tipo_serializacao;
  public String tipo_inferencia;
  
  // constructors
  public NotificacaoType() {}
  public NotificacaoType( String __f1, String __f2, String __f3, String __f4, byte[] __f5, String __f6, String __f7 ) {
    id_entidade = __f1;
    nome_topico = __f2;
    complemento_topico = __f3;
    caminho_ontologia = __f4;
    rdfSerializado = __f5;
    tipo_serializacao = __f6;
    tipo_inferencia = __f7;
  }
  
  public void clear() {
    id_entidade = null;
    nome_topico = null;
    complemento_topico = null;
    caminho_ontologia = null;
    rdfSerializado = null;
    tipo_serializacao = null;
    tipo_inferencia = null;
  }
  
  public void copy( NotificacaoType from ) {
    this.id_entidade = from.id_entidade;
    this.nome_topico = from.nome_topico;
    this.complemento_topico = from.complemento_topico;
    this.caminho_ontologia = from.caminho_ontologia;
    this.rdfSerializado = from.rdfSerializado;
    this.tipo_serializacao = from.tipo_serializacao;
    this.tipo_inferencia = from.tipo_inferencia;
  }
  
}; // NotificacaoType
