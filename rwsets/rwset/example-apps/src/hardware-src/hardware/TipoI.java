package hardware;

import java.util.ArrayList;
import java.util.List;

public class TipoI {
	
	List<Estrutura> OPCODE = new ArrayList<Estrutura>();
	
	public TipoI() {
		
		OPCODE.add(new Estrutura("addi","001000"));
		OPCODE.add(new Estrutura("addiu","001001"));
		OPCODE.add(new Estrutura("beq","000100"));
		OPCODE.add(new Estrutura("bne","000101"));
		OPCODE.add(new Estrutura("ble","000110"));
		OPCODE.add(new Estrutura("bgt","000111"));
		OPCODE.add(new Estrutura("beqm","000001"));
		OPCODE.add(new Estrutura("lb","100000"));
		OPCODE.add(new Estrutura("lh","100001"));
		OPCODE.add(new Estrutura("lui","001111"));
		OPCODE.add(new Estrutura("lw","100011"));
		OPCODE.add(new Estrutura("sb","101000"));
		OPCODE.add(new Estrutura("sh","101001"));
		OPCODE.add(new Estrutura("slti","001010"));
		OPCODE.add(new Estrutura("sw","101011"));
		
	}
}
