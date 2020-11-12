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
.PHONY: classpath clean run tree adapt spaces tabs llvm0 llvm1

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

#------------------------------ emit llvm -----------------------------

llvm: $(shell echo executables/O{0,1}/0{0,2,3})

.PRECIOUS: $(shell echo outputs/O{0,1}/0{0,2,3}.ll)

outputs/O0/%.ll: inputs/%.c | outputs/O0
	clang $< -O0 -S -emit-llvm -o $@

outputs/O1/%.ll: inputs/%.c | outputs/O1
	clang $< -O1 -S -emit-llvm -o $@

executables/O0/%: outputs/O0/%.ll | executables/O0
	clang $< -o $@

executables/O1/%: outputs/O1/%.ll | executables/O1
	clang $< -o $@

outputs/O0:
	mkdir $@
outputs/O1:
	mkdir $@
executables/O0:
	mkdir -p $@
executables/O1:
	mkdir -p $@

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
	sed -Ei -e 's:\<file\>:fiile:g' -e 's:\<type\>:tyype:g' $<

#------------------------ change spaces to tabs ----------------------

tabs: $(VISITOR).py
	sed -Ei 's:    :\t:g' $<

#------------------------ change tabs to spaces ----------------------

spaces: $(VISITOR).py
	sed -Ei 's:\t:    :g' $<

#---------------------------- clean ---------------------------------- 

clean:
	find . -name __pycache__ | xargs rm -rf autogen $(PYAUTOGEN) outputs/O* executables
