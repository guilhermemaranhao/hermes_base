package br.ufg.inf.mestrado.coreDX;

// CoreDX DDL Generated code.  Do not modify - modifications may be overwritten.
import com.toc.coredx.DDS.*;
import java.nio.*;
import java.nio.charset.*;


public final class NotificacaoTypeTypeSupport implements TypeSupport{
  
  public ReturnCode_t register_type(DomainParticipant dp, String type_name) {
    if (dp.check_version( "3", "6", "8" ) != 0) {
      System.out.println( "WARNING: NotificacaoType TypeSupport version does not match CoreDX Library version.");
      System.out.println( "This may cause software instability or crashes.");
    }
    return dp.register_type(this, type_name);
  }

  public String get_type_name()   { return "NotificacaoType"; }
  public long   getCTypeSupport() { return cTypeSupport; }

  public NotificacaoTypeTypeSupport() {
    NotificacaoType tmp = new NotificacaoType();
    cTypeSupport = DomainParticipant.createTypeSupport(this, 
                     getClass().getName(), tmp.getClass().getName());
  }

  // ------------------------------------------------------
  // implementation

  public DataReader   create_datareader(Subscriber sub, TopicDescription td, 
                                        SWIGTYPE_p__DataReader jni_dr) {
    return new NotificacaoTypeDataReader(sub, td, jni_dr);
  }
  public DataWriter   create_datawriter(Publisher  pub, Topic topic, SWIGTYPE_p__DataWriter jni_dw ) {
    return new NotificacaoTypeDataWriter(pub, topic, jni_dw);
  }

