FILENAME = Test
ROOTRULE = stat

SHELL = /bin/bash
ANTLR = antlr-4.7.2-complete.jar
ANTLR4 = java -jar $(ANTLR)
GRUN = java org.antlr.v4.gui.TestRig
CLASSFILES = $(shell echo autogen/$(FILENAME){{Base,}Listener,Lexer,Parser}.class)
TOKENFILES = $(shell echo autogen/$(FILENAME){Lexer,}.tokens)
JAVAFILES = $(shell echo autogen/$(FILENAME){{Base,}Listener,Lexer,Parser}.java)

PYDIR = antlr4-python3-runtime-4.7.2
PYAUTOGEN = $(PYDIR)/src/autogen
PYFILES = $(shell echo $(PYAUTOGEN)/$(FILENAME){Listener,Lexer,Parser}.py)
.PHONY: classpath clean run sublime tree

# ----------------------------- python rules --------------------------
run: $(PYFILES)
	python3 $(PYDIR)/src/main.py

$(PYFILES): $(PYAUTOGEN)/$(FILENAME).g4 | classpath
	$(ANTLR4) -Dlanguage=Python3 $<

$(PYAUTOGEN)/$(FILENAME).g4: grammars/$(FILENAME).g4 | $(PYAUTOGEN)
	cp $< $@

$(PYAUTOGEN):
	mkdir $@

#------------------------------- java rules ---------------------------

tree: $(CLASSFILES) | classpath
	$(GRUN) $(FILENAME) $(ROOTRULE) -gui

$(CLASSFILES) $(JAVAFILES) $(TOKENFILES): autogen/$(FILENAME).g4 | classpath
	$(ANTLR4) autogen/$(FILENAME).g4
	javac $(JAVAFILES)

classpath:
	$(eval export CLASSPATH=".:$(PWD)/autogen:$(PWD)/$(ANTLR):$(CLASSPATH)")

autogen/$(FILENAME).g4: grammars/$(FILENAME).g4 | autogen
	cp $< $@

autogen:
	mkdir $@

#---------------------------- etc ------------------------------------

clean:
	-rm -rf autogen sublime_text_3 $(PYAUTOGEN)

sublime: | sublime_text_3
	./sublime_text_3/sublime_text

sublime_text_3:
	curl https://download.sublimetext.com/sublime_text_3_build_3200_x64.tar.bz2 | tar xvj

