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
Total do período: ${(contaAtivos.total/100)?string.currency}<br/>
Total acumulado até fim do período: ${(contaAtivos.saldoAcumulado/100)?string.currency}<br/>
<br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descrição</th><th>Valor</th><th></th></tr>
<#list (contaAtivos.lancamentos) as ld>
<tr>
<td>${ld.id}</td>
<td>${ld.data?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>
</#list>
</table>

<h2>${contaPassivos.nome}</h2>
Total do período: ${(contaPassivos.total/100)?string.currency}<br/>
Total acumulado até fim do período: ${(contaPassivos.saldoAcumulado/100)?string.currency}<br/>
<br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descrição</th><th>Valor</th><th></th></tr>
<#list (contaPassivos.lancamentos) as ld>
<tr>
<td>${ld.id}</td>
<td>${ld.data?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>
</#list>
</table>
<br/>
<hr/><hr/>
Saldo do período: ${(cj.saldoMes/100)?string.currency} <br/>
Saldo acumulado, ao fim do período: ${(cj.saldo/100)?string.currency}
<br/>
<h2>${contaRendimentos.nome}</h2>
Total do período: ${(contaRendimentos.total/100)?string.currency}
<br/><br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descrição</th><th>Valor</th><th></th></tr>
<#list (contaRendimentos.lancamentos) as ld>
<tr>
<td>${ld.id}</td>
<td>${ld.data?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>
</#list>
</table>

<h2>${contaDespesas.nome}</h2>
Total do período: ${(contaDespesas.total / 100)?string.currency}
<br/><br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descrição</th><th>Valor</th><th></th></tr>
<#list (contaDespesas.lancamentos) as ld>
<tr>
<td>${ld.id}</td>
<td>${ld.data?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>
</#list>
</table>

<#include "footer.ftl">