<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="page_components/imports.jsp"></c:import>
    <title>${title}</title>
</head>
<body>
    <c:import url="page_components/header.jsp"></c:import>
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="text-center">
                            <h1>${action} автора <small>используя ${title}</small></h1>
                        </div>
                    </div>
                    <div class="panel-body">
                        <form:form method="POST" modelAttribute="author" class="form-horizontal">
                            <form:hidden path="id"/>
                            <div class="form-group">
                                <label for="name" class="col-sm-3 control-label">Автор:</label>
                                <div class="col-sm-9">
                                    <form:input path="name" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="birthYear" class="col-sm-3 control-label">Год рождения:</label>
                                <div class="col-sm-9">
                                    <form:input path="birthYear" class="form-control" type="number"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="alias" class="col-sm-3 control-label">Псевдоним:</label>
                                <div class="col-sm-9">
                                    <form:input path="alias" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-9">
                                    <button type="submit" class="btn btn-primary">Сохранить</button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>