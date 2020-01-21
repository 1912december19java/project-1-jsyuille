'use strict'

let empList = document.getElementById("employee-list");
let singleEmp = document.getElementById('single-employee-pending');
let singleEmpProcessed = document.getElementById("single-employee-processed");

let uri = "http://localhost:8080/project-1-jsyuille/view";
let ApprovalUri = "http://localhost:8080/project-1-jsyuille/approval";


window.onload = function () {
    getList();
};


async function getList() {
    let resp = await fetch(uri, { method: 'GET' });
    let responseStr = await resp.text();

    JSON.parse(responseStr, function (key, value) {

        if (key == "emp_name") {
            let elem = document.createElement('li');
            elem.style.textDecoration = "underline";
            elem.className = 'employee-list';
            elem.setAttribute('id',`${value}`);
            elem.setAttribute('onClick', `getEmployeeReimbursements(this.id)`);
            elem.innerText = value;
            empList.appendChild(elem);
        }
    });
}

async function getEmployeeReimbursements(clicked_name) {
    singleEmp.innerHTML = '';
    singleEmpProcessed.innerHTML = '';  //These remove the previously created
    let clickObj = {};
    clickObj.employeeName = clicked_name;
    
    let resp = await fetch(uri, { method: 'POST', body: JSON.stringify(clickObj) });
    let respStr = await resp.text();
    let obj = {};
    JSON.parse(respStr, function (key, value) {

        if (key == "id") {
            obj.id = value;
        } else if (key == "amount") {
            obj.amount = value;
        } else if (key == "status") {
            obj.status = value;
        } else if (key == "approvedBy") {
            obj.approvedBy = value;
        } else if (key == "approval") {
            obj.approval = value;
        } else if (key == "employeeName") {
            obj.employee = value;
        }

        if (Object.keys(obj).length == 6) {
            if (obj.status == 'pending') {
                displayPendingInfo(obj);
            } else {
                displayProcessedInfo(obj);
            }
            obj = {};
        }
        
    });
}

function displayPendingInfo(obj) {

    let pendingInfo = document.createElement('p');
    pendingInfo.setAttribute('id', `column ${obj.id}`);
    pendingInfo.innerText = "Request of: $" + obj.amount + ", Status: Processed, Employee: " + obj.employee;
    singleEmp.appendChild(pendingInfo);

    let approveButton = document.createElement('button');
    approveButton.setAttribute('class', 'btn btn-success');
    approveButton.setAttribute('type', 'submit');
    approveButton.setAttribute('id', `approve ${obj.id}`);
    approveButton.setAttribute('onClick', 'approval(this.id)');
    approveButton.innerText = "Approve";
    singleEmp.appendChild(approveButton);

    let denyButton = document.createElement('button');
    denyButton.setAttribute('class', 'btn btn-danger');
    denyButton.setAttribute('type', 'submit');
    denyButton.setAttribute('id', `deny ${obj.id}`);
    denyButton.setAttribute('onClick', 'approval(this.id)');
    denyButton.innerText = "Reject";
    singleEmp.appendChild(denyButton);
}

async function approval(clicked_id) {
    let str = clicked_id.match(/\d+/g).map(Number);
    Number(str);    //Being extra safe because of deserialization errors
    let obj = {};
    obj.id = parseInt(str);
    obj.amount = 0;
    obj.status = 'processed';
    obj.approvedBy = '';
    obj.employeeName = '';

    if (clicked_id === `deny ${str}`) {
        obj.approval = 'Rejected';

    } else {
        obj.approval = 'Approved';
    }
    let resp = await fetch(ApprovalUri, { method: 'POST', body: JSON.stringify(obj) });

    if (resp.status == 200) {
        let e = document.getElementById(`column ${str}`);
        let f = document.getElementById(`deny ${str}`);
        let g = document.getElementById(`approve ${str}`);
        g.remove();
        f.remove();
        e.remove();
    }
}

function displayProcessedInfo(obj) {
    let processedInfo = document.createElement('p');
    processedInfo.innerText = "Request of: $" + obj.amount + " | Employee: " + obj.employee + "| Status: Processed | Response: " + obj.approval + " | Approved By: " + obj.approvedBy;
    singleEmpProcessed.appendChild(processedInfo);
}
