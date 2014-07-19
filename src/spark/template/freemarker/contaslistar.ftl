<#include "header.ftl">
<h1>Suas contas</h1>
<h2>${contaAtivos.nome}</h2>
Saldo: ${contaAtivos.saldo}<br/>
<#list (contaAtivos.lancamentos) as la>
${la.data?date?string("dd")}/${la.data?date?string("MM")}/${la.data?date?string("yyyy")} ${la.id} ${la.descricao} ${la.valor} 
<a href='/lancamento/editar/${la.id}'>A</a> <a href='/lancamento/excluir/${la.id}'>X</a> <br/>
</#list>

<h2>${contaPassivos.nome}</h2>
Saldo: ${contaPassivos.saldo}<br/>
<#list (contaPassivos.lancamentos) as lp>
${lp.data?date?string("dd")}/${lp.data?date?string("MM")}/${lp.data?date?string("yyyy")} ${lp.id} ${lp.descricao} ${lp.valor}
<a href='/lancamento/editar/${lp.id}'>A</a> <a href='/lancamento/excluir/${lp.id}'>X</a><br/>
</#list>

<h2>${contaRendimentos.nome}</h2>
Saldo: ${contaRendimentos.saldo}<br/>
<#list (contaRendimentos.lancamentos) as lr>
${lr.data?date?string("dd")}/${lr.data?date?string("MM")}/${lr.data?date?string("yyyy")} ${lr.id} ${lr.descricao} ${lr.valor}
<a href='/lancamento/editar/${lr.id}'>A</a> <a href='/lancamento/excluir/${lr.id}'>X</a><br/>
</#list>

<h2>${contaDespesas.nome}</h2>
Saldo: ${contaDespesas.saldo}<br/>
<#list (contaDespesas.lancamentos) as ld>
${ld.data?date?string("dd")}/${ld.data?date?string("MM")}/${ld.data?date?string("yyyy")} ${ld.id} ${ld.descricao} ${ld.valor}
<a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}'>X</a><br/>
</#list>
<#include "footer.ftl">