<#include "header.ftl">

</br>
<form method="post">
	DataInicio 
	<input type="text" name="diai" <#if (subconta)??>value="${subconta.dataInicio.innerDate?date?string("dd")}"</#if> />
	<input type="text" name="mesi" <#if (subconta)??>value="${subconta.dataInicio.innerDate?date?string("MM")}"</#if> />
	<input type="text" name="anoi" <#if (subconta)??>value="${subconta.dataInicio.innerDate?date?string("yyyy")}"</#if> /><br/>
	
	DataFim
	<input type="text" name="diaf" <#if (subconta)??>value="${subconta.dataFim.innerDate?date?string("dd")}"</#if> />
	<input type="text" name="mesf" <#if (subconta)??>value="${subconta.dataFim.innerDate?date?string("MM")}"</#if> />
	<input type="text" name="anof" <#if (subconta)??>value="${subconta.dataFim.innerDate?date?string("yyyy")}"</#if> /><br/>
	<br/>
	Nome
	<input type="text" name="nome" <#if (subconta)??>value="${subconta.nome}"</#if> />
	<br/>
	<select name="tipoc">
		<option value="A">Ativos</option>
		<option value="P">Passivos</option>
		<option value="D">Despesas</option>
		<option value="R">Rendimentos</option>
	</select>
	<input type="submit" />
</form>

<#include "footer.ftl">