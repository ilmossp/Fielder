

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>User Management Application</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: black">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand"> myapp </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
			<form class="d-flex" action="/UserMan/find">
		      <input class="form-control me-2" type="search" name ="CIN" placeholder="Search by CIN" aria-label="Search">
		      <button class="btn btn-outline-success" type="submit">Search</button>
		    </form>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
			<form action="/UserMan/${ edit?'update':'insert' }">
  <div class="mb-3">
    <label for="exampleInputtext1" class="form-label">nom</label>
    <input type="text" name="nom" class="form-control" id="exampleInputtext1" aria-describedby="textHelp" value="${ !empty user.nom? user.nom:''}">
  </div> <div class="mb-3">
    <label for="exampleInputtext1" class="form-label">Prenom</label>
    <input type="text" name="prenom" class="form-control" id="exampleInputtext1" aria-describedby="textHelp" value="${ !empty user.prenom? user.prenom:''}">
  </div>
   <div class="mb-3">
    <label for="exampleInputtext1" class="form-label">CIN</label>
    <input type="text" name="CIN" class="form-control" id="exampleInputtext1" aria-describedby="textHelp" value="${ !empty user.CIN? user.CIN:''}">
  </div>
   <div class="mb-3">
    <label for="exampleInputtext1" class="form-label">job</label>
    <input type="text" name="job" class="form-control" id="exampleInputtext1" aria-describedby="textHelp" value="${ !empty user.job? user.job:''}">
  </div>
   <div class="mb-3">
    <label for="exampleInputtext1" class="form-label">Skills</label>
    <textarea class="form-control" name="skills" aria-label="With textarea" >${ !empty user.skills? user.skills:''}</textarea>
  </div>
  <!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  Save
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Confirmation</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Are you sure ?
      </div>
      <div class="modal-footer">
        <button type="reset" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
        <button type="submit" class="btn btn-primary">Confirm</button>
      </div>
    </div>
  </div>
</div>
</form>	
			</div>
		</div>
	</div>
</body>
</html>