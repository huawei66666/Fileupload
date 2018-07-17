<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="http://java.sun.com/jsp/jstl/functionss" %>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>

<c:set var="SYSTIME" value="${fns:getSysTime('yyyy-MM-dd HH:mm:ss')}"/>
<c:set var="SYSTIME_MINUTE" value="${fns:getSysTime('yyyy-MM-dd HH:mm')}"/>
<c:set var="SYSTIME_HOUR" value="${fns:getSysTime('yyyy-MM-dd HH')}"/>
<c:set var="SYSTIME_DAY" value="${fns:getSysTime('yyyy-MM-dd')}"/>