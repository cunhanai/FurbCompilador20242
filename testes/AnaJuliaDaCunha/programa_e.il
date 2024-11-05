// Ana Júlia da Cunha

.assembly extern mscorlib {}
.assembly _codigo_objeto {}
.module _codigo_objeto.exe

.class public _UNICA {
    .method static public void _principal() {
        .entrypoint
	.locals(int64 i_qtdNumeros, int64 i_qtdLidos, int64 i_valorLido, int64 i_somatorio, int64 i_valorBase)

	ldstr "quantos numeros: "
	call void [mscorlib]System.Console::Write(string)
	call string [mscorlib]System.Console::ReadLine()
	call int64 [mscorlib]System.Int64::Parse(string)
	stloc i_qtdNumeros

	ldc.i8 0
	stloc i_qtdLidos

	br L1

L1:
	ldstr "digite um numero: "
	call void [mscorlib]System.Console::Write(string)
	call string [mscorlib]System.Console::ReadLine()
	call int64 [mscorlib]System.Int64::Parse(string)
	stloc i_valorLido

	ldloc i_qtdLidos
	conv.i8
	ldc.i8 1
	add
	stloc i_qtdLidos

	ldc.i8 0
	stloc i_somatorio
	ldc.i8 1
	stloc i_valorBase

	br L3

L3:
	ldloc i_somatorio
	conv.i8
	ldloc i_valorBase
	conv.i8
	add
	stloc i_somatorio

	ldloc i_valorBase
	conv.i8
	ldc.i8 1
	add
	stloc i_valorBase

	br L4

L4:
	ldloc i_valorBase
	conv.i8
	ldloc i_valorLido
	conv.i8
	cgt
	brfalse L3

	br L2

L2:
	ldstr "somatorio: "
	call void [mscorlib]System.Console::Write(string)
	ldloc i_somatorio
	call void [mscorlib]System.Console::WriteLine(int64)

	ldloc i_qtdLidos
	conv.i8
	ldloc i_qtdNumeros
	conv.i8
	clt
	brtrue L1

        ret
    }
}