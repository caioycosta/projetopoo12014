<#include "header.ftl">
<h1>Seus per�odos de contabiliza��o</h1>
<#list (lista) as g>
${g.idPeriodo}: de ${g.dataInicio.innerDate?date?string("dd/MM/yyyy")} a ${g.dataFim.innerDate?date?string("dd/MM/yyyy")} 
<a href='/periodo/editar/${g.idPeriodo}'>A</a> <a href='/periodo/excluir/${g.idPeriodo}' class="confirmation">X</a><br/>
</#list>
<#include "footer.ftl">