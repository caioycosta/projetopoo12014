<#include "header.ftl">


<#list (lista) as g>
${g.id}: ${g.nome} de ${g.dataInicio.innerDate?date?string("dd/MM/yyyy")} a ${g.dataFim.innerDate?date?string("dd/MM/yyyy")} 
<a href='/subconta/editar/${g.id}'>A</a> <a href='/subconta/excluir/${g.id}' class="confirmation">X</a><br/>
</#list>


<#include "footer.ftl">