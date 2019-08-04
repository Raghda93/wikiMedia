<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%@page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<link rel="stylesheet" href="HotelStyles.css" type="text/css">
<title>Articles</title>


</head>
<body bgcolor="#edf7ee">

	<h2>Articles</h2>

	<dir>
		<table class=articles>
			<tr>
				<td><label for="SmallestSize"><b>Smallest Size</b></label></td>
				<td><label for="LargestSize"><b>Largest Size</b></label></td>
				<td><label for="MedianSize"><b>Median Size</b></label></td>
				<td><label for="TotalWordcount"><b>Total Word count</b></label></td>
			</tr>

			<tr>
				<td><text type="number" name="SmallestSize"> <%=request.getAttribute("SmallestSize")%></text>
				</td>
				<td><text type="number" name="LargestSize"> <%=request.getAttribute("LargestSize")%>
					</text></td>
				<td><text type="number" pname="MedianSize"> <%=request.getAttribute("MedianSize")%>
					</text></td>
				<td><text type="number" name="TotalWordcount"> <%=request.getAttribute("TotalWordcount")%>
					</text></td>
			</tr>
		</table>
	</dir>

	<form action="/wikiMedia2/main/search/article" method="get">
	
		<div class="postcode-input">
			<input type="text">
			<button type="submit">Search</button>
		</div>
		<div>
			<%
			/* String responseString = response.readEntity(String.class); */
						
						
			%>
						<!-- code to read response, loop on the result and set the data in table -->
		</div>
	</form>

	
<!-- 	<script>
		BASE_URL = "http://localhost:8099/wikiMedia2"

		$(".postcode-input button").click(
				function() {
					var postcode = $(this).parents(".postcode-input").children(
							"input").val();
					// First do some basic validation of the postcode like
					// correct format etc.
					if (!validatePostcode(postcode)) {
						alert("Invalid Postal Code, please try again");
						return false;
					}
					var finalUrl = BASE_URL += "?postcode=" + postcode;
					$.ajax({
						url : finalUrl,
						cache : false,
						success : function(html) {
							// Parse the recieved data here.
							console.log(html);
						}
					});
				});
	</script> -->
</body>
</html>