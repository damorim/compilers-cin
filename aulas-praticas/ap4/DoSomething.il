.assembly DoSomething {}
.assembly extern mscorlib {}

.method static void Main( ) {
    .entrypoint
    .maxstack 2
    .locals init(int32 a)
    call int32 temp( )
    stloc.s a
    ldloc.s a
    call void [mscorlib]System.Console::WriteLine(int32) 
    call string[mscorlib]System.Console::ReadLine( ) 
    pop
    ret
}

.method public static int32 temp( ) {
    ldc.i4 10
    ret
}
