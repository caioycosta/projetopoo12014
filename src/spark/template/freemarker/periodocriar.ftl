<#include "header.ftl">
<h1>Cadastrar novo período de contabilização</h1>
</br>
<form method="post">
DataInicio 
<input type="text" name="diai" <#if (periodo)??>value="${periodo.dataInicio.innerDate?date?string("dd")}"</#if> />
<input type="text" name="mesi" <#if (periodo)??>value="${periodo.dataInicio.innerDate?date?string("MM")}"</#if> />
<input type="text" name="anoi" <#if (periodo)??>value="${periodo.dataInicio.innerDate?date?string("yyyy")}"</#if> /><br/>

DataFim
<input type="text" name="diaf" <#if (periodo)??>value="${periodo.dataFim.innerDate?date?string("dd")}"</#if> />
<input type="text" name="mesf" <#if (periodo)??>value="${periodo.dataFim.innerDate?date?string("MM")}"</#if> />
<input type="text" name="anof" <#if (periodo)??>value="${periodo.dataFim.innerDate?date?string("yyyy")}"</#if> /><br/>


 <br/>

<input type="submit" />
</form>
<#include "footer.ftl">