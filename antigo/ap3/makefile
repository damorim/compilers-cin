GRAMMAR = Cymbol
VISITOR = $(GRAMMAR)CheckerVisitor
ROOTRULE = fiile

SHELL = /bin/bash
JAR = antlr-4.7.2-complete.jar
GRUN = java org.antlr.v4.gui.TestRig
ANTLR4 = java -jar $(JAR)
CLASSFILES = $(shell echo autogen/$(GRAMMAR){{Base,}Listener,Lexer,Parser}.class)
TOKENFILES = $(shell echo autogen/$(GRAMMAR){Lexer,}.tokens)
JAVAFILES = $(shell echo autogen/$(GRAMMAR){{Base,}Listener,Lexer,Parser}.java)

PYDIR = antlr4-python3-runtime-4.7.2
PYAUTOGEN = $(PYDIR)/src/autogen
PYFILES = $(shell echo $(PYAUTOGEN)/$(GRAMMAR){Visitor,Lexer,Parser}.py)
.PHONY: classpath clean run sublime tree vscode

# ----------------------------- python rules --------------------------

run: $(PYAUTOGEN)/$(VISITOR).py $(PYFILES) autogen/$(GRAMMAR)Visitor.py
	python3 $(PYDIR)/src/main.py

$(PYAUTOGEN)/$(VISITOR).py: $(VISITOR).py | $(PYAUTOGEN)
	cp $< $@

$(PYFILES): $(PYAUTOGEN)/$(GRAMMAR).g4 | classpath
	$(ANTLR4) -Dlanguage=Python3 $< -visitor -no-listener

autogen/$(GRAMMAR)Visitor.py: $(PYAUTOGEN)/$(GRAMMAR)Visitor.py | autogen
	cp $< $@

$(PYAUTOGEN)/$(GRAMMAR).g4: grammars/$(GRAMMAR).g4 | $(PYAUTOGEN)
	cp $< $@

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

autogen/$(GRAMMAR).g4: grammars/$(GRAMMAR).g4 | autogen
	cp $< $@

autogen:
	mkdir $@

#---------------------------- text editors ------------------------------------

sublime: sublime_text_3
	./$</sublime_text

sublime_text_3:
	curl https://download.sublimetext.com/sublime_text_3_build_3207_x64.tar.bz2 | tar xvj

vscode: VSCode-linux-x64
	./$</bin/code

VSCode-linux-x64:
	curl https://az764295.vo.msecnd.net/stable/0dd516dd412d42323fc3464531b1c715d51c4c1a/code-stable-1554390792.tar.gz | tar xvz

#---------------------------- clean ----------------------------------

clean:
	-rm -rf autogen sublime_text_3 VSCode-linux-x64 $(PYAUTOGEN)
