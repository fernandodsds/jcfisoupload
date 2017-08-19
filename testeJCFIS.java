import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import jcifs.smb.*;

public class testeJCFIS {

	public static void main(String[] args) throws IOException {
	    String user = "seuuser";
	    String pass ="seupassword";
	    String filename = "folder/file";
	    String sharedFolder="shared";
	    String path="smb://srv/folder";
	    int i=1;
	    char c;
	    
	    InputStream to = new FileInputStream(filename);
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

	    int nRead;
	    byte[] data = new byte[16384];

	    while ((nRead = to.read(data, 0, data.length)) != -1) {
	    i++;
	      buffer.write(data, 0, nRead);
	      
	    }

	    buffer.flush();

	    NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("",user, pass);
	    SmbFile smbFile = new SmbFile(path,auth);
	    SmbFileOutputStream smbfos = new SmbFileOutputStream(smbFile);
	   
	    smbfos.write(buffer.toByteArray()); 
	    System.out.println("upload concluido com sucesso");
	}

}
