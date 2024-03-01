package com.dong.acm;

import java.util.Scanner;

/**
* ACM 输入模板（多数之和）
* @author FreeMarker template error (DEBUG mode; use RETHROW in production!):
The following has evaluated to null or missing:
==> mainTemplate.author  [in template "MainTemplate.java.ftl" at line 7, column 13]

----
Tip: It's the step after the last dot that caused this error, not those before it.
----
Tip: If the failing expression is known to legally refer to something that's sometimes null or missing, either specify a default value like myOptionalVar!myDefault, or use <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
----

----
FTL stack trace ("~" means nesting-related):
	- Failed at: ${mainTemplate.author}  [in template "MainTemplate.java.ftl" at line 7, column 11]
----

Java stack trace (for programmers):
----
freemarker.core.InvalidReferenceException: [... Exception message was already printed; see it above ...]
	at freemarker.core.InvalidReferenceException.getInstance(InvalidReferenceException.java:134)
	at freemarker.core.EvalUtil.coerceModelToTextualCommon(EvalUtil.java:481)
	at freemarker.core.EvalUtil.coerceModelToStringOrMarkup(EvalUtil.java:401)
	at freemarker.core.EvalUtil.coerceModelToStringOrMarkup(EvalUtil.java:370)
	at freemarker.core.DollarVariable.calculateInterpolatedStringOrMarkup(DollarVariable.java:104)
	at freemarker.core.DollarVariable.accept(DollarVariable.java:63)
	at freemarker.core.Environment.visit(Environment.java:335)
	at freemarker.core.Environment.visit(Environment.java:341)
	at freemarker.core.Environment.process(Environment.java:314)
	at freemarker.template.Template.process(Template.java:383)
	at com.dong.generator.DynamicGenerator.doGenerate(DynamicGenerator.java:49)
	at com.dong.generator.MainGenerator.doGenerate(MainGenerator.java:35)
	at com.dong.cli.command.GenerateCommand.call(GenerateCommand.java:53)
	at com.dong.cli.command.GenerateCommand.call(GenerateCommand.java:14)
	at picocli.CommandLine.executeUserObject(CommandLine.java:2041)
	at picocli.CommandLine.access$1500(CommandLine.java:148)
	at picocli.CommandLine$RunLast.executeUserObjectOfLastSubcommandWithSameParent(CommandLine.java:2461)
	at picocli.CommandLine$RunLast.handle(CommandLine.java:2453)
	at picocli.CommandLine$RunLast.handle(CommandLine.java:2415)
	at picocli.CommandLine$AbstractParseResultHandler.execute(CommandLine.java:2273)
	at picocli.CommandLine$RunLast.execute(CommandLine.java:2417)
	at picocli.CommandLine.execute(CommandLine.java:2170)
	at com.dong.cli.CommandExecutor.doExecute(CommandExecutor.java:37)
	at com.dong.Main.main(Main.java:10)
