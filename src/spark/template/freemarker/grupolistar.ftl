<html>
<body>
</body>
<h1>Seus grupos de gasto</h1>
<#list (gruposGasto) as g>
${g.nome} ${g.id} <br/>
</#list>
</html>