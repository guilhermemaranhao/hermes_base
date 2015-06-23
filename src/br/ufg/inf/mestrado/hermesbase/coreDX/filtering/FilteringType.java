// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.


package br.ufg.inf.mestrado.hermesbase.coreDX.filtering;

public class FilteringType {
  
  // instance variables
  public String idListener;
  public String nomeTopico;
  public byte[] filtroJson;
  public String consultaFiltro;
  
  // constructors
  public FilteringType() {}
  public FilteringType( String __f1, String __f2, byte[] __f3, String __f4 ) {
    idListener = __f1;
    nomeTopico = __f2;
    filtroJson = __f3;
    consultaFiltro = __f4;
  }
  
  public void clear() {
    idListener = null;
    nomeTopico = null;
    filtroJson = null;
    consultaFiltro = null;
  }
  
  public void copy( FilteringType from ) {
    this.idListener = from.idListener;
    this.nomeTopico = from.nomeTopico;
    this.filtroJson = from.filtroJson;
    this.consultaFiltro = from.consultaFiltro;
  }
  
}; // FilteringType