  // marshal variants
  public int     marshall ( ByteBuffer out_stream,  NotificacaoType src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.instancia_sujeito
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.instancia_sujeito == null) size += 1;
      else {
        try {
          byte[] sbytes = src.instancia_sujeito.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.instancia_predicado
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.instancia_predicado == null) size += 1;
      else {
        try {
          byte[] sbytes = src.instancia_predicado.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.instancia_objeto
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.instancia_objeto == null) size += 1;
      else {
        try {
          byte[] sbytes = src.instancia_objeto.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.tipo_sujeito
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.tipo_sujeito == null) size += 1;
      else {
        try {
          byte[] sbytes = src.tipo_sujeito.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.tipo_predicado
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.tipo_predicado == null) size += 1;
      else {
        try {
          byte[] sbytes = src.tipo_predicado.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.tipo_objeto
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.tipo_objeto == null) size += 1;
      else {
        try {
          byte[] sbytes = src.tipo_objeto.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.rdfSerializado
      // src.rdfSerializado.length
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      if (src.rdfSerializado != null) {
        for (int i7 = 0; i7 < src.rdfSerializado.length; i7++ ) {
          // src.rdfSerializado[i7]
          size += 1;
        }
      }
      // src.tipo_serializacao
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.tipo_serializacao == null) size += 1;
      else {
        try {
          byte[] sbytes = src.tipo_serializacao.getBytes("UTF-8");
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
      
      
      // src.instancia_sujeito
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.instancia_sujeito == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.instancia_sujeito.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.instancia_predicado
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.instancia_predicado == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.instancia_predicado.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.instancia_objeto
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.instancia_objeto == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.instancia_objeto.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.tipo_sujeito
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.tipo_sujeito == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.tipo_sujeito.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.tipo_predicado
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.tipo_predicado == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.tipo_predicado.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.tipo_objeto
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.tipo_objeto == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.tipo_objeto.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.rdfSerializado
      // src.rdfSerializado.length
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      offset += 4;
      out_stream.putInt(src.rdfSerializado.length);
      for (int i7 = 0; i7 < src.rdfSerializado.length; i7++ ) {
          // src.rdfSerializado[i7]
          offset++;
          out_stream.put((byte)src.rdfSerializado[i7]);
      }
      // src.tipo_serializacao
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.tipo_serializacao == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.tipo_serializacao.getBytes("UTF-8");
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
  public int     marshall_key ( ByteBuffer out_stream,  NotificacaoType src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.instancia_sujeito
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.instancia_sujeito == null) size += 1;
      else {
        try {
          byte[] sbytes = src.instancia_sujeito.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.instancia_predicado
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.instancia_predicado == null) size += 1;
      else {
        try {
          byte[] sbytes = src.instancia_predicado.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.instancia_objeto
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.instancia_objeto == null) size += 1;
      else {
        try {
          byte[] sbytes = src.instancia_objeto.getBytes("UTF-8");
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
      
      // src.instancia_sujeito
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.instancia_sujeito == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.instancia_sujeito.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.instancia_predicado
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.instancia_predicado == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.instancia_predicado.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.instancia_objeto
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.instancia_objeto == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.instancia_objeto.getBytes("UTF-8");
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

  public int     marshall_key_hash ( ByteBuffer out_stream,  NotificacaoType src ) {
    int size = 0;
    if ( out_stream == null ) {
      // src.instancia_sujeito
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.instancia_sujeito == null) size += 1;
      else {
        try {
          byte[] sbytes = src.instancia_sujeito.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.instancia_predicado
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.instancia_predicado == null) size += 1;
      else {
        try {
          byte[] sbytes = src.instancia_predicado.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.instancia_objeto
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.instancia_objeto == null) size += 1;
      else {
        try {
          byte[] sbytes = src.instancia_objeto.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
    } else {
      int offset = 0;
      out_stream.clear();
      out_stream.order(ByteOrder.BIG_ENDIAN);
      
      // src.instancia_sujeito
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.instancia_sujeito == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.instancia_sujeito.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.instancia_predicado
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.instancia_predicado == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.instancia_predicado.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.instancia_objeto
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.instancia_objeto == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.instancia_objeto.getBytes("UTF-8");
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
  public int     unmarshall ( NotificacaoType t, ByteBuffer data, int s )    {
    int offset = 0;
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0xFE)==0) { // CDR encoding
      // t.instancia_sujeito
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.instancia_sujeito   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.instancia_sujeito   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.instancia_predicado
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.instancia_predicado   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.instancia_predicado   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.instancia_objeto
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.instancia_objeto   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.instancia_objeto   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.tipo_sujeito
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.tipo_sujeito   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.tipo_sujeito   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.tipo_predicado
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.tipo_predicado   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.tipo_predicado   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.tipo_objeto
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.tipo_objeto   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.tipo_objeto   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.rdfSerializado
      // t.rdfSerializado.length
      while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); } // align 4
      offset += 4;
      int    slen7 = data.getInt();
      t.rdfSerializado = new byte[slen7];
      for (int i7 = 0; i7 < t.rdfSerializado.length; i7++ ) {
        // t.rdfSerializado[i7]
        offset++;
        t.rdfSerializado[i7] = data.get();
      }
      // t.tipo_serializacao
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.tipo_serializacao   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.tipo_serializacao   = new String(); }
        offset += 4 + slen + 1;
      }
    }
    return 1; // 1==success
  }

  public int     unmarshall_key( NotificacaoType t, ByteBuffer data, int s ) {
    int offset = 0;
    data.get();                      // skip the first byte 
    byte encoding = data.get();      // data encoding CDR / ENDIAN 
    data.getShort();                 // unused flags (2 bytes)
    if ((encoding & 0x01)==0)  data.order(ByteOrder.BIG_ENDIAN);
    else                       data.order(ByteOrder.LITTLE_ENDIAN);

    if ((encoding & 0xFE)==0) { // CDR encoding
      // t.instancia_sujeito
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.instancia_sujeito   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.instancia_sujeito   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.instancia_predicado
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.instancia_predicado   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.instancia_predicado   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.instancia_objeto
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.instancia_objeto   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.instancia_objeto   = new String(); }
        offset += 4 + slen + 1;
      }
    }
    return 1; // 1==success
  }

  public int     unmarshall_key_hash( NotificacaoType t, ByteBuffer data, int s ) {
    int offset = 0;
    data.order(ByteOrder.BIG_ENDIAN);
    // t.instancia_sujeito
    {
      while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
      int    slen   = data.getInt()-1;  // skip trailing null
      byte[] sbytes = new byte[slen];
      data.get(sbytes, 0, slen);
      data.get(); // skip trailing null
      try {;
        t.instancia_sujeito   = new String(sbytes, "UTF-8");
      } catch(java.io.UnsupportedEncodingException e) {
        t.instancia_sujeito   = new String(); }
      offset += 4 + slen + 1;
    }
    // t.instancia_predicado
    {
      while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
      int    slen   = data.getInt()-1;  // skip trailing null
      byte[] sbytes = new byte[slen];
      data.get(sbytes, 0, slen);
      data.get(); // skip trailing null
      try {;
        t.instancia_predicado   = new String(sbytes, "UTF-8");
      } catch(java.io.UnsupportedEncodingException e) {
        t.instancia_predicado   = new String(); }
      offset += 4 + slen + 1;
    }
    // t.instancia_objeto
    {
      while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
      int    slen   = data.getInt()-1;  // skip trailing null
      byte[] sbytes = new byte[slen];
      data.get(sbytes, 0, slen);
      data.get(); // skip trailing null
      try {;
        t.instancia_objeto   = new String(sbytes, "UTF-8");
      } catch(java.io.UnsupportedEncodingException e) {
        t.instancia_objeto   = new String(); }
      offset += 4 + slen + 1;
    }
    return 0;
  }

  public int gen_typecode( ByteBuffer b ) {
    byte[] tc_data = { 
(byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x72, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'N', (byte)'o', (byte)'t', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'c', (byte)'a', (byte)'o', (byte)'T', (byte)'y', (byte)'p', (byte)'e', (byte)0x00, (byte)0x08, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x2a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x12, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'i', (byte)'n', (byte)'s', (byte)'t', (byte)'a', (byte)'n', (byte)'c', (byte)'i', (byte)'a', (byte)'_', (byte)'s', (byte)'u', (byte)'j', (byte)'e', (byte)'i', (byte)'t', (byte)'o', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x2e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x14, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'i', (byte)'n', (byte)'s', (byte)'t', (byte)'a', (byte)'n', (byte)'c', (byte)'i', (byte)'a', (byte)'_', (byte)'p', (byte)'r', (byte)'e', (byte)'d', (byte)'i', (byte)'c', (byte)'a', (byte)'d', (byte)'o', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x2a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'i', (byte)'n', (byte)'s', (byte)'t', (byte)'a', (byte)'n', (byte)'c', (byte)'i', (byte)'a', (byte)'_', (byte)'o', (byte)'b', (byte)'j', (byte)'e', (byte)'t', (byte)'o', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x26, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'t', (byte)'i', (byte)'p', (byte)'o', (byte)'_', (byte)'s', (byte)'u', (byte)'j', (byte)'e', (byte)'i', (byte)'t', (byte)'o', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x26, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0f, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'t', (byte)'i', (byte)'p', (byte)'o', (byte)'_', (byte)'p', (byte)'r', (byte)'e', (byte)'d', (byte)'i', (byte)'c', (byte)'a', (byte)'d', (byte)'o', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x26, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'t', (byte)'i', (byte)'p', (byte)'o', (byte)'_', (byte)'o', (byte)'b', (byte)'j', (byte)'e', (byte)'t', (byte)'o', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x2c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0f, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'r', (byte)'d', (byte)'f', (byte)'S', (byte)'e', (byte)'r', (byte)'i', (byte)'a', (byte)'l', (byte)'i', (byte)'z', (byte)'a', (byte)'d', (byte)'o', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x28, (byte)0x00, (byte)0x12, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'t', (byte)'i', (byte)'p', (byte)'o', (byte)'_', (byte)'s', (byte)'e', (byte)'r', (byte)'i', (byte)'a', (byte)'l', (byte)'i', (byte)'z', (byte)'a', (byte)'c', (byte)'a', (byte)'o', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,     };
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
  public NotificacaoType         alloc ()              { return new NotificacaoType(); }
  public void       clear (NotificacaoType instance)   { instance.clear(); }
  public void       destroy (NotificacaoType instance) { /* noop */ }
  public void       copy (NotificacaoType to, NotificacaoType from) { to.copy(from); }
  public boolean    get_field( String fieldname, CoreDX_FieldDef fdef ) { 
    return false;
  }
  private long cTypeSupport = 0;
}; // NotificacaoType
