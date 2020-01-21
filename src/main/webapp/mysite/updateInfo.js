'use strict'

//********************************************* */
//Update Employee Login Info

let changeInfoForm = document.getElementById("change-info-form");
let employeeUsername = document.getElementById("change-username");
let employeePassword = document.getElementById("change-password");

let currentUsername = document.getElementById("current-username");
let currentPassword = document.getElementById("current-password");

let updateInfoUri = "http://localhost:8080/project-1-jsyuille/updateInfo";


// document.addEventListener('DOMContentLoaded',(event)=>{
   
//     let response = {};
//     response.emp_name = null;
//     response.emp_password = null;
   
//     let getInfo = fetch (updateInfoUri, {method: 'GET', body: JSON.stringify(response)});
    
//     let currentUserInfo = document.createElement('p');
//     let currentPassInfo = document.createElement('p');

//     currentUserInfo.innerText = getInfo.value;
//     currentPassInfo.innerText = getInfo.value;

//     console.log(getInfo.text);
//     console.log(getInfo.value);

//     currentUsername.appendChild(currentUserInfo);
//     currentPassword.appendChild(currentPassInfo);

// });

changeInfoForm.addEventListener('submit', (event)=>{
    event.preventDefault();
    UpdateLoginInfo();
});

async function UpdateLoginInfo() {

    let updatedInfo = {};
    updatedInfo.emp_id = 0;
    updatedInfo.emp_name = employeeUsername.value;
    updatedInfo.emp_password = employeePassword.value;
    updatedInfo.emp_position = 'employee';

    console.log("Login Info: " + updatedInfo.emp_id + updatedInfo.emp_name + updatedInfo.emp_password + updatedInfo.emp_position);

    let response = await fetch (updateInfoUri, {method:'POST',body: JSON.stringify(updatedInfo)});


    console.log(response);
    let body = await response.text();
    console.log(`Response Body: ${body}`)

    if (response.status == 200) {
        changeInfoForm.reset();
        let changed = document.createElement("p");
        changed.innerText = "Your information has been updated!"
        changeInfoForm.appendChild(changed);
        }
}