<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Unit Test</title>
</head>
<body>
    <h1 align="center" style=" font-size: 60px">测试报告</h1>
    <p>测试环境：</p>
    <p style="padding-left: 70px">${environment}</p>
    <p>测试工具：</p>
    <p style="padding-left: 70px">${tool}</p>
    <p>测试类：</p>
    <p style="padding-left: 70px">${zlass}</p>
    <p>测试日期：</p>
    <p style="padding-left: 70px">${date}</p>
    <p>统计：</p>
    <p style="padding-left: 70px">执行个数：${total}</p>
    <p style="padding-left: 70px">成功个数：${success}</p>
    <p style="padding-left: 70px">失败个数：${failure}</p>

    <table border="1" align="center">
        <th>
            <td>测试方法</td>
            <td>测试内容</td>
            <td>输入参数</td>
            <td>输出结果</td>
            <td>预期输出参数</td>
            <td>测试结论</td>
        </th>
        <tbody>
         ${data}
        </tbody>
    </table>
</body>
</html>