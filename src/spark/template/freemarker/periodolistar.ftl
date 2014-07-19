<#include "header.ftl">
<h1>Seus períodos de contabilização</h1>
<#list (lista) as g>
${g.idPeriodo}: de ${g.dataInicio?date?string("dd/MM/yyyy")} a ${g.dataFim?date?string("dd/MM/yyyy")} 
<a href='/periodo/editar/${g.idPeriodo}'>A</a> <a href='/periodo/excluir/${g.idPeriodo}'>X</a><br/>
</#list>
<#include "footer.ftl">