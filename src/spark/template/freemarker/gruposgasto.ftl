<#include "header.ftl">

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

<#list (relatorios) as r>
<table class="listing" style="width:300px;">
<tr><th>Grupo</th><th>Valor</th></tr>
<#list (r.gastoPorGrupo) as g>
  <tr><td style="text-align:left;">${g.grupo.nome} </td> <td style="font-family:monospace;text-align:right;"> ${(g.valor/100)?string.currency} </td></tr> <br/>
</#list>
</table>

</#list>    


<#include "footer.ftl">