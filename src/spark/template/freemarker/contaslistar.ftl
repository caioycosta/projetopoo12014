<#include "header.ftl">
<h1>Suas contas</h1>
<form method="post">
<select name="periodo">
	<#list (periodos) as p>
		<option value="${p.idPeriodo}" 
			<#if periodoSel?? && (periodoSel.idPeriodo) == (p.idPeriodo)>
				selected="selected"
			</#if>>
			${p.dataInicio.innerDate?date?string("dd/MM/yyyy")} a ${p.dataFim.innerDate?date?string("dd/MM/yyyy")}
		</option>
	</#list>
</select>
<input type="submit" value=">>" />
</form>
<br/>
<h2>${contaAtivos.nome}</h2>
Total do per�odo: ${(contaAtivos.total/100)?string.currency}<br/>
Total acumulado at� fim do per�odo: ${(contaAtivos.saldoAcumulado/100)?string.currency}<br/>
<br/>
<#list (contaAtivos.subcontas) as s>

<h3>${s.nome}</h3>
Total do per�odo: ${(s.total/100)?string.currency}<br/>
Total acumulado at� fim do per�odo: ${(s.saldoAcumulado/100)?string.currency}<br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descri��o</th><th>Valor</th><th></th></tr>
<#list (s.lancamentos) as ld>

<tr>
<td>${ld.id}</td>
<td>${ld.data.innerDate?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>

</#list>
</table>

</#list>
<br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descri��o</th><th>Valor</th><th></th></tr>
<#list (contaAtivos.lancamentos) as ld>
<tr>
<td>${ld.id}</td>
<td>${ld.data.innerDate?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>
</#list>
</table>

<h2>${contaPassivos.nome}</h2>
Total do per�odo: ${(contaPassivos.total/100)?string.currency}<br/>
Total acumulado at� fim do per�odo: ${(contaPassivos.saldoAcumulado/100)?string.currency}<br/>
<br/>
<#list (contaPassivos.subcontas) as s>

<h3>${s.nome}</h3>
Total do per�odo: ${(s.total/100)?string.currency}<br/>
Total acumulado at� fim do per�odo: ${(s.saldoAcumulado/100)?string.currency}<br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descri��o</th><th>Valor</th><th></th></tr>
<#list (s.lancamentos) as ld>

<tr>
<td>${ld.id}</td>
<td>${ld.data.innerDate?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>

</#list>
</table>

</#list>
<br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descri��o</th><th>Valor</th><th></th></tr>
<#list (contaPassivos.lancamentos) as ld>
<tr>
<td>${ld.id}</td>
<td>${ld.data.innerDate?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>
</#list>
</table>
<br/>
<hr/><hr/>
Saldo do per�odo: ${(cj.saldoMes/100)?string.currency} <br/>
Saldo acumulado, ao fim do per�odo: ${(cj.saldo/100)?string.currency}
<br/>
<h2>${contaRendimentos.nome}</h2>
Total do per�odo: ${(contaRendimentos.total/100)?string.currency}
<br/><br/>
<#list (contaRendimentos.subcontas) as s>

<h3>${s.nome}</h3>
Total do per�odo: ${(s.total/100)?string.currency}<br/>
Total acumulado at� fim do per�odo: ${(s.saldoAcumulado/100)?string.currency}<br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descri��o</th><th>Valor</th><th></th></tr>
<#list (s.lancamentos) as ld>

<tr>
<td>${ld.id}</td>
<td>${ld.data.innerDate?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>

</#list>
</table>

</#list>
<br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descri��o</th><th>Valor</th><th></th></tr>
<#list (contaRendimentos.lancamentos) as ld>
<tr>
<td>${ld.id}</td>
<td>${ld.data.innerDate?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>
</#list>
</table>

<h2>${contaDespesas.nome}</h2>
Total do per�odo: ${(contaDespesas.total / 100)?string.currency}
<br/><br/>
<#list (contaDespesas.subcontas) as s>

<h3>${s.nome}</h3>
Total do per�odo: ${(s.total/100)?string.currency}<br/>
Total acumulado at� fim do per�odo: ${(s.saldoAcumulado/100)?string.currency}<br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descri��o</th><th>Valor</th><th></th></tr>
<#list (s.lancamentos) as ld>

<tr>
<td>${ld.id}</td>
<td>${ld.data.innerDate?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>

</#list>
</table>

</#list>
<br/><#list (contaDespesas.subcontas) as s>

<h3>${s.nome}</h3>
Total do per�odo: ${(s.total/100)?string.currency}<br/>
Total acumulado at� fim do per�odo: ${(s.saldoAcumulado/100)?string.currency}<br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descri��o</th><th>Valor</th><th></th></tr>
<#list (s.lancamentos) as ld>

<tr>
<td>${ld.id}</td>
<td>${ld.data.innerDate?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>

</#list>
</table>

</#list>
<br/>
<table class="listing">
<tr><th>ID</th><th>Data</th><th>Descri��o</th><th>Valor</th><th></th></tr>
<#list (contaDespesas.lancamentos) as ld>
<tr>
<td>${ld.id}</td>
<td>${ld.data.innerDate?date?string("dd/MM/yyyy")}</td>
<td style="text-align:left;">${ld.descricao}</td>
<td style="font-family:monospace;text-align:right;">${(ld.valor / 100)?string.currency}</td>
<td><a href='/lancamento/editar/${ld.id}'>A</a> <a href='/lancamento/excluir/${ld.id}' class="confirmation">X</a></td>
</tr>
</#list>
</table>

<#include "footer.ftl">