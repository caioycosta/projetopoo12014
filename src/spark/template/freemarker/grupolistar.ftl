<#include "header.ftl">
<h1>Seus grupos de gasto</h1>
<#list (gruposGasto) as g>
${g.nome} ${g.id} 
<a href='/grupogasto/editar/${g.id}'>A</a> <a href='/grupogasto/excluir/${g.id}' class="confirmation">X</a><br/>
</#list>
<#include "footer.ftl">