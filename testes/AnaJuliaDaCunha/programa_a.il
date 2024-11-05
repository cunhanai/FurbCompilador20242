// Ana Júlia da Cunha

.assembly extern mscorlib {}
.assembly _codigo_objeto {}
.module _codigo_objeto.exe

.class public _UNICA {
    .method static public void _principal() {
        .entrypoint
        .locals(int64 i_baseMaior, int64 i_baseMenor, int64 i_altura, float64 f_area)

        call string [mscorlib]System.Console::ReadLine()
        call int64 [mscorlib]System.Int64::Parse(string)
        stloc i_baseMaior

        call string [mscorlib]System.Console::ReadLine()
        call int64 [mscorlib]System.Int64::Parse(string)
        stloc i_baseMenor

        call string [mscorlib]System.Console::ReadLine()
        call int64 [mscorlib]System.Int64::Parse(string)
        stloc i_altura

        ldloc i_baseMaior
        conv.r8
        ldloc i_baseMenor
        conv.r8
        add

        ldloc i_altura
        conv.r8
        mul

        ldc.r8 2
        div
        
        stloc f_area
        ldloc f_area
        conv.r8

        call void [mscorlib]System.Console::WriteLine(float64)

        ret
    }
}