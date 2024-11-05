//Thomas Augusto Haskel

.assembly extern mscorlib {}
.assembly _codigo_objeto{}
.module _codigo_objeto.exe

.class public _UNICA{
	.method static public void _principal(){
		.entrypoint
		.locals (int64 i_mesLido)
		
		call string
			[mscorlib]System.Console::ReadLine()
		call int64 
        		[mscorlib]System.Int64::Parse(string)
		stloc i_mesLido
		ldloc i_mesLido
		ldc.i8 1
		clt
		brtrue 	REPETE_LEITURA
		ldloc i_mesLido
		ldc.i8 12
		cgt
		brtrue	REPETE_LEITURA
		br AVALIA_TRIMESTRE
	REPETE_LEITURA:
		ldstr "valor invalido, digitar um valor entre 1 e 12: "
		call void
			[mscorlib]System.Console::Write(string)
		call string
			[mscorlib]System.Console::ReadLine()
		call int64 
        		[mscorlib]System.Int64::Parse(string)
		stloc i_mesLido
		ldloc i_mesLido
		ldc.i8 1
		clt
		brtrue 	REPETE_LEITURA
		ldloc i_mesLido
		ldc.i8 12
		cgt
		brtrue 	REPETE_LEITURA
		br AVALIA_TRIMESTRE
	AVALIA_TRIMESTRE:
		ldloc i_mesLido
		ldc.i8 4
		clt
		brtrue PRIMEIRO_TRIMESTRE
		ldloc i_mesLido
		ldc.i8 7
		clt
		brtrue SEGUNDO_TRIMESTRE
		ldloc i_mesLido
		ldc.i8 10
		clt
		brtrue TERCEIRO_TRIMESTRE
		ldstr "quarto trimestre"
		br ESCREVE_TRIMESTRE
	PRIMEIRO_TRIMESTRE:
		ldstr "primeiro trimestre"
		br ESCREVE_TRIMESTRE
	SEGUNDO_TRIMESTRE:
		ldstr "segundo trimestre"
		br ESCREVE_TRIMESTRE
	TERCEIRO_TRIMESTRE:
		ldstr "terceiro trimestre"
	ESCREVE_TRIMESTRE:
		call void
			[mscorlib]System.Console::Write(string)
		ret
	}
}