FILENAME = Exp

SHELL = /bin/bash
ANTLR = antlr-4.5.3-complete.jar
ANTLR4 = java -jar $(ANTLR)
GRUN = java org.antlr.v4.gui.TestRig
CLASSFILES = $(shell echo autogen/$(FILENAME){{Base,}Listener,Lexer,Parser}.class)
TOKENFILES = $(shell echo autogen/$(FILENAME){Lexer,}.tokens)
JAVAFILES = $(shell echo autogen/$(FILENAME){{Base,}Listener,Lexer,Parser}.java)
.PHONY: classpath clean run

run: $(CLASSFILES) | classpath
	$(GRUN) $(FILENAME) s -gui

$(CLASSFILES) $(JAVAFILES) $(TOKENFILES): autogen/$(FILENAME).g4 | classpath
	$(ANTLR4) autogen/$(FILENAME).g4
	javac $(JAVAFILES)

classpath:
	$(eval export CLASSPATH=".:$(PWD)/autogen:$(PWD)/$(ANTLR):$(CLASSPATH)")

autogen/$(FILENAME).g4: grammars/$(FILENAME).g4 | autogen
	cp $< $@

autogen:
	mkdir autogen

clean:
	-rm -rf autogen
