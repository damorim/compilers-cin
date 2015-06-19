package hardware;

import java.util.ArrayList;
import java.util.List;

public class TipoJ {
	
	List<Estrutura> OPCODE = new ArrayList<Estrutura>();
	
	public TipoJ() {
		
		OPCODE.add(new Estrutura("j","000010"));
		OPCODE.add(new Estrutura("jal","000011"));
		
	}		
}