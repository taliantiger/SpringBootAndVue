<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"></metacharset>
    <title>图书列表</title>
</head>

<table border="1">
    <tr>
        <td>图书编号</td>
        <td>图书作者</td>
        <td>图书作者</td>
    </tr>

    <#if books ??&&(books?size>0)>
    <#list books as book>
        <tr>
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.author}</td>
        </tr>
    </#list>
    </#if>
</table>
</html>