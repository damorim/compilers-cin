package Area;

public class Circunferencia extends Forma {  
    public Circunferencia(double raio) {  
        super(1);  
        setMedida(0, raio);  
    }  
  
    public double area() {  
        return Math.PI * Math.pow(getMedida(0), 2);  
    }  
}  