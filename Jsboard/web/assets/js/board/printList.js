let initPage = () => {
    let temp = new URLSearchParams(window.location.search).get("pageNo");
    let data;
    if (temp == null) {
        data = {"pageNo": 1}
    } else {
        data = {"pageNo": temp}
    }
    $.ajax({
        url: "/board/printList",
        method: "GET",
        data: data,
        success: (message) => {
            let result = JSON.parse(message);
            let array = JSON.parse(result.data);
            array.forEach((b) => {
                insertRow(b);
            })
        }
    });
}

function insertRow(board) {

    let tbody = $('tbody');
    let pagination = $(tbody).children().last();
    $(tbody).remove(pagination);
    let tr = document.createElement("tr");
    $(tr).addClass("table-danger");
    $(tr).click(() => {
        location.href = "/board/printOne.jsp?id="+board.id;
    });
    $(tr).append($(document.createElement("td")).text(board.id));
    $(tr).append($(document.createElement("td")).text(board.title));
    $(tr).append($(document.createElement("td")).text(board.writerNickname));
    $(tr).append($(document.createElement("td")).text(board.entryDate));
    $(tr).append($(document.createElement("td")).text(board.modifyDate));
    $(tbody).append(tr);
    $(tbody).append(pagination);
}