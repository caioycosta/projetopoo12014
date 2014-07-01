<#include "header.ftl">
<h1>Cadastrar novo Lançamento</h1>
<#if (mensagem)??>${mensagem}<#else>Digite os dados.</#if> </br>
<form method="post">
Grupo de gasto <select name="grupo">
<option>Selecione</option>
<#list (gruposGasto) as g>
<option value="${g.id}" <#if (g.id == idGrupo)> selected="selected"</#if>>${g.nome}</option>
</#list>
</select> <br/>

<#if (contas)??>
<select name="conta">
<option>Selecione</option>
<#list (contas) as g>
<option value="${g.nome}" <#if lancamento?? && (g.id) == (lancamento.id)>selected="selected"</#if> >${g.nome}</option>
</#list>
</select>


Descricao <input type="text" name="descricao" value="<#if (lancamento.descricao)??>${lancamento.descricao}</#if>" />
Valor <input type="text" name="valor" value="<#if (lancamento.valor)??>${lancamento.valor}</#if>" />

<#else>Selecione um grupo de gasto e pressione enviar.</#if> </br>


 <br/>

<input type="submit" />
</form>
<#include "footer.ftl">