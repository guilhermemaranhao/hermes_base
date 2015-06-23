// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.


package br.ufg.inf.mestrado.coreDX;

public class SinalVitalType {
  
  // instance variables
  public int sensor_id;
  public byte[] rdf_serializado;
  
  // constructors
  public SinalVitalType() { }
  public SinalVitalType( int __f1, byte[] __f2 ) {
    sensor_id = __f1;
    rdf_serializado = __f2;
  }
  
  public SinalVitalType init() { 
    rdf_serializado = new byte[0];
    return this;
  }

  public void clear() {
    rdf_serializado = null;
  }
  
  public void copy( SinalVitalType from ) {
    this.sensor_id = from.sensor_id;
    this.rdf_serializado = from.rdf_serializado;
  }
  
}; // SinalVitalType
