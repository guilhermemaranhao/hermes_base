package br.ufg.inf.mestrado.hermesbase.coreDX.filtering;

// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.
import com.toc.coredx.DDS.*;
import java.nio.*;
import java.nio.charset.*;


public final class FilteringTypeTypeSupport implements TypeSupport{
  
  public ReturnCode_t register_type(DomainParticipant dp, String type_name) {
    if (dp.check_version( "3", "6", "8" ) != 0) {
      System.out.println( "WARNING: FilteringType TypeSupport version does not match CoreDX Library version.");
      System.out.println( "This may cause software instability or crashes.");
    }
    return dp.register_type(this, type_name);
  }

  public String get_type_name()   { return "FilteringType"; }
  public long   getCTypeSupport() { return cTypeSupport; }

  public FilteringTypeTypeSupport() {
    FilteringType tmp = new FilteringType();
    cTypeSupport = DomainParticipant.createTypeSupport(this, 
                     getClass().getName(), tmp.getClass().getName());
  }

  // ------------------------------------------------------
  // implementation

  public DataReader   create_datareader(Subscriber sub, TopicDescription td, 
                                        SWIGTYPE_p__DataReader jni_dr) {
    return new FilteringTypeDataReader(sub, td, jni_dr);
  }
  public DataWriter   create_datawriter(Publisher  pub, Topic topic, SWIGTYPE_p__DataWriter jni_dw ) {
    return new FilteringTypeDataWriter(pub, topic, jni_dw);
  }

  // marshal variants
  public int     marshall ( ByteBuffer out_stream,  FilteringType src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.idListener
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.idListener == null) size += 1;
      else {
        try {
          byte[] sbytes = src.idListener.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.nomeTopico
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.nomeTopico == null) size += 1;
      else {
        try {
          byte[] sbytes = src.nomeTopico.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.filtroJson
      // src.filtroJson.length
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      if (src.filtroJson != null) {
        for (int i3 = 0; i3 < src.filtroJson.length; i3++ ) {
          // src.filtroJson[i3]
          size += 1;
        }
      }
      // src.consultaFiltro
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.consultaFiltro == null) size += 1;
      else {
        try {
          byte[] sbytes = src.consultaFiltro.getBytes("UTF-8");
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
      
      
      // src.idListener
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.idListener == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.idListener.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.nomeTopico
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.nomeTopico == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.nomeTopico.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.filtroJson
      // src.filtroJson.length
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      offset += 4;
      out_stream.putInt(src.filtroJson.length);
      for (int i3 = 0; i3 < src.filtroJson.length; i3++ ) {
          // src.filtroJson[i3]
          offset++;
          out_stream.put((byte)src.filtroJson[i3]);
      }
      // src.consultaFiltro
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.consultaFiltro == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.consultaFiltro.getBytes("UTF-8");
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

  public int     marshall_fixed_size (  ) { return 0; }
  public int     marshall_key ( ByteBuffer out_stream,  FilteringType src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.idListener
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.idListener == null) size += 1;
      else {
        try {
          byte[] sbytes = src.idListener.getBytes("UTF-8");
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
      
      // src.idListener
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.idListener == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.idListener.getBytes("UTF-8");
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

  public int     marshall_key_hash ( ByteBuffer out_stream,  FilteringType src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.idListener
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.idListener == null) size += 1;
      else {
        try {
          byte[] sbytes = src.idListener.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
    } else {
      int offset = 0;
      out_stream.clear();
      out_stream.order(ByteOrder.BIG_ENDIAN);
      
      // src.idListener
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.idListener == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.idListener.getBytes("UTF-8");
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
  public int     unmarshall ( FilteringType t, ByteBuffer data, int s )    {
    int offset = 0;
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0xFE)==0) { // CDR encoding
      // t.idListener
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.idListener   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.idListener   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.nomeTopico
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.nomeTopico   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.nomeTopico   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.filtroJson
      // t.filtroJson.length
      while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); } // align 4
      offset += 4;
      int    slen3 = data.getInt();
      t.filtroJson = new byte[slen3];
      for (int i3 = 0; i3 < t.filtroJson.length; i3++ ) {
        // t.filtroJson[i3]
        offset++;
        t.filtroJson[i3] = data.get();
      }
      // t.consultaFiltro
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.consultaFiltro   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.consultaFiltro   = new String(); }
        offset += 4 + slen + 1;
      }
    }
    return 1; // 1==success
  }

  public int     unmarshall_key( FilteringType t, ByteBuffer data, int s ) {
    int offset = 0;
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0xFE)==0) { // CDR encoding
      // t.idListener
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.idListener   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.idListener   = new String(); }
        offset += 4 + slen + 1;
      }
    }
    return 1; // 1==success
  }

  public int     unmarshall_key_hash( FilteringType t, ByteBuffer data, int s ) {
    int offset = 0;
    data.order(ByteOrder.BIG_ENDIAN);
    // t.idListener
    {
      while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
      int    slen   = data.getInt()-1;  // skip trailing null
      byte[] sbytes = new byte[slen];
      data.get(sbytes, 0, slen);
      data.get(); // skip trailing null
      try {;
        t.idListener   = new String(sbytes, "UTF-8");
      } catch(java.io.UnsupportedEncodingException e) {
        t.idListener   = new String(); }
      offset += 4 + slen + 1;
    }
    return 0;
  }

  public int gen_typecode( ByteBuffer b ) {
    byte[] tc_data = { 
(byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xb2, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'F', (byte)'i', (byte)'l', (byte)'t', (byte)'e', (byte)'r', (byte)'i', (byte)'n', (byte)'g', (byte)'T', (byte)'y', (byte)'p', (byte)'e', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x04, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x22, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0b, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'i', (byte)'d', (byte)'L', (byte)'i', (byte)'s', (byte)'t', (byte)'e', (byte)'n', (byte)'e', (byte)'r', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x22, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0b, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'n', (byte)'o', (byte)'m', (byte)'e', (byte)'T', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)'o', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x28, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0b, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'f', (byte)'i', (byte)'l', (byte)'t', (byte)'r', (byte)'o', (byte)'J', (byte)'s', (byte)'o', (byte)'n', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x24, (byte)0x00, (byte)0x0f, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'c', (byte)'o', (byte)'n', (byte)'s', (byte)'u', (byte)'l', (byte)'t', (byte)'a', (byte)'F', (byte)'i', (byte)'l', (byte)'t', (byte)'r', (byte)'o', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,     };
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
  public FilteringType         alloc ()              { return new FilteringType(); }
  public void       clear (FilteringType instance)   { instance.clear(); }
  public void       destroy (FilteringType instance) { /* noop */ }
  public void       copy (FilteringType to, FilteringType from) { to.copy(from); }
  public boolean    get_field( String fieldname, CoreDX_FieldDef fdef ) { 
    return false;
  }
  private long cTypeSupport = 0;
}; // FilteringType
