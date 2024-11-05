//Thomas Augusto Haskel

.assembly extern mscorlib {}
.assembly _codigo_objeto{}
.module _codigo_objeto.exe

.class public _UNICA{
	.method static public void _principal(){
		.entrypoint
		.locals (int64 i_numTotalPrestacoes, int64 i_numPrestacoesPagas,  float64 f_valorPrestacao, float64 f_saldoDevedor)
		
		ldstr "qual o numero total de prestacoes: "
		call void
			[mscorlib]System.Console::Write(string)
		call string
			[mscorlib]System.Console::ReadLine()
		call int64 
        		[mscorlib]System.Int64::Parse(string)
		stloc i_numTotalPrestacoes
		ldstr "quantas prestacoes voce pagou: "
		call void
			[mscorlib]System.Console::Write(string)
		call string
			[mscorlib]System.Console::ReadLine()
		call int64 
        		[mscorlib]System.Int64::Parse(string)
		stloc i_numPrestacoesPagas
		ldstr "qual o valor da prestacao: "
		call void
			[mscorlib]System.Console::Write(string)
		call string
			[mscorlib]System.Console::ReadLine()
		call float64 
        		[mscorlib]System.Double::Parse(string)
		stloc f_valorPrestacao
		ldloc i_numTotalPrestacoes
		ldloc i_numPrestacoesPagas
		sub
		conv.r8
		ldloc f_valorPrestacao
		mul
		stloc f_saldoDevedor
		ldloc f_saldoDevedor
		ldstr "saldo devedor: "
		call void
			[mscorlib]System.Console::Write(string)
		call void
			[mscorlib]System.Console::Write(float64)
		ret
	}
}