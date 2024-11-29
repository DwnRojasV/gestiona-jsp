<%
    if(session.getAttribute("Usuario") == null){
        request.setAttribute("mesagge", "You must log in to access the application");
        pageContext.forward("index.jsp");
    }
%>