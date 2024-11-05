//Thomas Augusto Haskel

.assembly extern mscorlib {}
.assembly _codigo_objeto{}
.module _codigo_objeto.exe

.class public _UNICA{
	.method static public void _principal(){
		.entrypoint
		.locals (int64 i_qtValores, int64 i_contador, int64 i_numeroLido, int64 i_contadorSomatorio, int64 i_somatorio)
		
		ldstr "quantos numeros: "
		call void
			[mscorlib]System.Console::Write(string)
		call string
			[mscorlib]System.Console::ReadLine()
		call int64 
        		[mscorlib]System.Int64::Parse(string)
		stloc i_qtValores
		ldc.i8 0
		stloc i_contador
	RECOMECA_SOMATORIO:
		ldstr "digite um numero: "
		call void
			[mscorlib]System.Console::Write(string)
		call string
			[mscorlib]System.Console::ReadLine()
		call int64 
        		[mscorlib]System.Int64::Parse(string)
		stloc i_numeroLido
		ldc.i8 0
		stloc i_contadorSomatorio
		ldc.i8 0
		stloc i_somatorio
	REALIZA_SOMA:
		ldloc i_somatorio
		ldloc i_contadorSomatorio
		add
		stloc i_somatorio
		ldloc i_contadorSomatorio
		ldc.i8 1
		add
		stloc i_contadorSomatorio
		ldloc i_contadorSomatorio
		ldloc i_numeroLido
		cgt
		brfalse REALIZA_SOMA
	ESCREVE_SOMA:
		ldstr "somatorio: "
		call void
			[mscorlib]System.Console::Write(string)
		ldloc i_somatorio
		call void
			[mscorlib]System.Console::WriteLine(int64)
		ldloc i_contador
		ldc.i8 1
		add
		stloc i_contador
		ldloc i_qtValores
		ldloc i_contador
		cgt
		brtrue RECOMECA_SOMATORIO
		ret
	}
}