package biblioteca;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Teste {
	public static void main(String[] args) {
		/*
		 * Calendar cal = Calendar.getInstance(); System.out.println("Today: " +
		 * cal.getTime());
		 * 
		 * cal.add(Calendar.DATE, -30); System.out.println("30 days ago:-" +
		 * cal.getTime()); cal = Calendar.getInstance(); cal.add(Calendar.DATE,
		 * 1); System.out.println("7 days later:-" + cal.getTime()); cal =
		 * Calendar.getInstance();
		 * 
		 * cal.add(Calendar.MONTH, 2); System.out.println("2 MONTHS later:-" +
		 * cal.getTime());
		 */

		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, +8);
		String formatted = format1.format(cal.getTime());
		System.out.println(formatted);
		System.out.println(geraMulta(formatted));
	}

	private static double geraMulta(String dataDevolucao) {
		double valor = 0.0;
		Calendar dev = Calendar.getInstance();
		
		String[] aux = dataDevolucao.split("-");

		dev.set(Integer.parseInt(aux[0]), (Integer.parseInt(aux[1]) - 1),
				Integer.parseInt(aux[2]));
		int dif = calculaDiferencaDatas(dev);

		if (dif < 0) {
			valor = (dif * 0.75) * (-1);
		}
		return valor;
	}

	private static int calculaDiferencaDatas(Calendar dev) {
		Calendar atual = Calendar.getInstance();
		long ini = atual.getTimeInMillis();
		long fim = dev.getTimeInMillis();
		long dif = fim - ini;

		long difDays = dif / (24 * 60 * 60 * 1000);

		int days = (int) difDays;
		return days;
	}
}
