package br.ufg.inf.mestrado.coreDX;

// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.toc.coredx.DDS.CoreDX_FieldDef;
import com.toc.coredx.DDS.DataReader;
import com.toc.coredx.DDS.DataWriter;
import com.toc.coredx.DDS.DomainParticipant;
import com.toc.coredx.DDS.Publisher;
import com.toc.coredx.DDS.ReturnCode_t;
import com.toc.coredx.DDS.SWIGTYPE_p__DataReader;
import com.toc.coredx.DDS.SWIGTYPE_p__DataWriter;
import com.toc.coredx.DDS.Subscriber;
import com.toc.coredx.DDS.Topic;
import com.toc.coredx.DDS.TopicDescription;
import com.toc.coredx.DDS.TypeSupport;


public final class SinalVitalTypeTypeSupport implements TypeSupport{
  
  public ReturnCode_t register_type(DomainParticipant dp, String type_name) {
    if (dp.check_version( "3", "6", "14" ) != 0) {
      System.out.println( "WARNING: SinalVitalType TypeSupport version does not match CoreDX Library version.");
      System.out.println( "This may cause software instability or crashes.");
    }
    return dp.register_type(this, type_name);
  }

  public String get_type_name()   { return "SinalVitalType"; }
  public long   getCTypeSupport() { return cTypeSupport; }

  public SinalVitalTypeTypeSupport() {
    SinalVitalType tmp = new SinalVitalType();
    cTypeSupport = DomainParticipant.createTypeSupport(this, 
                     getClass().getName(), tmp.getClass().getName());
  }

  // ------------------------------------------------------
  // implementation

  public DataReader   create_datareader(Subscriber sub, TopicDescription td, 
                                        SWIGTYPE_p__DataReader jni_dr) {
    return new SinalVitalTypeDataReader(sub, td, jni_dr);
  }
  public DataWriter   create_datawriter(Publisher  pub, Topic topic, SWIGTYPE_p__DataWriter jni_dw ) {
    return new SinalVitalTypeDataWriter(pub, topic, jni_dw);
  }

  // marshal variants
  public int     marshall ( ByteBuffer out_stream,  SinalVitalType src ) {
    int size = 0;
    if ( out_stream == null ) {
      size = marshal_size_core(out_stream, size, src);
      size += 4; // for CDR 'header' 
    } else {
      int offset = 0;
      out_stream.clear();
      if ((1 & 0x01)==0) out_stream.order(ByteOrder.BIG_ENDIAN);
      else               out_stream.order(ByteOrder.LITTLE_ENDIAN);
      
      // add CDR 'header' 
      out_stream.put((byte)0x00);
      out_stream.put((byte)1);
      out_stream.put((byte)0x00); // flags
      out_stream.put((byte)0x00); // flags
      
      marshal_core(out_stream, offset, src);
      size = out_stream.position();
    }
    return size;
  }

  public int     marshal_size_core ( ByteBuffer out_stream, int size, br.ufg.inf.mestrado.coreDX.SinalVitalType src ) {
    // src.sensor_id
    size = (size+3) & 0xfffffffc;// align 4
    size += 4;
    // src.rdf_serializado
    // src.rdf_serializado.length
    size = (size+3) & 0xfffffffc;// align 4
    size += 4;
    if (src.rdf_serializado != null) {
      for (int i2 = 0; i2 < src.rdf_serializado.length; i2++ ) {
        // src.rdf_serializado[i2]
        size += 1;
      }
    }
    return size;
  }

  public int     marshal_core ( ByteBuffer out_stream, int offset, br.ufg.inf.mestrado.coreDX.SinalVitalType src ) {
    // src.sensor_id
    while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
    offset += 4;
    out_stream.putInt(src.sensor_id);
    // src.rdf_serializado
    // src.rdf_serializado.length
    while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
    offset += 4;
    out_stream.putInt(src.rdf_serializado.length);
    for (int i2 = 0; i2 < src.rdf_serializado.length; i2++ ) {
        // src.rdf_serializado[i2]
        offset++;
        out_stream.put((byte)src.rdf_serializado[i2]);
    }
    return offset;
  }

