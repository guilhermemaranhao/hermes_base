package br.ufg.inf.mestrado.hermesbase.coreDX.configuracao;

// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.
import com.toc.coredx.DDS.*;
import java.nio.*;
import java.nio.charset.*;


public final class ConfiguracaoTypeTypeSupport implements TypeSupport{
  
  public ReturnCode_t register_type(DomainParticipant dp, String type_name) {
    if (dp.check_version( "3", "6", "8" ) != 0) {
      System.out.println( "WARNING: ConfiguracaoType TypeSupport version does not match CoreDX Library version.");
      System.out.println( "This may cause software instability or crashes.");
    }
    return dp.register_type(this, type_name);
  }

  public String get_type_name()   { return "ConfiguracaoType"; }
  public long   getCTypeSupport() { return cTypeSupport; }

  public ConfiguracaoTypeTypeSupport() {
    ConfiguracaoType tmp = new ConfiguracaoType();
    cTypeSupport = DomainParticipant.createTypeSupport(this, 
                     getClass().getName(), tmp.getClass().getName());
  }

  // ------------------------------------------------------
  // implementation

  public DataReader   create_datareader(Subscriber sub, TopicDescription td, 
                                        SWIGTYPE_p__DataReader jni_dr) {
    return new ConfiguracaoTypeDataReader(sub, td, jni_dr);
  }
  public DataWriter   create_datawriter(Publisher  pub, Topic topic, SWIGTYPE_p__DataWriter jni_dw ) {
    return new ConfiguracaoTypeDataWriter(pub, topic, jni_dw);
  }

  // marshal variants
  public int     marshall ( ByteBuffer out_stream,  ConfiguracaoType src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.tipo_inferencia
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.tipo_inferencia == null) size += 1;
      else {
        try {
          byte[] sbytes = src.tipo_inferencia.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.topicos
      // src.topicos.length
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      if (src.topicos != null) {
        for (int i2 = 0; i2 < src.topicos.length; i2++ ) {
          // src.topicos[i2]
          size = (size+3) & 0xfffffffc;// align 4
          size += 4; // length
          if (src.topicos[i2] == null) size += 1;
          else {
            try {
              byte[] sbytes = src.topicos[i2].getBytes("UTF-8");
              size += sbytes.length + 1;
            } catch(Exception e) {
              size += 1;
            }
          }
        }
      }
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
      
      
      // src.tipo_inferencia
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.tipo_inferencia == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.tipo_inferencia.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.topicos
      // src.topicos.length
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      offset += 4;
      out_stream.putInt(src.topicos.length);
      for (int i2 = 0; i2 < src.topicos.length; i2++ ) {
          // src.topicos[i2]
          while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
          if (src.topicos[i2] == null) {
           out_stream.putInt(1);
          }
          else {
            try {
              byte[] sbytes = src.topicos[i2].getBytes("UTF-8");
              out_stream.putInt(sbytes.length+1);
              out_stream.put(sbytes);
              offset += sbytes.length;
            } catch(Exception e) {
              out_stream.putInt(1);
            }
          }
          out_stream.put((byte)0);
          offset += 4 + 1;
      }
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_fixed_size (  ) { return 0; }
  public int     marshall_key ( ByteBuffer out_stream,  ConfiguracaoType src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.tipo_inferencia
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.tipo_inferencia == null) size += 1;
      else {
        try {
          byte[] sbytes = src.tipo_inferencia.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
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
      
      // src.tipo_inferencia
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.tipo_inferencia == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.tipo_inferencia.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      size = out_stream.position();
    }
    return size;
  }

  public int     marshall_key_hash ( ByteBuffer out_stream,  ConfiguracaoType src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.tipo_inferencia
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.tipo_inferencia == null) size += 1;
      else {
        try {
          byte[] sbytes = src.tipo_inferencia.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
    } else {
      int offset = 0;
      out_stream.clear();
      out_stream.order(ByteOrder.BIG_ENDIAN);
      
      // src.tipo_inferencia
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.tipo_inferencia == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.tipo_inferencia.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      size = out_stream.position();
    }
    return size;
  }

  public boolean key_must_hash (  ) { return true; }

  // unmarshal variants
  public int     unmarshall ( ConfiguracaoType t, ByteBuffer data, int s )    {
    int offset = 0;
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0xFE)==0) { // CDR encoding
      // t.tipo_inferencia
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.tipo_inferencia   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.tipo_inferencia   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.topicos
      // t.topicos.length
      while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); } // align 4
      offset += 4;
      int    slen2 = data.getInt();
      t.topicos = new String[slen2];
      for (int i2 = 0; i2 < t.topicos.length; i2++ ) {
        // t.topicos[i2]
        {
          while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
          int    slen   = data.getInt()-1;  // skip trailing null
          byte[] sbytes = new byte[slen];
          data.get(sbytes, 0, slen);
          data.get(); // skip trailing null
          try {;
            t.topicos[i2]   = new String(sbytes, "UTF-8");
          } catch(java.io.UnsupportedEncodingException e) {
            t.topicos[i2]   = new String(); }
          offset += 4 + slen + 1;
        }
      }
    }
    return 1; // 1==success
  }

  public int     unmarshall_key( ConfiguracaoType t, ByteBuffer data, int s ) {
    int offset = 0;
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0xFE)==0) { // CDR encoding
      // t.tipo_inferencia
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.tipo_inferencia   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.tipo_inferencia   = new String(); }
        offset += 4 + slen + 1;
      }
    }
    return 1; // 1==success
  }

  public int     unmarshall_key_hash( ConfiguracaoType t, ByteBuffer data, int s ) {
    int offset = 0;
    data.order(ByteOrder.BIG_ENDIAN);
    // t.tipo_inferencia
    {
      while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
      int    slen   = data.getInt()-1;  // skip trailing null
      byte[] sbytes = new byte[slen];
      data.get(sbytes, 0, slen);
      data.get(); // skip trailing null
      try {;
        t.tipo_inferencia   = new String(sbytes, "UTF-8");
      } catch(java.io.UnsupportedEncodingException e) {
        t.tipo_inferencia   = new String(); }
      offset += 4 + slen + 1;
    }
    return 0;
  }

  public int gen_typecode( ByteBuffer b ) {
    byte[] tc_data = { 
(byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x7a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'C', (byte)'o', (byte)'n', (byte)'f', (byte)'i', (byte)'g', (byte)'u', (byte)'r', (byte)'a', (byte)'c', (byte)'a', (byte)'o', (byte)'T', (byte)'y', (byte)'p', (byte)'e', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x02, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'t', (byte)'i', (byte)'p', (byte)'o', (byte)'_', (byte)'i', (byte)'n', (byte)'f', (byte)'e', (byte)'r', (byte)'e', (byte)'n', (byte)'c', (byte)'i', (byte)'a', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x2e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'t', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)'o', (byte)'s', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x12, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,     };
    if (b != null) b.put(tc_data, 0, tc_data.length);
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
  public ConfiguracaoType         alloc ()              { return new ConfiguracaoType(); }
  public void       clear (ConfiguracaoType instance)   { instance.clear(); }
  public void       destroy (ConfiguracaoType instance) { /* noop */ }
  public void       copy (ConfiguracaoType to, ConfiguracaoType from) { to.copy(from); }
  public boolean    get_field( String fieldname, CoreDX_FieldDef fdef ) { 
    return false;
  }
  private long cTypeSupport = 0;
}; // ConfiguracaoType
