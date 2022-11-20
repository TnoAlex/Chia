const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");
const type = document.querySelector("#type_selector");

sign_up_btn.addEventListener("click", () => {
    container.classList.add("sign-up-mode");
    type.textContent = "1"
});

sign_in_btn.addEventListener("click", () => {
    container.classList.remove("sign-up-mode");
    type.textContent = "0"
});

function submitForm() {
    const type = $("#type_selector").text()
    const object = {
        username: "",
        password: "",
        type: ""
    }
    if (type === "0") {
        object.type = 0
        object.password = $("#student_passwd").val()
        object.username = $("#student_name").val()
    } else if (type === "1") {
        object.type = 1
        object.password = $("#teacher_passwd").val()
        object.username = $("#teacher_name").val()
    }
    $.ajax({
        type: "post",
        url: "/login",
        contentType: 'application/json',
        data: JSON.stringify(object),
        dataType: 'json',
        success: function (res) {
            console.log(res)
        },
        error: function (res) {
            console.log(res)
        }
    })
    return false
}
