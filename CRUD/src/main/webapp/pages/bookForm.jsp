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
                            <h1>${action} книги <small>используя ${title}</small></h1>
                        </div>
                    </div>
                    <div class="panel-body">
                        <form:form method="POST" modelAttribute="book" class="form-horizontal">
                            <form:hidden path="idBook"/>
                            <div class="form-group">
                                <label for="idAuthor" class="col-sm-3 control-label">Автор:</label>
                                <div class="col-sm-9">
                                    <form:select path="idAuthor" multiple="false" class="form-control">
                                        <c:forEach var="author" items="${listBookAuthor}" varStatus="status">
                                            <c:choose>
                                                <c:when test="${author.id == book.idAuthor}">
                                                    <option selected value="${author.id}">${author.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${author.id}">${author.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="bookName" class="col-sm-3 control-label">Книга:</label>
                                <div class="col-sm-9">
                                    <form:input path="bookName" class="form-control" required="required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="foundedYear" class="col-sm-3 control-label">Год віпуска:</label>
                                <div class="col-sm-9">
                                    <form:input path="foundedYear" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="description" class="col-sm-3 control-label">Описание:</label>
                                <div class="col-sm-9">
                                    <form:input path="description" class="form-control" type="number"/>
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