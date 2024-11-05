// Ana Júlia da Cunha

.assembly extern mscorlib {}
.assembly _codigo_objeto {}
.module _codigo_objeto.exe

.class public _UNICA {
    .method static public void _principal() {
        .entrypoint
        .locals(int64 i_mes, string s_trimestre)

        call string [mscorlib]System.Console::ReadLine()
        call int64 [mscorlib]System.Int64::Parse(string)
        stloc i_mes

        br L1

L1:
        ldloc i_mes
        conv.i8
        ldc.i8 1
        clt

        ldloc i_mes
        conv.i8
        ldc.i8 12
        cgt

        or
        brtrue L2

	br L3
L2:
	ldstr "valor invalido, digitar um valor entre 1 e 12: "
	call void [mscorlib]System.Console::Write(string)
	call string [mscorlib]System.Console::ReadLine()
	call int64 [mscorlib]System.Int64::Parse(string)
	stloc i_mes
	
	br L3
L3:
        ldloc i_mes
        conv.i8
        ldc.i8 1
        clt

        ldloc i_mes
        conv.i8
        ldc.i8 12
        cgt

        or
	brtrue L1
	
	ldloc i_mes
	conv.i8
	ldc.i8 4
	clt
	brtrue L4

	ldloc i_mes
	conv.i8
	ldc.i8 7
	clt
	brtrue L5

	ldloc i_mes
	conv.i8
	ldc.i8 10
	clt
	brtrue L6

	ldstr "quarto trimestre"
	stloc s_trimestre
	br L7

L4:
	ldstr "primeiro trimestre"
	stloc s_trimestre
	br L7
	
L5:
	ldstr "segundo trimestre"
	stloc s_trimestre
	br L7

L6:
	ldstr "terceiro trimestre"
	stloc s_trimestre
	br L7

L7:
	ldloc s_trimestre
	call void [mscorlib]System.Console::WriteLine(string)

        ret
    }
}