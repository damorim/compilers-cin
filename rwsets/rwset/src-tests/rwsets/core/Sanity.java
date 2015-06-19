package rwsets.core;

import japa.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import rwsets.Helper;
import rwsets.RWTest;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;
import com.ibm.wala.util.io.CommandLine;
import com.ibm.wala.util.warnings.Warnings;

import depend.MethodDependencyAnalysis;
import depend.util.Util;
import depend.util.graph.SimpleGraph;

public class Sanity extends RWTest {
  
  @Before
  public void setup() {
    super.setup();
    JAR_FILENAME = EXAMPLES_JAR + SEP + "foo.jar";
  }
  
  @Test
  public void testBasicDoesNotCrash() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "foo/D.java";
    String line = "System.out.println(\"hello\");";   
    checkDeps("foo", strCompUnit, line, JAR_FILENAME, null);
  }
  
  @Test
  public void testIR_isNotEmpty() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "foo/D.java";
    // checks
    Assert.assertTrue((new File(strCompUnit)).exists());
    
    String line = "System.out.println(\"hello\");";

    // line number and class in WALA format 
    String[] lineAndClass = 
        depend.util.parser.Util.getLineAndWALAClassName(line+"", strCompUnit);
    int targetLine = Integer.parseInt(lineAndClass[0]);
    String targetClass = lineAndClass[1];    

    String dotPath = "/usr/bin/dot";
    
    String[] args = new String[] {
        "-appJar=" + JAR_FILENAME, 
        "-printWalaWarnings=" + false, 
        "-exclusionFile=" + EXCLUSION_FILE, 
        "-exclusionFileForCallGraph=" +EXCLUSION_FILE_FOR_CALLGRAPH, 
        "-dotPath=" + dotPath, 
        "-appPrefix=" + "foo",
        "-listAppClasses="+false, 
        "-listAllClasses="+false, 
        "-listAppMethods="+false, 
        "-genCallGraph="+false, 
        "-measureTime="+false, 
        "-reportType="+"dot"
    };
    // reading and saving command-line properties
    Properties p = CommandLine.parse(args);
    Util.setProperties(p);
    
    // clearing warnings from WALA
    Warnings.clear();
    
    MethodDependencyAnalysis mda = new MethodDependencyAnalysis(p);
    
    // find informed class
    String strClass = Util.getStringProperty("targetClass");
    IClass clazz = mda.getCHA().lookupClass(TypeReference.findOrCreate(ClassLoaderReference.Application, targetClass));
    if (clazz == null) {
      throw new RuntimeException("Could not find class \"" + strClass + "\"");
    }
    // find informed method
    IMethod imethod = depend.Main.findMethod(mda, clazz, targetLine);    

    // check
    String expected = "< Application, Lfoo/D$E, k(Ljava/lang/String;)V >";
    Assert.assertEquals(expected, imethod.toString());

  }
  
  @Test
  public void testPrimitiveTypeDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "foo/B.java";
    String line = "a.y + a.z > c.y + w";    
    String expectedResultFile = TEST_DIR + "/rwsets/core/Sanity.testPrimitiveTypeDependency.data";
    checkDeps("foo", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  @Test
  public void testArrayDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "foo/FooArray.java";
    String line = "t[1] = t[1] + \"!\";";    
    String expectedResultFile = TEST_DIR + "/rwsets/core/Sanity.testArrayDependency.data";
    checkDeps("foo", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
  @Test
  public void testReferenceDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "foo/FooReference.java";
    String line = "System.out.println(t);";    
    String expectedResultFile = TEST_DIR + "/rwsets/core/Sanity.testReferenceDependency.data";
    checkDeps("foo", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }
  
    
  @Ignore @Test
  public void testCollectionsDependency() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "foo/FooCollections.java";
    String line = "(t.size())";    
    String expectedResultFile = TEST_DIR + "/rwsets/core/Sanity.testCollectionsDependency.data";
    checkDeps("foo", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }

  @Test
  public void testFlowSensitivity() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException{
    
    String strCompUnit = EXAMPLES_SRC + SEP + "foo/FooFlowSensitivity.java";
    String dotPath = "/usr/bin/dot";
    
    Assert.assertTrue((new File(strCompUnit)).exists());
    
    String targetClass = "Lfoo/FooFlowSensitivity";
    String targetMethod = "read()V";
    
    String[] args = new String[] { 
        "-appJar=" + JAR_FILENAME,
        "-printWalaWarnings=" + false, 
        "-exclusionFile=" + EXCLUSION_FILE,
        "-exclusionFileForCallGraph=" + EXCLUSION_FILE_FOR_CALLGRAPH,
        "-dotPath=" + dotPath, 
        "-appPrefix=" + "foo",
        "-listAppClasses=" + false, 
        "-listAllClasses=" + false,
        "-listAppMethods=" + false, 
        "-genCallGraph=" + false,
        "-measureTime=" + false, 
        "-reportType=" + "dot",
        "-targetClass=" + targetClass, 
        "-targetMethod=" + targetMethod,
        "-targetLine=13"};
    // reading and saving command-line properties
    Properties p = CommandLine.parse(args);
    Util.setProperties(p);
    
    // clearing warnings from WALA
    Warnings.clear();
    
    MethodDependencyAnalysis mda = new MethodDependencyAnalysis(p);
    
    // find informed class    
    IClass clazz = depend.Main.findClass(mda);
    //  find informed method
    IMethod method = depend.Main.findMethod(clazz);
    SimpleGraph sg = depend.Main.run(mda, method);
    
    String expectedResultFile = TEST_DIR + SEP + "rwsets/core/Sanity.testFlowSensitivity.data";
    
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toDotString());
  }
}