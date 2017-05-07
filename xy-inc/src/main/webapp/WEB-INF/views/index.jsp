
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title>ZUP</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/screen.css"/>"/>
    <link rel="stylesheet" type="text/css" href="./resources/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="./resources/bootstrap/css/bootstrap-theme.min.css" />
 	<script src="./resources/bootstrap/js/bootstrap.min.js"></script> 	
</head>

<script>
function validarCampoNome() {
	if($('#name').val() == null || $('#name').val() == ""){
		alert("Necessário informar nome.");
		return false;
	}
}

</script>

<body>
<div id="container">
    <div class="dualbrand">
        <img src="<c:url value="/static/resources/gfx/zup.jpg"/>"/>
    </div>
      
    <div id="content">
        <h1>Bem vindo ao Sistema da Zup!</h1>
        <div>
            <p>E um sistema basico feito com a Tecnologia Spring MVC</p>
        </div>

        <form:form commandName="novoModelo" id="reg">
            <h2>Cadastre seu Modelo</h2>

            <table class="table table-bordered" >
            
                <tbody>
                <tr>
                    <td><form:label path="name">Nome:</form:label></td>
                    <td><form:input cssClass="form-control" path="name"/></td>
                </tr>
                </tbody>
              
            </table>
            <table>
                <tr>
                    <td>
                        <input type="submit" value="Cadastrar" class="btn btn-success" onclick="return validarCampoNome();"/>
                        <input type="reset" value="Cancelar" class="btn btn-danger" />
                    </td>
                </tr>
            </table>
        </form:form>
        
       
        <ul class="nav nav-pills">
 			 <li role="presentation" class="presentation"><a href="<c:url value="/visualizar"/>">Adicionar Atributo</a></li>
		</ul>
        
        <h2>Modelos</h2>
        <c:choose>
            <c:when test="${modelos.size()==0}">
                <em>Sem regristros.</em>
            </c:when>
            <c:otherwise>
                <table id="membersTable" class="simpletablestyle">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nome</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${modelos}" var="modelo">
                            <tr>
                                <td>${modelo.id}</td>
                                <td>${modelo.name}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
      
    </div>

    <div id="footer">
        <p>
           Esse projeto e um MVP         
           <br/>
        </p>
    </div>
</div>
</body>
</html>
