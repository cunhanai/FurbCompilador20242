// Ana Júlia da Cunha

.assembly extern mscorlib {}
.assembly _codigo_objeto {}
.module _codigo_objeto.exe

.class public _UNICA {
    .method static public void _principal() {
        .entrypoint
        .locals(int64 i_numPrestacoesTotais, int64 i_qtdPrestacoesPagas, float64 f_valorPrestacao, float64 f_saldoDevedor)

        ldstr "qual o numero total de prestacoes: "
	call void [mscorlib]System.Console::Write(string)
        call string [mscorlib]System.Console::ReadLine()
        call int64 [mscorlib]System.Int64::Parse(string)
        stloc i_numPrestacoesTotais

        ldstr "quantas prestacoes voce pagou: "
	call void [mscorlib]System.Console::Write(string)
        call string [mscorlib]System.Console::ReadLine()
        call int64 [mscorlib]System.Int64::Parse(string)
        stloc i_qtdPrestacoesPagas

        ldstr "qual o valor da prestacao: "
	call void [mscorlib]System.Console::Write(string)
        call string [mscorlib]System.Console::ReadLine()
        call float64 [mscorlib]System.Double::Parse(string)
        stloc f_valorPrestacao

        ldloc i_numPrestacoesTotais
        conv.r8
        ldloc i_qtdPrestacoesPagas
        conv.r8
        sub

        ldloc f_valorPrestacao
        conv.r8
        mul

        ldloc f_saldoDevedor
        stloc f_saldoDevedor
        conv.r8

        call void [mscorlib]System.Console::WriteLine(float64)

        ret
    }
}