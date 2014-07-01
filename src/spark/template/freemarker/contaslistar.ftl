<#include "header.ftl">
<h1>Suas contas</h1>
<h2>${contaAtivos.nome}</h2>
Saldo: ${contaAtivos.saldo}<br/>
<#list (contaAtivos.lancamentos) as la>
${la.id} ${la.descricao} ${la.valor} 
<a href='/lancamento/editar/${la.id}'>A</a> <a href='/lancamento/excluir/${la.id}'>X</a> <br/>
</#list>

<h2>${contaPassivos.nome}</h2>
Saldo: ${contaPassivos.saldo}<br/>
<#list (contaPassivos.lancamentos) as lp>
${lp.id} ${lp.descricao} ${lp.valor}
<a href='/lancamento/editar/${lp.id}'>A</a> <a href='/lancamento/excluir/${lp.id}'>X</a><br/>
</#list>

<h2>${contaRendimentos.nome}</h2>
Saldo: ${contaRendimentos.saldo}<br/>
<#list (contaRendimentos.lancamentos) as lr>
${lr.id} ${lr.descricao} ${lr.valor}
<a href='/lancamento/editar/${lr.id}'>A</a> <a href='/lancamento/excluir/${lr.id}'>X</a><br/>
</#list>

<h2>${contaDespesas.nome}</h2>
Saldo: ${contaDespesas.saldo}<br/>
<#list (contaDespesas.lancamentos) as ld>
${ld.id} ${ld.descricao} ${ld.valor}
<a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}'>X</a><br/>
</#list>
<#include "footer.ftl">