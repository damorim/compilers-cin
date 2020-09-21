FILENAME = Grammar
ROOTRULE = file

SHELL = /bin/bash
ANTLR = antlr-4.7.2-complete.jar
ANTLR4 = java -jar $(ANTLR)
GRUN = java org.antlr.v4.gui.TestRig
CLASSFILES = $(shell echo autogen/$(FILENAME){{Base,}Listener,Lexer,Parser}.class)
TOKENFILES = $(shell echo autogen/$(FILENAME){Lexer,}.tokens)
JAVAFILES = $(shell echo autogen/$(FILENAME){{Base,}Listener,Lexer,Parser}.java)
.PHONY: classpath clean tree

#------------------------------- java rules ---------------------------
# requires package: jdk-openjdk

tree: $(CLASSFILES) | classpath
	$(GRUN) $(FILENAME) $(ROOTRULE) -gui

$(CLASSFILES) $(JAVAFILES) $(TOKENFILES): autogen/$(FILENAME).g4 | classpath
	$(ANTLR4) autogen/$(FILENAME).g4
	javac $(JAVAFILES)

classpath:
	$(eval export CLASSPATH=".:$(PWD)/autogen:$(PWD)/$(ANTLR):$(CLASSPATH)")

autogen/$(FILENAME).g4: | $(FILENAME).g4 autogen
	ln -s ../$(FILENAME).g4 $@

autogen:
	mkdir $@

#---------------------------- clean ---------------------------------- 

clean:
	-rm -rf autogen

