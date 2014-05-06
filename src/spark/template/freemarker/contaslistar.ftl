<html>
<body>
</body>
<h1>Suas contas</h1>
<h2>${contaAtivos.nome}</h2>
<#list (contaAtivos.lancamentos) as la>
${la.descricao} ${la.valor}<br/>
</#list>

<h2>${contaPassivos.nome}</h2>
<#list (contaPassivos.lancamentos) as lp>
${lp.descricao} ${lp.valor}<br/>
</#list>

<h2>${contaRendimentos.nome}</h2>
<#list (contaRendimentos.lancamentos) as lr>
${lr.descricao} ${lr.valor}<br/>
</#list>

<h2>${contaDespesas.nome}</h2>
<#list (contaDespesas.lancamentos) as ld>
${ld.descricao} ${ld.valor}<br/>
</#list>
</html>