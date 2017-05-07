
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title>SpringMVC Starter Application</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/screen.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/screen.css"/>"/>
    <link rel="stylesheet" type="text/css" href="./resources/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="./resources/bootstrap/css/bootstrap-theme.min.css" />
 	<script src="./resources/bootstrap/js/bootstrap.min.js"></script>
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    
</head>

<script>
function validarCampos() {
	if($('#name').val() == null || $('#name').val() == ""){
		alert("Necessário informar nome.");
		return false;
	}else if($("select[name='enumTipos'] option:selected").index()  == 0 ){
		alert("Necessário informar nome.");
		return false;
	}else if($("select[name='modelo.name'] option:selected").index()  == 0 ){
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

        <form:form commandName="novoAtributo" id="reg">
            <h2>Cadastrar Atributo(s)</h2>

            <table class="table table-bordered">
                <tbody>
              		<tr>
	                    <td><form:label path="name">Name:</form:label></td>
	                    <td><form:input  cssClass="form-control" path="name"/></td>
                	</tr>
                	
                	<tr>
               		<td><form:label path="enumTipos">Tipo:</form:label></td>
                	
                	  <td> 
                	  	<form:select path="enumTipos">
                	   		<form:option value="descricao"> --SELECT--</form:option>
    						<form:options items="${enuns}"></form:options>
                	  	</form:select>
                	  </td>
   					</tr>
              
                  	<tr>
               		<td><form:label path="modelo">Modelo:</form:label></td>
                	
                	  <td> 
                	  	<form:select path="modelo.name"  >
                	   		<form:option  value="name"> --SELECT--</form:option>
    						<form:options items="${nomeModelos}"></form:options>
                	  	</form:select>
                	  </td>
   					</tr> 
               
                
                
                
                <tr>
                    <td><p style="color: red">${error}</p></td>
                </tr>
                </tbody>
            </table>
            <table>
                <tr>
                    <td>
                        <input type="submit" value="Cadastrar" class="btn btn-success" onclick="return validarCampos();"/>
                        <input type="reset" value="Cancelar" class="btn btn-danger"/>
                    </td>
                </tr>
            </table>
        </form:form>
        
         <ul class="nav nav-pills">
 			 <li role="presentation" class="presentation"><a href="<c:url value="/"/>">Adicionar Modelo</a></li>
		</ul>
        
        
        
        
        <h2>Atributos</h2>
        <c:choose>
            <c:when test="${atributos.size()==0}">
                <em>Sem regristros.</em>
            </c:when>
            <c:otherwise>
                <table id="membersTable" class="simpletablestyle">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nome</th>
                            <th>Modelo vinculado</th>
                            <th>REST URL</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${atributos}" var="atributo">
                            <tr>
                                <td>${atributo.id}</td>
                                <td>${atributo.name}</td>
                                <td>${atributo.modelo.name}</td>
                                  <td><a href="<c:url value="/rest/modelo/${atributo.modelo.name}/${atributo.id} "/>">/rest/modelo/${atributo.id}</a></td>
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
