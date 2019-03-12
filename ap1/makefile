SHELL = /bin/bash
ANTLR = antlr-4.5.3-complete.jar
ANTLR4 = java -jar ./$(ANTLR)
GRUN = java org.antlr.v4.gui.TestRig
FILENAME = Exp
REQUIRE = $(shell echo grammars/$(FILENAME){{Base,}Listener,Lexer,Parser}.java)

run: $(REQUIRE)
	$(eval export CLASSPATH=".:$(PWD)/grammars:$(PWD)/$(ANTLR):$(CLASSPATH)")
	cd grammars; $(GRUN) $(FILENAME) s -gui

grammars/%.java: grammars/$(FILENAME).g4
	$(eval export CLASSPATH=".:$(PWD)/$(ANTLR):$(CLASSPATH)")
	$(ANTLR4) grammars/$(FILENAME).g4
	javac $(REQUIRE)

install:
	git clone https://github.com/damorim/compilers-cin.git
	#echo export CLASSPATH=\".:./compilers-cin/ap1/$(ANTLR):$$CLASSPATH\" >> $(HOME)/.bashrc
	cp ./makefile ./compilers-cin/ap1/
	#source ~/.bashrc

	
#mkdir $(HOME)/lib
#curl -O https://www.antlr.org/download/$(ANTLR)
#mv $(ANTLR) $(HOME)/lib
