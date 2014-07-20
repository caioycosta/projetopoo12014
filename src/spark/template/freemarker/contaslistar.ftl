<#include "header.ftl">
<h1>Suas contas</h1>
<form method="post">
<select name="periodo">
	<#list (periodos) as p>
		<option value="${p.idPeriodo}" 
			<#if periodoSel?? && (periodoSel.idPeriodo) == (p.idPeriodo)>
				selected="selected"
			</#if>>
			${p.dataInicio?date?string("dd/MM/yyyy")} a ${p.dataFim?date?string("dd/MM/yyyy")}
		</option>
	</#list>
</select>
<input type="submit" value=">>" />
</form>
<br/>
<h2>${contaAtivos.nome}</h2>
Total do período: ${contaAtivos.total}<br/>
<#list (contaAtivos.lancamentos) as la>
${la.data?date?string("dd")}/${la.data?date?string("MM")}/${la.data?date?string("yyyy")} ${la.id} ${la.descricao} ${la.valor} 
<a href='/lancamento/editar/${la.id}'>A</a> <a href='/lancamento/excluir/${la.id}'>X</a> <br/>
</#list>

<h2>${contaPassivos.nome}</h2>
Total do período: ${contaPassivos.total}<br/>
<#list (contaPassivos.lancamentos) as lp>
${lp.data?date?string("dd")}/${lp.data?date?string("MM")}/${lp.data?date?string("yyyy")} ${lp.id} ${lp.descricao} ${lp.valor}
<a href='/lancamento/editar/${lp.id}'>A</a> <a href='/lancamento/excluir/${lp.id}'>X</a><br/>
</#list>
<br/>
<hr/>
Saldo do período: ${saldo}
<br/>
<h2>${contaRendimentos.nome}</h2>
Total do período: ${contaRendimentos.total}<br/>
<#list (contaRendimentos.lancamentos) as lr>
${lr.data?date?string("dd")}/${lr.data?date?string("MM")}/${lr.data?date?string("yyyy")} ${lr.id} ${lr.descricao} ${lr.valor}
<a href='/lancamento/editar/${lr.id}'>A</a> <a href='/lancamento/excluir/${lr.id}'>X</a><br/>
</#list>

<h2>${contaDespesas.nome}</h2>
Total do período: ${contaDespesas.total}<br/>
<#list (contaDespesas.lancamentos) as ld>
${ld.data?date?string("dd")}/${ld.data?date?string("MM")}/${ld.data?date?string("yyyy")} ${ld.id} ${ld.descricao} ${ld.valor}
<a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}'>X</a><br/>
</#list>
<#include "footer.ftl">