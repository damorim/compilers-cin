package depend;

import java.io.File;
import java.io.IOException;

import com.ibm.wala.examples.drivers.PDFTypeHierarchy;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.cha.ClassHierarchy;
import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.util.CancelException;
import com.ibm.wala.util.WalaException;
import com.ibm.wala.util.config.AnalysisScopeReader;
import com.ibm.wala.util.graph.Graph;
import com.ibm.wala.viz.DotUtil;

import depend.util.CallGraphGenerator;

/**
 * Class used to generate a call-graph and print it using Dot.
 * Check method <code>main</code> for details.
 * @author pbsf
 *
 */

public class GenCallGraph {


    /**
     * 
     * @param args[0] = input .jar file. 
     * args[1] = exclusionFile.txt path
     * args[2] = dotPath
     * args[3] = outputFile path. e.g /tmp/cg.pdf
     */
    public static void main(String[] args) {
        try {
            //Scope
            AnalysisScope scope = AnalysisScopeReader.makeJavaBinaryAnalysisScope(args[0], new File(args[1]));
            // Class Hierarchy Analysis (CHA)
            ClassHierarchy cha = ClassHierarchy.make(scope);

            CallGraphGenerator cgg = new CallGraphGenerator(scope, cha);
            Graph<CGNode> graph = cgg.getPrunedCallGraph();
            
            DotUtil.dotify(graph, null, PDFTypeHierarchy.DOT_FILE, args[3],
                    args[2]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassHierarchyException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (WalaException e) {
            e.printStackTrace();
        } catch (CancelException e) {
            e.printStackTrace();
        }
    }

}
