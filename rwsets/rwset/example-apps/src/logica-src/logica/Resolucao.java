package logica;
  import java.util.Vector;
  

  public class Resolucao {
    public static void main(String[] args) {
      Arquivo arq = new Arquivo ("Expressoes.in", "Expressoes.out");
      int cases = arq.readInt();
      int cont=1;
      String exp;
      Vector<String> clausulas = new Vector<String>();
      while (cases>0){
        exp = arq.readString();
        arq.print("caso #" + cont + ": ");
        if (Functions.verifyFNC(exp)){
          if (Functions.verifyHorn(exp)){
            if (Functions.SAT(exp, clausulas)){
              arq.print("satisfativel");
            } else {
              arq.print("insatisfativel");
            }
          } else {
            arq.print("nem todas as clausulas sao de horn");
          }
        } else {
          arq.print("nao esta na FNC");
        }
        arq.println();
        clausulas.clear();
        cont++;
        cases--;
      }
    }
  }