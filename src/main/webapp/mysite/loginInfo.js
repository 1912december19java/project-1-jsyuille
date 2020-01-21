'use strict'

let loginForm = document.getElementById("login-form");
let employeeUsername = document.getElementById("username-login");
let employeePassword = document.getElementById("password-login");

let loginUri = "http://localhost:8080/project-1-jsyuille/login/";


loginForm.addEventListener('submit',(event)=>{
    event.preventDefault();
    sendLoginInfo();
});


async function sendLoginInfo () {
    console.log(employeeUsername.value);
    console.log(employeePassword.value);

    let loginInfo = {};
    loginInfo.emp_id = 0;
    loginInfo.emp_name = employeeUsername.value;
    loginInfo.emp_password = employeePassword.value;
    loginInfo.emp_position = 'employee';

    console.log("Login Info: " + loginInfo.emp_id + loginInfo.emp_name + loginInfo.emp_password + loginInfo.emp_position);

    let response = await fetch (loginUri, {method:'POST',body: JSON.stringify(loginInfo)});

    console.log(response);
    let body = await response.text();
    console.log(`Response Body: ${body}`)

    if (response.status == 206) {
    window.location.href = "http://localhost:8080/project-1-jsyuille/mysite/EmployeePortal.html";
    }
    else if (response.status == 200) {
        window.location.href = "http://localhost:8080/project-1-jsyuille/mysite/ManagerPortal.html";
    }
    else {
        console.log("Invalid Credentials");
        document.getElementById("invalid-login").innerText = "Incorrect username or password!";
        loginForm.reset();
    }
}