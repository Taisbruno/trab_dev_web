<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="models.Medico"%>
<%
    Medico medico = (Medico) session.getAttribute("medico");
    
    if (medico == null) {
        response.sendRedirect("LoginController?action=loginmedico");
    }
%>
