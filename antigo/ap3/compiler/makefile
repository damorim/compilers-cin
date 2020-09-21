ifeq ($(shell uname -o),Msys)
	EXE = compiler.exe
	DBG = compiler_db.exe
	OUT = tmp.exe
else
	EXE = compiler
	DBG = compiler_db
	OUT = tmp.out
endif

SOURCE = $(addprefix src/, lexer.c parser.c ast.c visitor.c main.c)
HEADER = $(addprefix include/, lexer.h parser.h ast.h visitor.h)
CFLAGS = -flto -Wall -Wno-parentheses -Wno-missing-braces -Wno-unused-variable -Wno-unused-but-set-variable -Iinclude
.PHONY: clean

release: $(EXE)
debug: $(DBG)
asm: compiler.s
all: tags $(EXE) $(DBG) compiler.s

$(EXE): $(SOURCE) $(HEADER)
	gcc $(SOURCE) -O2 $(CFLAGS) -o $@

$(DBG): $(SOURCE) $(HEADER)
	gcc $(SOURCE) -O0 -ggdb $(CFLAGS) -o $@

compiler.s: $(SOURCE) $(HEADER)
	gcc $(SOURCE) -Og -g $(CFLAGS) -o $(OUT)
	objdump -S -M intel $(OUT) > $@
	rm $(OUT)

tags: $(SOURCE) $(HEADER)
	ctags -R

clean:
	-rm -f $(EXE) $(DBG) compiler.s
