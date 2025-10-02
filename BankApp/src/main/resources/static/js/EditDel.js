
		function script(){
			var vm = this;

			this.registerEvents = function(){
			document.addEventListener('click', function(e){
						targetElement = e.target;
						classList = targetElement.classList;

						if(classList.contains('deleteTableware')){
							e.preventDefault();

							TId = targetElement.dataset.tid;
							PCname = targetElement.dataset.pcname;


							BootstrapDialog.confirm({
								type: BootstrapDialog.TYPE_DANGER,
								title: 'Delete 	Equipment',
								message: 'Are you sure to delete <strong>' + PCname + '<strong>?',
								callback: function(isDelete){
									if(isDelete){

								$.ajax({
									method: 'POST',
									data: {
										id: TId,
										table: 'tablewares'
									},
									url:'DATABASE/delete.php',
									dataType: 'json',
									success: function(data){
										message = data.success ?
											PCname + ' successfully deleted!' : ' Error processing your request!';


											BootstrapDialog.alert({
												type: data.success ? BootstrapDialog.TYPE_SUCCESS : BootstrapDialog.TYPE_DANGER,
												message:message,
												callback: function(){
													if(data.success)location.reload();
												}
											});
									}
								});

									}
								
								}
							});
						
						
						}


						if(classList.contains('updateTableware')){

							e.preventDefault();

							TId = targetElement.dataset.tid;

							
							vm.showEditDialog(TId);

						
						}

					});

					// document.addEventListener('submit', function(e){
					// 	targetElement = e.target;

					// 	alert(targetElement.id);
					// 	e.preventDefault();
					// });

					$('#editTablewareForm').on('submit', function(e){
						e.preventDefault();

					});
					document.addEventListener('submit', function(e){
						e.preventDefault();
						targetElement = e.target;

						if(targetElement.id === 'editTablewareForm'){
							vm.saveUpdateDate(targetElement);
						}
					});
			}
			this.saveUpdateDate = function(form){

					var formData = new FormData(form);
    var quantity = parseInt(formData.get("quantity"));
    var g_condition = parseInt(formData.get("g_condition"));
    var damage = parseInt(formData.get("damage"));
    var missing = parseInt(formData.get("missing"));

    // Calculate the sum of g_condition, damage, and missing
    var total = g_condition + damage + missing;

    // Check if the total exceeds the quantity
    if (total > quantity) {
        BootstrapDialog.alert({
            type: BootstrapDialog.TYPE_DANGER,
            message: "You have exceeded the quantity.",
             callback: function () {
           vm.showEditDialog(TId);
        },
             });

     	return;
    }
    else if(total !== quantity) {
    BootstrapDialog.alert({
        type: BootstrapDialog.TYPE_DANGER,
        message: "The sum of 'In Good Condition,' 'Damage,' and 'Missing' must be equal to the quantity.",
             callback: function () {
           vm.showEditDialog(TId);
        },
    });
    return;
}

    // Update the values
    g_condition = quantity - damage - missing;

    // Set updated values in the form data
    formData.set("g_condition", g_condition.toString());
    formData.set("damage", damage.toString());
    formData.set("missing", missing.toString());

								$.ajax({
									method: 'POST',
									data: new FormData(form) ,
									url:'DATABASE/update-tablewaredata.php',
									processData: false,
									contentType: false,
									dataType: 'json',  
									success: function(data){
										BootstrapDialog.alert({
											type: data.success ? BootstrapDialog.TYPE_SUCCESS : BootstrapDialog.TYPE_DANGER,
											message: data.message, 
											callback: function(){
												if(data.success) location.reload();
											}
										});
									}
								});

			},

			this.showEditDialog = function(id){


				$.get('DATABASE/get-tableware.php', {id: id}, function(tablewareDetails){
					console.log(tablewareDetails);
					BootstrapDialog.confirm({
							title: 'Update <strong>' + tablewareDetails.PC_tableware + '</strong>',
							message: '<form action="DATABASE/add.php" method="POST" enctype="multipart/form-data" id="editTablewareForm">\
							<div class="appFormInputContainer0">\
										<label for="PC_tableware">Equipment Name</label>\
										<input type="text" class="appFormInput" name="PC_tableware" id="product_name" value="' + tablewareDetails.PC_tableware + '">\
									</div>\
									<div class="appFormInputContainer1">\
 										<label for="tableware_type">type plate or cup</label>\
										<select name="tableware_type" id="producttypeSelect">\
											<option value="' + tablewareDetails.tableware_type + '">SELECT TYPE</option>\
											<option value="PLASTIC">PLASTIC</option>\
											<option value="GLASS">GLASS</option>\
											<option value="RUBBER">RUBBER</option>\
										</select>\
									</div>\
									<div class="appFormInputContainer4">\
									<label id="labelquantity" for="quantity">Quantity</label>\
										<input class="appFormInputquantity" type="number" name="quantity" value="' + tablewareDetails.quantity + '">\
									</div>\
									<div class="appFormInputContainer4">\
									<label id="labelquantity" for="g_condition">IN GOOD CONTION</label>\
										<input class="appFormInputquantity" type="number" name="g_condition" value="' + tablewareDetails.g_condition + '">\
									</div>\
									<div class="appFormInputContainer4">\
									<label id="labelquantity" for="damage">DAMAGE</label>\
										<input class="appFormInputquantity" type="number" name="damage" value="' + tablewareDetails.damage + '">\
									</div>\
									<div class="appFormInputContainer4">\
									<label id="labelquantity" for="missing">MISSING</label>\
										<input class="appFormInputquantity" type="number" name="missing" value="' + tablewareDetails.missing + '">\
									</div>\
									<div class="appFormInputContainer4">\
									<label id="labelquantity" for="unit">Quantity Unit</label>\
										<input class="appFormInputquantity" type="text" name="unit" value="' + tablewareDetails.unit + '">\
									</div>\
									<input type="hidden" name="tid" value="'+ tablewareDetails.id +'"/>\
									<input type="submit" value="submit" id="editTablewareSubmitBtn" class="hidden"/>\
									</form>\
							',

						callback: function(isUpdate){
							if(isUpdate){
								document.getElementById('editTablewareSubmitBtn').click();
					 
							}
						}
						});

				}, 'json');	

				 

			},



			this.initialize = function(){
				this.registerEvents();
			}

		}
		var script = new script;
		script.initialize();

		function fadeaway(){
      document.getElementById('popup').style.display="none";
      document.getElementById('live_search').style.display="visible";
		}