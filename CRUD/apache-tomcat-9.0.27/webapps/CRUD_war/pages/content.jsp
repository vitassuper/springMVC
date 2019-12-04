<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="page_components/imports.jsp"></c:import>
    <title>CRUD - ${title}</title>
</head>
<c:import url="page_components/header.jsp"></c:import>
    <div class="container">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="text-center">
                            <h1>${title}<small> crud операции</small></h1>
                        </div>
                    </div>
                    <div class="alert alert-info" role="alert">
                        <a class="btn btn-primary" role="button"
                           href="${pageContext.request.contextPath}/${instrument}/pdfReport?view=pdfView" target="_blank">Download
                            PDF report</a>
                        <a class="btn btn-primary" role="button"
                           href="${pageContext.request.contextPath}/${instrument}/xlsxReport.xlsx?view=excelView"
                           target="_blank">Download Excel report</a>
                    </div>
                    <div class="panel-body">
                        <div class="panel panel-info">
                            <!-- Default panel contents -->
                            <div class="panel-heading">
                                <div class="text-center"><h3>Авторы</h3></div>
                            </div>
                            <table class="table table-striped table-condensed" id="car-brands">
                                <thead>
                                <th>
                                    <button class="sort" data-sort="brand-name">Автор</button>
                                </th>
                                <th>
                                    <button class="sort" data-sort="founded-year">Год рождения</button>
                                </th>
                                <th>
                                    <button class="sort" data-sort="headquarter">Псевдоним</button>
                                </th>
                                <th>Действия</th>
                                </thead>
                                <tbody align="center" class="list">
                                <c:forEach var="author" items="${listBookAuthor}" varStatus="status">
                                    <tr>
                                        <td class="brand-name">${author.name}</td>
                                        <td class="founded-year">${author.birthYear}</td>
                                        <td class="headquarter">${author.alias}</td>
                                        <td class="action">
                                            <a href="${pageContext.request.contextPath}/${instrument}/edit-author/${author.id}">Изменить</a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="${pageContext.request.contextPath}/${instrument}/delete-author/${author.id}">Удалить</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="panel-footer"><a class="btn btn-info" role="button"
                                                         href="${pageContext.request.contextPath}/${instrument}/newAuthor">Добавить
                                нового автора &raquo</a></div>
                        </div>

                        <div class="panel panel-info">
                            <!-- Default panel contents -->
                            <div class="panel-heading">
                                <div class="text-center"><h3>Книги</h3></div>
                            </div>
                            <table class="table table-striped table-condensed" id="car-models">
                                <thead>
                                <th>
                                    <button class="sort" data-sort="brand-name">Автор</button>
                                </th>
                                <th>
                                    <button class="sort" data-sort="model-name">Книга</button>
                                </th>
                                <th>
                                    <button class="sort" data-sort="generation">Год выпуска</button>
                                </th>
                                <th>
                                    <button class="sort" data-sort="production-year">Описание</button>
                                </th>
                                <th>Действия</th>
                                </thead>
                                <tbody align="center" class="list">
                                <c:forEach var="book" items="${listBook}" varStatus="status">
                                    <tr>
                                        <td class="brand-name">${book.bookAuthor.name}</td>
                                        <td class="model-name">${book.bookName}</td>
                                        <td class="generation">${book.description}</td>
                                        <td class="production-year">${book.foundedYear}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/${instrument}/edit-book/${book.idBook}">Изменить</a>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="${pageContext.request.contextPath}/${instrument}/delete-book/${book.idBook}">Удалить</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="panel-footer"><a class="btn btn-info" role="button"
                                                         href="${pageContext.request.contextPath}/${instrument}/newBook">Добавить
                                новую книгу &raquo</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/resources/js/list.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/content-list.js"></script>
    </body>
</html>