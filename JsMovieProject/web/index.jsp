<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.MovieController" %>
<%@ page import="controller.MemberController" %>
<%@ page import="model.MovieDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
    <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
    <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css"/>
    <style>
        @font-face {
            font-family: 'MovieFont';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2') format('woff2');
            font-weight: 400;
            font-style: normal;
        }
    </style>
    <style>
        .swiper {
            width: 100%;
            height: 50%;
        }

        .swiper-slide {
            text-align: center;
            font-size: 18px;
            background: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        #indexImg {
            margin-left: 50px;
            margin-right: 50px;
        }

        .swiper-button-prev {
            color: black;
        }

        .swiper-button-next {
            color: black;
        }
    </style>
    <title>영화대여사이트</title>
    <%
        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        MovieController movieController = new MovieController(connectionMaker);

        ArrayList<MovieDTO> list = movieController.selectAll();
        pageContext.setAttribute("list", list);
    %>
</head>
<body style="font-family: MovieFont">
<%@include file="header.jsp" %>
<div class="container" style="width: 1200px; min-height: 600px; padding-top: 100px;margin-bottom: 200px">
    <div class="swiper">
        <div class="swiper-wrapper">
            <c:forEach var="t" items="${list}">
                <div class="swiper-slide" style="text-align : center;">
                    <a id="indexImg" href="/movie/printOne.jsp?id=${t.id}">
                        <img src="../${t.path}" style="max-width:100%; height: auto !important;">
                    </a>
                </div>
            </c:forEach>
        </div>
        <div class="swiper-button-next"></div>
        <div class="swiper-button-prev"></div>
    </div>
</div>

<script>
    var swiper = new Swiper('.swiper', {
        slidesPerView: 3,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });
</script>
</body>
<%--영화사진들 기입--%>
<%@include file="footer.jsp" %>
</body>

</html>