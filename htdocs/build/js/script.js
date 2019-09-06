function study_write() {
    location.href = "study_write.html"
}

function test_write(){
    locataion.href ="test_write.html"
}

function study_submit() {
    location.href = "study_list.html"
}

function study_list() {
    location.href = "study_list.html"
}

function notice_write() {
    location.href = "notice_write.html"
}

function notice_list() {
    location.href = "notice_list.html"
}

function qna_write() {
    location.href = "qna_write.html"
}

function qna_list() {
    location.href = "qna_list.html"
}

function faq_write() {
    location.href = "faq_write.html"
}

function faq_list() {
    location.href = "faq_list.html"
}

function study_test() {
    location.href ="study_test.html"
}

function study_key() {
    location.href = "study_key.html"
}
function notification(){
    alert("알림을 전송했습니다.");
    history.back();
}
function clearhiscourse(){
    alert("수료 처리가 완료되었습니다.");
    location.href = "member_test.php"
}

function clearhiscourse(){
    alert("수료 처리가 완료되었습니다.");
    location.href = "member_test.php"
}
function clearhiscourse(){
    alert("수료 처리가 완료되었습니다.");
    location.href = "member_test.php"
}
function clearhiscourse(){
    alert("수료 처리가 완료되었습니다.");
    location.href = "member_test.php"
}
function study_detail_selectbox(e, coursenum){
    var state = e.value;

    $.ajax({
        url: './build/js/learn_course.php',
        type: 'post',
        dataType: 'json',
        data: {"course_num": coursenum, "state": state},
        success: function(data){
            var i = 0;
            var res = "";

            while(data[i]){

                //짝수행과 홀수행의 속성 지정
                if(i%2 == 0)
                {
                    res += '<tr class="even pointer" style="text-align: center;"></tr>';
                }                    
                else
                {
                    res += '<tr class="odd pointer" style="text-align: center;"></tr>';
                }
                res += '<td class="center" style="text-align: center;">';
                res += '<input type="checkbox" class="chk" name="row'+(i+1)+'" value="'+data[i]['mem_token']+'">';
                res += '</td>';
                res += '<td class=" " style="text-align: center;">'+data[i]['com_name']+'</td>';
                res += '<td class=" " style="text-align: center;">'+data[i]['mem_num']+'</td>';
                res += '<td class=" " style="text-align: center;">'+data[i]['mem_name']+'</td>';
                res += '<td class=" " style="text-align: center;">';
                if(data[i]['mem_learn_state'] == 3)
                {
                    res += "수료 완료";
                }
                else
                {
                    res += "미수료";
                }
                res += "</td></tr>";

                i++;
            }

            $("#learn_course_table").html(res);
        }
    });
    

}