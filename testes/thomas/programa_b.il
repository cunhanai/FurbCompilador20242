.assembly extern mscorlib {}
.assembly _codigo_objeto{}
.module _codigo_objeto.exe

.class public UNICA{

.method static public void _principal() {
.entrypoint

.locals(int64 i_numTotalPrestacoes)
.locals(int64 i_numPrestacoesPagas)
.locals(float64 f_valorPrestacao)
.locals(float64 f_saldoDevedor)
ldstr "qual o numero total de prestacoes: "
call void [mscorlib]System.Console::WriteLine(string)
call string [mscorlib]System.Console::ReadLine()
call int64 [mscorlib]System.Int64::Parse(string)
stloc i_numTotalPrestacoes
ldstr "quantas prestacoes voce pagou: "
call void [mscorlib]System.Console::WriteLine(string)
call string [mscorlib]System.Console::ReadLine()
call int64 [mscorlib]System.Int64::Parse(string)
stloc i_numPrestacoesPagas
ldstr "qual o valor da prestacao: "
call void [mscorlib]System.Console::WriteLine(string)
call string [mscorlib]System.Console::ReadLine()
call float64 [mscorlib]System.Double::Parse(string)
stloc f_valorPrestacao
ldloc i_numTotalPrestacoes
conv.r8
ldloc i_numPrestacoesPagas
conv.r8
sub
ldloc f_valorPrestacao
mul
stloc f_saldoDevedor
ldstr "saldo devedor: "
ldloc f_saldoDevedor
add
call void [mscorlib]System.Console::WriteLine(float64)
ret
}
}
