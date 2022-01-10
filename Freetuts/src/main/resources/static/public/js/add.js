 $(document).ready(function() {
                    $('#frmAdd').validate({
                        submitHandler: function(form) {
                                $('#frmAdd').submit(function(e) {
                                    e.preventDefault();
                                    $.ajax({
                                        url: $(this).attr('th:action'),
                                        type: 'POST',
                                        data: $('#frmAdd').serialize(),
                                        success: function(res) {
                                            if (res === "TRUE") {
                                      
                                                swal({
                                                    title: "Add successfull !!!.",
                                                    text: "Done.",
                                                    type: "success"
                                                },function(){
                                                	window.location.href = 'add-category'
                                                })
                                            } else {
                                                swal("Fail!", "ERROR", "error");
                                            }

                                        },
                                        error: function(res) {
                                        	swal("Fail!", "ERROR", "error");
                                        }
                                    })
                                })
                            }
                             });
                    });