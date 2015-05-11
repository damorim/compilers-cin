1. Download your "Compiler Kit" from the URL below for your OS.

 https://software.intel.com/en-us/articles/pintool-downloads

2. Decompress the pin distribution file 

----

Mateus, o arquivo .so nao esta sendo genrado apos execucao de build.sh.

Tentei comentar a linha de PIN_ROOT de dentro do Makefile e dar uma export externamente (foi o que eu entendi do tutorial).

Enfim, estou recebendo a mensagem abaixo.

make: *** No rule to make target `/Users/damorim/projects/compilers-cin/pin/out/memorytrace.so'.  Stop.

* estou fazendo isto do meu mac

---

Outro comentario: percebi que vc. ***nao*** usou o parametro -e, a partir de build.sh, como indicado para compilar a ferramenta pin.