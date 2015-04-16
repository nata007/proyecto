<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Home</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<script>
		  $(document).ready(function() {
		 
			  $('#registrar').click(function(evento){
					var name = $('#nombre').val();
					var password = $('#password').val();
					if(name != null || passowrd != null)
						{
						   $.post('insert2',
			    		       {nombre: name, password: password},
			    		       respuesta,
			    		       'json');
						}
				   });
			   
			  $('#token').click(function(evento){
					var name = $('#nombre').val();
					var password = $('#password').val();
					var code = $('#code').val();
					if(name != null || passowrd != null || code != null)
						{
						   $.post('update3',
			    		       {nombre: name, password: password, acces_token: code},
			    		       respuesta,
			    		       'json');
						}
				   });
		 
		  });
		
		  function respuesta(data) {

				$('#code').val(data.acces_code);
			  
		 	 alert("tu acces code es : "+data.acces_code);
		 	}
		  </script>
</head>

<body>
	<input id="code" type="hidden" value=""/>
		<form:form  modelAttribute="user">
		<form:label path="nombre"> Nombre: </form:label>
		<form:input id="nombre" path="nombre"/>
		</br></br></br>
		<form:label path="password"> Password: </form:label>
		<form:input id="password" path="password"/>
		</br></br></br>
		<input id="registrar" type="button" value="registrar app"/>
	</form:form>
		<input id="token" type="button" value="generar nuevo token"/>
</body>
</html>
