package ec.com.def.core.util.mail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

public class ByteArrayDataSource implements DataSource {
    private String name;
    private String contentType;
    private byte[] bytes;

    public  ByteArrayDataSource(String name, byte[] bytes) {
        this.name = name;
        this.bytes = bytes;
    }

    public  ByteArrayDataSource(byte[] bytes, String contentType) {
        this("bytes", bytes);
        this.contentType=contentType;
    }

    public  String getName() {
        return name;
    }

    public  InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(bytes);
    }

    public  OutputStream getOutputStream() {
        throw new IllegalStateException("not support");
    }

    public  String getContentType() {
        return this.contentType;
    }
    
    
}
