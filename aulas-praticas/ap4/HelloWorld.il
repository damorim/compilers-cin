.assembly HelloWorld {}
.assembly extern mscorlib {}

.method static void Main( ){
    .entrypoint
    .maxstack 2
    ldstr "Hello World!"
    call void [mscorlib]System.Console::WriteLine(string) 
    ret
}
