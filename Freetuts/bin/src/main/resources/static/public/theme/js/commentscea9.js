
if(typeof(Storage) !== "undefined") {

    function setUserInfo(fullname, email, website)
    {
        localStorage.setItem('fullname', fullname);
        localStorage.setItem('email', email);
        //localStorage.setItem('website', website);
    }

    function generalUserInfo()
    {
        var fullname = localStorage.getItem('fullname');
        var email = localStorage.getItem('email');
        //var website = localStorage.getItem('website');
        if (fullname != 'undefined'){
            jQuery('.append-fullname').val(fullname);
        }
        if (email != 'undefined'){
            jQuery('.append-email').val(email);
        }
//        if (website != 'undefined'){
//            jQuery('.append-website').val(website);
//        }
    }

}
var html = '<div id="respond" class="comment-respond">';
        html += '<form action="#" method="post" id="commentform" class="comment-form">';
            html += '<a style="display: none" id="cancel-comment-reply-link" href="#">THOÁT FORM</a>';
            html += '<p class="comment-notes">';
                html += '<span id="email-notes"><strong>ĐĂNG BÌNH LUẬN</strong>: Thông tin của bạn sẽ được giữ bí mật.</span> <br/>';
                html += '<span style="display:block; margin-top: 10px"><strong>Lưu ý:</strong>: Sử dụng <span style="color: red">{Code}</span> để đăng code, và bình luận của bạn sẽ được kiểm duyệt cẩn thận.</span>';
            html += '</p>';
            html += '<p class="comment-form-comment">';
                html += '<textarea id="comment_content" placeholder="Comment..." name="comment" cols="45" rows="8" required="true"></textarea>';
            html += '</p>';
            html += '<input id="cm_name" class="append-fullname" type="text" placeholder="Name (required)" value="" size="30" required />';
            html += '<input id="cm_email" class="append-email" type="email" placeholder="Email (required)" value="" size="30" required />';
            //html += '<input id="cm_url" type="text" class="append-website" placeholder="Website" value="" size="30" style="margin-right: 0px" />';
            html += '<input id="parent_id" name="parent_id" type="hidden" value="0" size="30" />';
            html += '<p style="margin-bottom: 0px">';
                html += '<input type="text" id="cm_captcha" name="captcha" value="" size="10" placeholder="Captcha" style="width: 90px !important" required>';
                html += '<img src="" id="comment_image_captcha" style="margin-top: 0px; margin-bottom: 0px; margin-right: 10px"/>';
                
                html += '<div style="clear:both; height: 10px"></div><center><input name="submit" type="submit" id="submit-comment" class="submit" value="Đăng bình luận"/> </center> ';
            html += '</p>';
        html += '</form>';
    html += '</div>';
    
    var comments = 
    {
        comment_ref_id  : '',
        comment_type    : '',
        base_url    : '',
        avatar : '',
        current_page : '1',
        sending : false,
        
        load_comment : function()
        {
            var data = {
                comment_ref_id      : comments.comment_ref_id,
                comment_type        : comments.comment_type,
                load_comment        : jQuery('#load_comment').val(),
                page : comments.current_page
            };
            
            jQuery.ajax({
                url : comments.base_url + 'ajax/comment/lists',
                data : data,
                type: 'post',
                dataType : "text",
                success : function(result){
                    jQuery('#main_comment_list').append(result);
                }
            });
        },
        
        start : function()
        {
            // Load box comment ban đầu
            jQuery('#main_comment_wrapper').html(html);
            CKEDITOR.replace('comment_content');
            CKEDITOR.instances.comment_content.on('paste', function(evt) {
                    evt.cancel();
                });
            jQuery('#reply-title').hide();
            changeCaptcha();
            if(typeof(Storage) !== "undefined") {
                generalUserInfo();
            }
                
            // Tạo captcha comment
            function changeCaptcha()
            {
                jQuery('#comment_image_captcha').attr('src', comments.base_url + '/captcha/comment?rand=' + Math.random() + Math.random() + Math.random());
                jQuery('#comment_image_captcha').css('cursor', 'pointer');
                jQuery('#comment_image_captcha').click(function(){
                    jQuery('#comment_image_captcha').attr('src', comments.base_url + '/captcha/comment?rand=' + Math.random() + Math.random() + Math.random());
                });
            }
            
            function removeEditorInstance()
            {
                for(name in CKEDITOR.instances)
                {
                    CKEDITOR.instances[name].destroy(true);
                }
            }
            
            jQuery(document).on('click', '.comment-reply-link', function(){
               removeEditorInstance();
               jQuery('#respond').remove();
               jQuery('#main_comment_wrapper').html('');
               
               jQuery(this).parent().after(html);
               CKEDITOR.replace('comment_content');
               CKEDITOR.instances.comment_content.on('paste', function(evt) {
                    evt.cancel();
                });
               jQuery('#cancel-comment-reply-link').show();
               
               var parent_id = jQuery(this).attr('data-parent-id');
               jQuery('#parent_id').val(parent_id);
               
               changeCaptcha();
               if(typeof(Storage) !== "undefined") {
                    generalUserInfo();
                }
               return false;
            });

            jQuery(document).on('click', '#cancel-comment-reply-link', function(){
               removeEditorInstance();
               jQuery('#respond').remove();
               jQuery('#main_comment_wrapper').html(html);
               CKEDITOR.replace('comment_content');
               CKEDITOR.instances.comment_content.on('paste', function(evt) {
                    evt.cancel();
                });
               jQuery('#reply-title').hide();
               changeCaptcha();
                if(typeof(Storage) !== "undefined") {
                    generalUserInfo();
                }
               return false;
            });
            
            jQuery('#load_more_comment_btn').click(function(){
                comments.load_comment();
                return false;
            });
            
            // Đẩy top
            jQuery(document).on('click', '.push-top', function(){
                
                if (jQuery(this).attr('data-status') == 'true'){
                    return false;
                }
                
                var obj = this;
                
                var data = {
                    comment_id : jQuery(this).attr('data-id'),
                    push_comment :jQuery('#push_comment').val(),
                };
                
                jQuery.ajax({
                    url : comments.base_url + 'ajax/comment/push_top',
                    data : data,
                    type: 'post',
                    dataType : "text",
                    success : function(result){
                        if (result == '1')
                        {
                            var counter = parseInt(jQuery(obj).next().html());
                            counter += 1;
                            jQuery(obj).next().html(counter.toString());
                        }
                        else{
                            alert('Xuất hiện lỗi!');
                        }
                    }
                });
                jQuery(this).attr('data-status', 'true').css('color', '#000').css('cursor', 'text');
                return false;
            });
            
            jQuery(document).on('submit', '#commentform', function()
            {
                if (comments.sending == true){
                    alert('Vui lòng chờ, hệ thống đang xử lý ...');
                    return false;
                }
                
                var data = {
                    cm_content  : CKEDITOR.instances['comment_content'].getData(),
                    cm_fullname : jQuery('#cm_name').val(),
                    cm_email    : jQuery('#cm_email').val(),
                    //cm_website  : jQuery('#cm_url').val(),
                    cm_website  : '',
                    cm_parent_id: jQuery.trim(jQuery('#parent_id').val()),
                    cm_ref_id   : comments.comment_ref_id,
                    cm_type     : comments.comment_type,
                    add_comment : jQuery('#add_comment').val(),
                    cm_captcha  : jQuery('#cm_captcha').val()
                };
                if(typeof(Storage) !== "undefined") {

                    setUserInfo(data.cm_fullname, data.cm_email, data.cm_website);
                }
                
                comments.sending = true;
                
                jQuery.ajax({
                    url : comments.base_url + 'ajax/comment',
                    data : data,
                    type: 'post',
                    dataType : "text",
                    success : function(result)
                    {
                        result = jQuery.trim(result);
                        comments.sending = false;
                        if (result == 'captcha'){
                            alert('Mã captcha bị sai!');
                        }
                        else if (result == 'bad_request'){
                            alert('Vui lòng nhập nội dung bình luận!');
                        }
                        else
                        {
                            if (data.cm_parent_id != '0')
                            {
                                var html = '<li class="comment odd alt depth-2 single-comment" id="li-comment-{comment_id}">';
                                        html += '<div id="comment-{comment_id}" class="comment-wrap base-box">';
                                            html += '<img src="'+comments.avatar+'" class="avatar avatar-60 photo" height="60" width="60" />';
                                            html += '<div class="comment-content">';
                                                html += '<h4 class="comment-author fn">{comment_fullname}</h4>';
                                                html += '<span class="comment-meta commentmetadata ">';
                                                    html += 'Vừa mới';
                                                html += '</span>';
                                                html += '<div class="comment-text">';
                                                    html += '{comment_content}';
                                                html += '</div>';
                                                html += '<a rel="nofollow" class="comment-reply-link" data-parent-id="'+data.cm_parent_id+'" href="#comment-{comment_id}">Phản hồi</a>';
                                            html += '</div>';
                                        html += '</div>';
                                    html += '</li>';
                                
                                html = html.replace('{comment_id}', result);
                                html = html.replace('{comment_id}', result);
                                html = html.replace('{comment_id}', result);
                                html = html.replace('{comment_id}', result);
                                html = html.replace('{comment_id}', result);

                                html = html.replace('{comment_fullname}', data.cm_fullname);
                                html = html.replace('{comment_content}', data.cm_content);
                                jQuery('body').find('#li-comment-'+data.cm_parent_id).find('.children').append(html);
                                
                            }
                            else
                            {
                                var html = '<li class="comment even thread-even depth-1 single-comment" id="li-comment-{comment_id}">';
                                    html += '<div id="comment-{comment_id}" class="comment-wrap base-box">';
                                        html += '<div class="commentnumber">';
                                            html += '<a href="#" class="push-top" data-id="{comment_id}" data-status="false">Like</a>: ';
                                            html += '<span>0</span>';
                                        html += '</div>';
                                        html += '<img src="'+comments.avatar+'" class="avatar user-65-avatar avatar-60 photo" width="60" height="60" alt="" />';
                                        html += '<div class="comment-content">';
                                            html += '<h4 class="comment-author fn">{comment_fullname}</h4>';
                                            html += '<span class="comment-meta commentmetadata ">';
                                                html += 'Vừa mới';
                                            html += '</span>';
                                            html += '<div class="comment-text">';
                                                html += '{comment_content}';
                                            html += '</div>';
                                            html += '<a rel="nofollow" data-parent-id="{comment_id}"  class="comment-reply-link" href="#comment-{comment_id}" aria-label="Phản hồi">Phản hồi</a>';
                                        html += '</div>';
                                    html += '</div>';
                                    html += '<ol class="children">';
                                    html += '</ol>';
                                html += '</li>';
                                
                                html = html.replace('{comment_id}', result);
                                html = html.replace('{comment_id}', result);
                                html = html.replace('{comment_id}', result);
                                html = html.replace('{comment_id}', result);
                                html = html.replace('{comment_id}', result);

                                html = html.replace('{comment_fullname}', data.cm_fullname);
                                html = html.replace('{comment_content}', data.cm_content);
                                
                                if (jQuery('#main_comment_list li').length < 1){
                                    jQuery('#main_comment_list li').html('');
                                    jQuery('#main_comment_list').html(html);
                                }
                                else{
                                    jQuery('#main_comment_list li').first().before(html);
                                }
                            }
                            jQuery('#cancel-comment-reply-link').click();
                            jQuery('body, html').animate({
                                    scrollTop: jQuery('#li-comment-' + result).offset().top - 10
                            }, 500);
                        }
                    },
                    error : function(){
                        comments.sending = false;
                    }
                }).always(function(){
                    comments.sending = false;
                });
               return false;
            });
        }
    };
    