package application;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;



public class ANTLRInputStreamUtils {
	public static ANTLRInputStream getStandardInputStream( ) throws IOException {
		return (new ANTLRInputStream(System.in));
	}
	
	public static ANTLRInputStream getFileInputStream(String filePath) throws IOException {
		return (new ANTLRFileStream(filePath));
	}
}
