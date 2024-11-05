// Ana Júlia da Cunha

.assembly extern mscorlib {}
.assembly _codigo_objeto {}
.module _codigo_objeto.exe

.class public _UNICA {
    .method static public void _principal() {
        .entrypoint
        .locals(int64 i_anoNascimento, int64 i_anoAtual, int64 i_idade, string s_categoria)

        ldc.i8 2024
        stloc i_anoAtual

        ldstr "digite seu ano de nascimento: "
	call void [mscorlib]System.Console::Write(string)
        call string [mscorlib]System.Console::ReadLine()
        call int64 [mscorlib]System.Int64::Parse(string)
        stloc i_anoNascimento

	ldloc i_anoAtual
	conv.i8
	ldloc i_anoNascimento
	conv.i8
	sub
	stloc i_idade
	
	ldloc i_idade
	conv.i8
	ldc.i8 13
	clt
	brtrue L1

	ldloc i_idade
	ldc.i8 20
	clt
	brtrue L2

	ldstr "senior"
	stloc s_categoria
	br L3
L1:
	ldstr "pre-mirim a petiz"
	stloc s_categoria
	br L4
L2:
	ldstr "infantil a junior"
	stloc s_categoria
	br L4
L3:
L4:
	ldloc s_categoria
	call void [mscorlib]System.Console::WriteLine(string)
	ret
    }
}