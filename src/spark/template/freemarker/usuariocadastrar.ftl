<#include "header.ftl">
<h1>Cadastrar novo usuário</h1>
<#if (mensagem)??>${mensagem}<#else>Digite seus dados.</#if> </br>
<form method="post">
Nome <input type="text" name="nome" /> <br/>
Login <input type="text" name="login" /> <br/>
Senha <input type="text" name="senha" /> <br/>
<input type="submit" />
</form>
<#include "footer.ftl">