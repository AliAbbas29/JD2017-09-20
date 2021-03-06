<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lease rooms in shopping centers</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/nb.css" rel="stylesheet">
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="row">
          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                  <span class="sr-only">Toggle navigation</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
            <a class="navbar-brand" href="">Lease rooms in shopping centers</a>
          </div>
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
              <li><a href="do?command=resetdb">ResetDB</a></li>
              <c:choose>
                  <c:when test="${user!=null}">
                      <li><a href="do?command=lease">Lease</a></li>
                      <c:choose>
                          <c:when test="${user.roleId == 1}">
                              <li><a href="do?command=adminpage">AdminPage</a></li>
                          </c:when>
                          <c:when test="${user.roleId == 2}">
                              <li><a href="do?command=managerpage">ManagerPage</a></li>
                          </c:when>
                      </c:choose>
                      <li class="navbar-text"><c:out value="${user.email}" /></li>

                      <li><a href="do?command=signout">SignOut</a></li>
                  </c:when>
                  <c:otherwise>
                      <li><a href="do?command=signin">SignIn</a></li>
                  </c:otherwise>
              </c:choose>
            </ul>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
      </div><!-- /.row -->
    </nav>