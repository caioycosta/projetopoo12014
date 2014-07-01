<#include "header.ftl">
<h1>Login</h1>
<#if (mensagem)??>${mensagem}<#else>Digite seus dados.</#if> </br>
<form method="post">
Login <input type="text" name="login" /> <br/>
Senha <input type="text" name="senha" /> <br/>
<input type="submit" />
</form>
<#include "footer.ftl">