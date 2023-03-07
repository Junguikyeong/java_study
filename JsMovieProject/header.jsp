<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.MemberDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberDTO logIn = (MemberDTO) session.getAttribute("logIn");
    pageContext.setAttribute("logIn", logIn);
%>
<header class="p-3 text-bg-dark">
    <div class="container">
        <nav class="d-flex flex-wrap align-items-center justify-content-start">
            <ul class="nav col-12 col-lg-auto justify-content-center me-lg-auto mb-2 mb-md-0">
                <li><a href="/index.jsp" class="nav-link fw-bold px-2 text-secondary">BIT'S MOVIE RENTAL</a></li>
                <li><a href="/movie/printList.jsp" class="nav-link px-2 text-white">영화</a></li>
                <li><a href="/theater/printList.jsp" class="nav-link px-2 text-white">극장</a></li>
            </ul>
            <form class="d-flex justify-content-center mb-3 mb-lg-0 mb-md-0" role="search"
                  style="max-width: 300px" action="/movie/search.jsp">
                <input type="search" class="form-control form-control-dark text-bg-white" placeholder="영화제목"
                       style="border-top-right-radius: 0; border-bottom-right-radius: 0;"
                       aria-label="Search" name="search">
                <button class="btn btn-light me-2" type="submit"
                        style="border-top-left-radius: 0; border-bottom-left-radius: 0;">
                    <img src="/images/search.png" style="width: 24px; height: 24px">
                </button>
            </form>

            <div class="text-end">
                <c:choose>
                    <c:when test="${logIn eq null}">
                        <button type="button" class="btn btn-info"
                                style="--bs-btn-bg: D4FAFF;  --bs-btn-border-color: D4FAFF"
                                onclick="location.href='/member/auth_register.jsp'">로그인/회원가입
                        </button>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${logIn.level == 1}">
                                <button type="button" class="btn btn-info"
                                        style="--bs-btn-bg: D4FAFF;  --bs-btn-border-color: D4FAFF"
                                        onclick="location.href='/member/levelManage.jsp?${logIn.id}'">회원등급 관리
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn btn-info"
                                        style="--bs-btn-bg: D4FAFF;  --bs-btn-border-color: D4FAFF"
                                        onclick="location.href='/member/update.jsp?id=${logIn.id}'">회원정보 수정/등업 신청
                                </button>
                            </c:otherwise>
                        </c:choose>
                        <button type="button" class="btn btn-info"
                                style="--bs-btn-bg: D4FAFF;  --bs-btn-border-color: D4FAFF"
                                onclick="location.href='/member/logout_logic.jsp?logout=1'">로그아웃
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </nav>
    </div>
</header>