package br.ufg.inf.mestrado.hermesbase.coreDX.notificacao;

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
      // src.id_entidade
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.id_entidade == null) size += 1;
      else {
        try {
          byte[] sbytes = src.id_entidade.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.nome_topico
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.nome_topico == null) size += 1;
      else {
        try {
          byte[] sbytes = src.nome_topico.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.complemento_topico
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.complemento_topico == null) size += 1;
      else {
        try {
          byte[] sbytes = src.complemento_topico.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.caminho_ontologia
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.caminho_ontologia == null) size += 1;
      else {
        try {
          byte[] sbytes = src.caminho_ontologia.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
      // src.contexto
      // src.contexto.length
      size = (size+3) & 0xfffffffc;// align 4
      size += 4;
      if (src.contexto != null) {
        for (int i5 = 0; i5 < src.contexto.length; i5++ ) {
          // src.contexto[i5]
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
      
      
      // src.id_entidade
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.id_entidade == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.id_entidade.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.nome_topico
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.nome_topico == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.nome_topico.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.complemento_topico
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.complemento_topico == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.complemento_topico.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.caminho_ontologia
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.caminho_ontologia == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.caminho_ontologia.getBytes("UTF-8");
          out_stream.putInt(sbytes.length+1);
          out_stream.put(sbytes);
          offset += sbytes.length;
        } catch(Exception e) {
          out_stream.putInt(1);
        }
      }
      out_stream.put((byte)0);
      offset += 4 + 1;
      // src.contexto
      // src.contexto.length
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      offset += 4;
      out_stream.putInt(src.contexto.length);
      for (int i5 = 0; i5 < src.contexto.length; i5++ ) {
          // src.contexto[i5]
          offset++;
          out_stream.put((byte)src.contexto[i5]);
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
      // src.id_entidade
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.id_entidade == null) size += 1;
      else {
        try {
          byte[] sbytes = src.id_entidade.getBytes("UTF-8");
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
      
      // src.id_entidade
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.id_entidade == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.id_entidade.getBytes("UTF-8");
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
      // src.id_entidade
      size = (size+3) & 0xfffffffc;// align 4
      size += 4; // length
      if (src.id_entidade == null) size += 1;
      else {
        try {
          byte[] sbytes = src.id_entidade.getBytes("UTF-8");
          size += sbytes.length + 1;
        } catch(Exception e) {
          size += 1;
        }
      }
    } else {
      int offset = 0;
      out_stream.clear();
      out_stream.order(ByteOrder.BIG_ENDIAN);
      
      // src.id_entidade
      while((offset & 0x03) != 0) { offset++; out_stream.put((byte)0x00); } // align 4
      if (src.id_entidade == null) {
       out_stream.putInt(1);
      }
      else {
        try {
          byte[] sbytes = src.id_entidade.getBytes("UTF-8");
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
      // t.id_entidade
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.id_entidade   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.id_entidade   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.nome_topico
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.nome_topico   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.nome_topico   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.complemento_topico
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.complemento_topico   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.complemento_topico   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.caminho_ontologia
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.caminho_ontologia   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.caminho_ontologia   = new String(); }
        offset += 4 + slen + 1;
      }
      // t.contexto
      // t.contexto.length
      while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); } // align 4
      offset += 4;
      int    slen5 = data.getInt();
      t.contexto = new byte[slen5];
      for (int i5 = 0; i5 < t.contexto.length; i5++ ) {
        // t.contexto[i5]
        offset++;
        t.contexto[i5] = data.get();
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
      // t.id_entidade
      {
        while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
        int    slen   = data.getInt()-1;  // skip trailing null
        byte[] sbytes = new byte[slen];
        data.get(sbytes, 0, slen);
        data.get(); // skip trailing null
        try {;
          t.id_entidade   = new String(sbytes, "UTF-8");
        } catch(java.io.UnsupportedEncodingException e) {
          t.id_entidade   = new String(); }
        offset += 4 + slen + 1;
      }
    }
    return 1; // 1==success
  }

  public int     unmarshall_key_hash( NotificacaoType t, ByteBuffer data, int s ) {
    int offset = 0;
    data.order(ByteOrder.BIG_ENDIAN);
    // t.id_entidade
    {
      while((offset & 0x03) != 0) { offset++; data.position(data.position()+1); }// align 4
      int    slen   = data.getInt()-1;  // skip trailing null
      byte[] sbytes = new byte[slen];
      data.get(sbytes, 0, slen);
      data.get(); // skip trailing null
      try {;
        t.id_entidade   = new String(sbytes, "UTF-8");
      } catch(java.io.UnsupportedEncodingException e) {
        t.id_entidade   = new String(); }
      offset += 4 + slen + 1;
    }
    return 0;
  }

  public int gen_typecode( ByteBuffer b ) {
    byte[] tc_data = { 
(byte)0x0a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x16, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x10, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'N', (byte)'o', (byte)'t', (byte)'i', (byte)'f', (byte)'i', (byte)'c', (byte)'a', (byte)'c', (byte)'a', (byte)'o', (byte)'T', (byte)'y', (byte)'p', (byte)'e', (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x26, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'i', (byte)'d', (byte)'_', (byte)'e', (byte)'n', (byte)'t', (byte)'i', (byte)'d', (byte)'a', (byte)'d', (byte)'e', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x26, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'n', (byte)'o', (byte)'m', (byte)'e', (byte)'_', (byte)'t', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)'o', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x2a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x13, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'c', (byte)'o', (byte)'m', (byte)'p', (byte)'l', (byte)'e', (byte)'m', (byte)'e', (byte)'n', (byte)'t', (byte)'o', (byte)'_', (byte)'t', (byte)'o', (byte)'p', (byte)'i', (byte)'c', (byte)'o', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x2a, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x12, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'c', (byte)'a', (byte)'m', (byte)'i', (byte)'n', (byte)'h', (byte)'o', (byte)'_', (byte)'o', (byte)'n', (byte)'t', (byte)'o', (byte)'l', (byte)'o', (byte)'g', (byte)'i', (byte)'a', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x28, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'c', (byte)'o', (byte)'n', (byte)'t', (byte)'e', (byte)'x', (byte)'t', (byte)'o', (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0e, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x0c, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0x09, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x28, (byte)0x00, (byte)0x12, (byte)0x00, (byte)0x00, (byte)0x00, (byte)'t', (byte)'i', (byte)'p', (byte)'o', (byte)'_', (byte)'s', (byte)'e', (byte)'r', (byte)'i', (byte)'a', (byte)'l', (byte)'i', (byte)'z', (byte)'a', (byte)'c', (byte)'a', (byte)'o', (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x0d, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x06, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0xff, (byte)0xff,     };
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
