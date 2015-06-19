package rwsets.coffeemaker;

import japa.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rwsets.Helper;
import rwsets.RWTest;

import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.shrikeCT.InvalidClassFileException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;
import com.ibm.wala.util.io.CommandLine;
import com.ibm.wala.util.warnings.Warnings;

import depend.MethodDependencyAnalysis;
import depend.util.Util;
import depend.util.graph.SimpleGraph;

public class TestCoffeeMaker extends RWTest {
  
  @Before
  public void setup() {
    JAR_FILENAME = EXAMPLES_JAR + SEP + "coffee.jar";
  }
  
  @Test
  public void test0() throws IOException, WalaException, CancelException, ParseException, InvalidClassFileException {
    String strCompUnit = EXAMPLES_SRC + SEP + "coffeemaker/CoffeeMaker.java";
    String line = "if(addRecipe(newRecipe)) {";
    String expectedResultFile = TEST_DIR + SEP + "rwsets/coffeemaker/TestCoffeeMaker.test0.data";
    checkDeps("coffee", strCompUnit, line, JAR_FILENAME, expectedResultFile);
  }

  @Test
  public void testAnalysisWithLineContents() throws Exception {
    String strCompUnit = EXAMPLES_SRC + SEP + "coffeemaker/CoffeeMaker.java";
    String exclusionFile = RESOURCES_DIR + SEP + "ExclusionAllJava.txt";
    String exclusionFileForCallGraph = RESOURCES_DIR + SEP + "ExclusionForCallGraph.txt";
    String coffeejar = EXAMPLES_JAR + SEP + "coffee.jar";
    String targetClass = "Lcoffeemaker/CoffeeMaker";
    String targetMethod = "editRecipe(Lcoffeemaker/Recipe;Lcoffeemaker/Recipe;)Z";

    // checks
    Assert.assertTrue((new File(strCompUnit)).exists());
    Assert.assertTrue((new File(exclusionFile)).exists());
    Assert.assertTrue((new File(exclusionFileForCallGraph)).exists());
    Assert.assertTrue((new File(coffeejar)).exists());


    String[] args = new String[] { 
        "-appJar=" + coffeejar,
        "-printWalaWarnings=" + false, 
        "-exclusionFile=" + exclusionFile,
        "-exclusionFileForCallGraph=" + exclusionFileForCallGraph,
        "-dotPath=" + "/usr/bin/dot", 
        "-appPrefix=" + "coffee",
        "-listAppClasses=" + false, 
        "-listAllClasses=" + false,
        "-listAppMethods=" + false, 
        "-genCallGraph=" + false,
        "-measureTime=" + false, 
        "-reportType=" + "list",
        "-targetClass=" + targetClass, 
        "-targetMethod=" + targetMethod,
        "-targetLine=99"};
    
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
    
    String expectedResultFile = TEST_DIR + SEP + "rwsets/coffeemaker/TestCoffeeMaker.testAnalysisWithLineContents.data";
    Assert.assertEquals(Helper.readFile(expectedResultFile), sg.toString());
  } 
}