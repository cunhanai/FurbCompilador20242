//Thomas Augusto Haskel

.assembly extern mscorlib {}
.assembly _codigo_objeto{}
.module _codigo_objeto.exe

.class public _UNICA{
	.method static public void _principal(){
		.entrypoint
		.locals (int64 i_anoAtual, int64 i_anoNascimento, int64 i_idade)
		
		ldc.i8 2024
		stloc i_anoAtual
		ldstr "digite seu ano de nascimento: "
		call void
			[mscorlib]System.Console::Write(string)
		call string
			[mscorlib]System.Console::ReadLine()
		call int64 
        		[mscorlib]System.Int64::Parse(string)
		stloc i_anoNascimento
		ldloc i_anoAtual
		ldloc i_anoNascimento
		sub
		stloc i_idade
		ldloc i_idade	
		ldc.i8 13
		clt
		brtrue PREMIRIM
		ldloc i_idade	
		ldc.i8 20
		clt
		brtrue INFANTIL
		ldstr "senior"
		br ESCREVER
	PREMIRIM:
		ldstr "pre-mirim a petiz"
		br ESCREVER
	INFANTIL:
		ldstr "infantil a junior"
	ESCREVER:
		call void
			[mscorlib]System.Console::Write(string)
		ret
	}
}