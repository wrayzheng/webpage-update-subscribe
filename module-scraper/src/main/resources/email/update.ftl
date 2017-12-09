<html>
    <head>
    </head>
    <body>
    	<h2 style="font-weight:700;">欢迎使用网页更新订阅服务，以下是今日更新：</h2>
        <#list urlMap?keys as key>
        <p><a href="${urlMap[key].url}">${urlMap[key].title}</a></p>
        <ul>
        <#list urlMap[key].updateList as update>
            <li><a href="${update.url}">${update.title}</a></li>
        </#list>
        </ul>
        <br />
        </#list>
    </body>
</html>