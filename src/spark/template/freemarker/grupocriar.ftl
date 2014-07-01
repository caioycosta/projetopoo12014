<#include "header.ftl">
<h1>Cadastrar novo Grupo de Gasto</h1>
<#if (mensagem)??>${mensagem}<#else>Digite os dados.</#if> </br>
<form method="post">
Nome <input type="text" name="nome" <#if (grp)??>value="${grp.nome}"</#if>   /> <br/>
Tipo de grupo
<select name="tipo">
<option value="Despesa" <#if grp?? && (grp.tipo) == "Despesa">selected="selected"</#if>  >Despesa</option>
<option value="Receita" <#if grp?? && (grp.tipo) == "Receita">selected="selected"</#if> >Receita</option>
</select>
<br />

<#assign ativos = 0>
<#assign passivos = 0>
<#assign rendimentos = 0>
<#assign despesas = 0>
<#if grp??>
<#list (grp.contas) as cs>
<#if (cs.nome) == "Ativos"><#assign ativos = 1></#if>
<#if (cs.nome) == "Passivos"><#assign passivos = 1></#if>
<#if (cs.nome) == "Rendimentos"><#assign rendimentos = 1></#if>
<#if (cs.nome) == "Despesas"><#assign despesas = 1></#if>
</#list>
</#if>

<input type="checkbox" name="contaAtivos" value="1" <#if ativos == 1>checked</#if> /> Conta de ativos<br/>
<input type="checkbox" name="contaPassivos" value="1" <#if passivos == 1>checked</#if> /> Conta de passivos<br/>
<input type="checkbox" name="contaRendimentos" value="1" <#if rendimentos == 1>checked</#if> /> Conta de rendimentos<br/>
<input type="checkbox" name="contaDespesas" value="1" <#if despesas == 1>checked</#if> /> Conta de despesas<br/>
<input type="submit" />
</form>
<#include "footer.ftl">