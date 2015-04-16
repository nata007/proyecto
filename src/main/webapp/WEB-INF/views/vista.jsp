<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>


<script>
  $(document).ready(function() {
 
	  $('#regist').click(function(evento){
			var name = $('#nombre2').val();
				   $.post('insert2',
	    		       {nombre: name},
	    		       respuesta,
	    		       'json');
		   });
 
  });

  function respuesta(data) {
 	 alert(data);
 	}
  </script>

</head>
<body>
<label >Nombre de la app: </label>
<input id="nombre2"  />
<a href="#" id="regist">Registrar app</a>
		
</body>
</html>