  public int     marshall_fixed_size (  ) { return 0; }
  public int     marshall_key ( ByteBuffer out_stream,  SinalVitalType src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.sensor_id
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      size += 4; // for CDR 'header' 
    } else {
      int offset = 0;
      out_stream.clear();
      if ((1 & 0x01)==0) out_stream.order(ByteOrder.BIG_ENDIAN);
      else               out_stream.order(ByteOrder.LITTLE_ENDIAN);
      
      // add CDR 'header' 
      out_stream.put((byte)0x00);
      out_stream.put((byte)1);
      out_stream.put((byte)0x00); // flags
      out_stream.put((byte)0x00); // flags
      
      // src.sensor_id
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      offset += 4;
      out_stream.putInt(src.sensor_id);
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_key_hash ( ByteBuffer out_stream,  SinalVitalType src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.sensor_id
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
    } else {
      int offset = 0;
      out_stream.clear();
      out_stream.order(ByteOrder.BIG_ENDIAN);
      
      // src.sensor_id
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      offset += 4;
      out_stream.putInt(src.sensor_id);
      size = out_stream.position();
    }
    return size;
  }

  public boolean key_must_hash (  ) { return false; }

  // unmarshal variants
  public int     unmarshall ( SinalVitalType t, ByteBuffer data, int s )    {
    int offset = 0;
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0xFE)==0) { // CDR encoding
      unmarshal_core(t, data, offset, s);
    }
    return 1; // 1==success
  }

  public int     unmarshal_core ( br.ufg.inf.mestrado.coreDX.SinalVitalType t, ByteBuffer data, int offset, int s )    {
    // t.sensor_id
    while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); } // align 4
    offset += 4;
    t.sensor_id = data.getInt();
    // t.rdf_serializado
    // t.rdf_serializado.length
    while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); } // align 4
    offset += 4;
    int    slen2 = data.getInt();
    t.rdf_serializado = new byte[slen2];
    for (int i2 = 0; i2 < t.rdf_serializado.length; i2++ ) {
      // t.rdf_serializado[i2]
      offset++;
      t.rdf_serializado[i2] = data.get();
    }
    return offset; // >0==success
  }

  public int     unmarshall_key( SinalVitalType t, ByteBuffer data, int s ) {
    int offset = 0;
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0xFE)==0) { // CDR encoding
      // t.sensor_id
      while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); } // align 4
      offset += 4;
      t.sensor_id = data.getInt();
    }
    return 1; // 1==success
  }

  public int     unmarshall_key_hash( SinalVitalType t, ByteBuffer data, int s ) {
    int offset = 0;
    data.order(ByteOrder.BIG_ENDIAN);
    // t.sensor_id
    while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); } // align 4
    offset += 4;
    t.sensor_id = data.getInt();
    return 0;
  }

  public int gen_typecode( ByteBuffer b ) {
    String tc_0000 = 
     "\n\u0000\u0000\u0000\u0068\u0000\u0000\u0000\u000f\u0000\u0000\u0000Sina" + 
     "lVitalType\u0000\u0000\u0002\u0000\u0000\u0000\u001c\u0000\u0000\u0000\n" + 
     "\u0000\u0000\u0000sensor_id\u0000\u0000\u0000\u00ff\u00ff\u0001\u0000\u0002" + 
     "\u0000\u0000\u0000\u0000\u0000\u002e\u0000\u0010\u0000\u0000\u0000rdf_se" + 
     "rializado\u0000\u0000\u0000\u00ff\u00ff\u0000\u0000\u0000\u0000\u000e\u0000" + 
     "\u0000\u0000\u000c\u0000\u0000\u0000\u00ff\u00ff\u00ff\u00ff\u0009\u0000" + 
     "\u0000\u0000\u0000\u0000";
    byte[] tc_data = new byte[ 110 ];
    int j = 0;
    for(int i=0; i < tc_0000.length(); i++) {
      tc_data[j++] = (byte)tc_0000.charAt(i);
    }
    if (b != null) b.put(tc_data, 0, 110);
    return tc_data.length;
  }

  public int get_typecode_enc( ) {
    return (1 & 0x01);
  }

  public int get_encoding( ) {
    return 0x0;
  }

  public int get_decoding( ) {
    return 0x0;
  }

  // key field operations
  public boolean has_key (  ) { return true; }

  // <type> operations
  public SinalVitalType         alloc ()              { return new SinalVitalType(); }
  public void       clear (SinalVitalType instance)   { instance.clear(); }
  public void       destroy (SinalVitalType instance) { /* noop */ }
  public void       copy (SinalVitalType to, SinalVitalType from) { to.copy(from); }
  public boolean    get_field( String fieldname, CoreDX_FieldDef fdef ) { 
    return false;
  }
  private long cTypeSupport = 0;
}; // SinalVitalType
