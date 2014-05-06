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
Nome <input type="text" name="nome" value=<#if (lancamento.nome)??>${lancamento.nome}</#if> /> <br/>

<input type="submit" />
</form>
</html>