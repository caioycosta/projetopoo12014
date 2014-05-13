<html>
<body>
</body>
<h1>Cadastrar novo Lançamento</h1>
<#if (mensagem)??>${mensagem}<#else>Digite os dados.</#if> </br>
<form method="post">
Grupo de gasto <select name="grupo">
<#list (gruposGasto) as g>
<option value="${g.id}">${g.nome}</option>
</#list>
</select> <br/>
Descricao <input type="text" name="descricao" value=<#if (lancamento.descricao)??>${lancamento.descricao}</#if> /> <br/>

<input type="submit" />
</form>
</html>