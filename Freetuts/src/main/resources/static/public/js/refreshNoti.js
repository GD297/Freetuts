function loadNotiAuto() {
    this.source = null;
    this.start = function () {
        let listNoti = document.getElementById("listNOTI");
        this.source = new EventSource("/updateListRecord");
        this.source.addEventListener("message", function (event) {
            // These events are JSON, so parsing.
            let noti = JSON.parse(event.data);
 			listNoti.innerHTML +=' <span class="dropdown-item-desc"> <span class="message-user">'+noti.author+' </span> <span class="time messege-text">'+noti.message+'</span> <span class="time">'+noti.time+'</span></span>';
        });
 
        this.source.onerror = function () {
            this.close();
        };
    };
 
    this.stop = function () {
        this.source.close();
    };
}
 
loadNoti = new loadNotiAuto();
 
/*
 * Register callbacks for starting and stopping the SSE controller.
 */
window.onload = function () {
    loadNoti.start();
};
 
window.onbeforeunload = function () {
    loadNoti.stop();
}