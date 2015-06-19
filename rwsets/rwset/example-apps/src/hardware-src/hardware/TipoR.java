package hardware;

import java.util.ArrayList;
import java.util.List;

public class TipoR{
	
	List<Estrutura> OPCODE = new ArrayList<Estrutura>();
	List<Estrutura> FUNCT = new ArrayList<Estrutura>();
	
	
	public TipoR() {
		
		OPCODE.add(new Estrutura("add","000000"));
		OPCODE.add(new Estrutura("and","000000"));
		OPCODE.add(new Estrutura("div","000000"));
		OPCODE.add(new Estrutura("jr","000000"));
		OPCODE.add(new Estrutura("mfhi","000000"));
		OPCODE.add(new Estrutura("mflo","000000"));
		OPCODE.add(new Estrutura("sll","000000"));
		OPCODE.add(new Estrutura("sllv","000000"));
		OPCODE.add(new Estrutura("slt","000000"));
		OPCODE.add(new Estrutura("sra","000000"));
		OPCODE.add(new Estrutura("srav","000000"));
		OPCODE.add(new Estrutura("srl","000000"));
		OPCODE.add(new Estrutura("sub","000000"));
		OPCODE.add(new Estrutura("break","000000"));
		OPCODE.add(new Estrutura("rte","000000"));
		OPCODE.add(new Estrutura("mult","000000"));
		
		//OPCODE.add(new Estrutura("push","000000"));
		//OPCODE.add(new Estrutura("pop","000000"));
		
		//FUNCAO
		FUNCT.add(new Estrutura("add","100000"));
		FUNCT.add(new Estrutura("and","100100"));
		FUNCT.add(new Estrutura("div","011010"));
		FUNCT.add(new Estrutura("jr","001000"));
		FUNCT.add(new Estrutura("mfhi","010000"));
		FUNCT.add(new Estrutura("mflo","010010"));
		FUNCT.add(new Estrutura("sll","000000"));
		FUNCT.add(new Estrutura("sllv","000100"));
		FUNCT.add(new Estrutura("slt","101010"));
		FUNCT.add(new Estrutura("sra","000011"));
		FUNCT.add(new Estrutura("srav","000111"));
		FUNCT.add(new Estrutura("srl","000010"));
		FUNCT.add(new Estrutura("sub","100010"));
		FUNCT.add(new Estrutura("break","001101"));
		FUNCT.add(new Estrutura("rte","010011"));
		FUNCT.add(new Estrutura("mult", "011000"));
		//FUNCT.add(new Estrutura("push", "000101"));
		//FUNCT.add(new Estrutura("pop", "000110"));
	}
}
