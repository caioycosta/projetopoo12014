<html>
<body>
</body>
<h1>Cadastrar novo Grupo de Gasto</h1>
<#if (mensagem)??>${mensagem}<#else>Digite os dados.</#if> </br>
<form method="post">
Nome <input type="text" name="nome" /> <br/>
Tipo de grupo
<select name="tipo">
<option value="Despesa">Despesa</option>
<option value="Receita">Receita</option>
</select>
<br />
<input type="checkbox" name="contaAtivos" value="1" /> Conta de ativos<br/>
<input type="checkbox" name="contaPassivos" value="1" /> Conta de passivos<br/>
<input type="checkbox" name="contaRendimentos" value="1" /> Conta de rendimentos<br/>
<input type="checkbox" name="contaDespesas" value="1" /> Conta de despesas<br/>
<input type="submit" />
</form>
</html>