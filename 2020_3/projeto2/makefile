GRAMMAR = Grammar
VISITOR = $(GRAMMAR)CheckerVisitor
ROOTRULE = fiile

SHELL = /bin/bash
JAR = antlr-4.7.2-complete.jar
ANTLR4 = java -jar $(JAR)
GRUN = java org.antlr.v4.gui.TestRig
CLASSFILES = $(shell echo autogen/$(GRAMMAR){{Base,}Listener,Lexer,Parser}.class)
TOKENFILES = $(shell echo autogen/$(GRAMMAR){Lexer,}.tokens)
JAVAFILES = $(shell echo autogen/$(GRAMMAR){{Base,}Listener,Lexer,Parser}.java)

PYDIR = antlr4-python3-runtime-4.7.2
PYAUTOGEN = $(PYDIR)/src/autogen
PYFILES = $(shell echo $(PYAUTOGEN)/$(GRAMMAR){Visitor,Lexer,Parser}.py)
.PHONY: classpath clean run tree adapt

# ----------------------------- python rules --------------------------

run: $(PYAUTOGEN)/$(VISITOR).py $(PYFILES) autogen/$(GRAMMAR)Visitor.py
	python3 $(PYDIR)/src/main.py

$(PYAUTOGEN)/$(VISITOR).py: $(VISITOR).py | $(PYAUTOGEN)
	ln -fs ../../../$< $@

$(PYFILES): $(PYAUTOGEN)/$(GRAMMAR).g4 | classpath
	$(ANTLR4) -Dlanguage=Python3 $< -visitor -no-listener

autogen/$(GRAMMAR)Visitor.py: $(PYAUTOGEN)/$(GRAMMAR)Visitor.py | autogen
	ln -fs ../$< $@

$(PYAUTOGEN)/$(GRAMMAR).g4: $(GRAMMAR).g4 | $(PYAUTOGEN)
	ln -fs ../../../$< $@

$(PYAUTOGEN):
	mkdir $@

#------------------------------- java rules ---------------------------

tree: $(CLASSFILES) | classpath
	$(GRUN) $(GRAMMAR) $(ROOTRULE) -gui

$(CLASSFILES) $(JAVAFILES) $(TOKENFILES): autogen/$(GRAMMAR).g4 | classpath
	$(ANTLR4) autogen/$(GRAMMAR).g4
	javac $(JAVAFILES)

classpath:
	$(eval export CLASSPATH=".:$(PWD)/autogen:$(PWD)/$(JAR):$(CLASSPATH)")

autogen/$(GRAMMAR).g4: $(GRAMMAR).g4 | autogen
	ln -s ../$< $@

autogen:
	mkdir $@

#------------------------ adapt to python ----------------------------

adapt: $(GRAMMAR).g4
	sed -Ei -e 's:\<file\>:fiile:g' -e 's:\<type\>:tyype:g' $(GRAMMAR).g4

#---------------------------- clean ---------------------------------- 

clean:
	find . -name __pycache__ | xargs rm -rf autogen $(PYAUTOGEN)
