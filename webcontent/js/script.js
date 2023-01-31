$(document).ready(function(){
	$(".editProduct").click(function(){
			id=$(this).attr("id");
			console.log(id);
		})
		
		
		$('#editProductForm').on('submit',function(event){
			event.preventDefault();
			let form=new FormData(this);
			
			console.log("same id "+id);
			$.ajax({
				url:"editProductServlet?id="+id,
				type:"post",
				data:form,
				success:function(data){
					console.log(data);
					if(data.trim()==='done')
					{
					swal({
						  title: "Update Successfully Done!!!",
						  text: "Thank You.",
						  icon: "success",
						  button: "Done...!",
						}).then((value)=>{
							window.location="viewAndEdit.jsp";
						})
					}
				else
						{
						swal({
							  title: "Something Went Wrong...!!!",
							  text: "Try Again.",
							  icon: "warning",
							  buttons: true,
							  dangerMode: true,
							})
						}
				$('#editProductForm').trigger("reset");
				},
				error:function(error){
					console.log(error);
					swal({
						  title: "Something Went Wrong...!!!",
						  text: "Try Again.",
						  icon: "warning",
						  buttons: true,
						  dangerMode: true,
						})
				},
				processData:false,
				contentType:false
			})
		})
});





