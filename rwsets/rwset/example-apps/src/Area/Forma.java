package Area;
import java.util.Scanner;
  
public abstract class Forma {  
    private double medida[];  
  
    public Forma(int numMedidas) {  
        medida = new double[numMedidas];  
    }  
  
    public double getMedida(int i) {  
        if (i < 0 || i >= medida.length) {  
            throw new RuntimeException("Numero inválido de medida!");  
        }  
        return medida[i];  
    }  
  
    protected void setMedida(int i, double m) {  
        if (i < 0 || i >= medida.length) {  
            throw new RuntimeException("Numero inválido de medida!");  
        }  
        if (m < 0) {  
            throw new RuntimeException("Medida #" + i + "inválida.");  
        }  
        medida[i] = m;  
    }  
  
    public String toString() {  
        StringBuffer sb = new StringBuffer(getClass().getName());  
        sb.append("medidas : ");  
        for (int i = 0; i < medida.length; i++) {  
            sb.append(medida[i]);  
            sb.append(",");  
        }  
        sb.append(medida[medida.length - 1]);  
        sb.append("}");  
        return sb.toString();  
    }  
  
    public int getNumMedidas() {  
        return medida.length;  
    }  
   
    public static void main(String[] args) {  
        System.out.println("Que área você quer calcular?\n"  
                + "1 para Circunferência\n" + "2 para Retângulo\n   ");  
        try {  
            Scanner abc = new Scanner(System.in);  
            int ler = abc.nextInt();  
            if (ler == 1) {  
                System.out.println("Digite a altura do retângulo:");  
                double altura = abc.nextDouble();  
                System.out.println("Digite a largura do retângulo:");  
                double largura = abc.nextDouble();  
                Retangulo ret = new Retangulo(altura, largura);  
                System.out.println("Área do retângulo: " + ret.area());  
                System.out  
                        .println("Perímetro do retângulo: " + ret.perimetro());  
            } else if (ler == 2) {  
                System.out.println("Digite o raio da circunferência:");  
                double raio = abc.nextDouble();  
                Circunferencia cir = new Circunferencia(raio);  
                System.out.println("Área da circunferência: " + cir.area());  
            }  
        } catch (Exception e) {  
            System.out.println(e);  
        }  
    }  
}  
