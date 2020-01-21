'use strict'

let requestForm = document.getElementById('req-form');
let requestAmount = document.getElementById('req-amount');
let requestPicture = document.getElementById('req-pic');

let pendingReqs = document.getElementById("pending-reqs-tab");
let processedReqs = document.getElementById("processed-reqs-tab");

let reimburseUri = "http://localhost:8080/project-1-jsyuille/reimbursements";
let processedReqUri = "http://localhost:8080/project-1-jsyuille/processed";
let feedback = document.createElement("p");

let pendingReqsList = document.getElementById("pending-resps");
let processedReqsList = document.getElementById("processed-resps")


processedReqs.addEventListener('submit', (event) => {
    console.log("Inside Processed requests event listener");
    event.preventDefault();
    getProcessedInfo();
});

pendingReqs.addEventListener('submit', (event) => {
    console.log("Inside Pending Requests event listener");
    event.preventDefault();
    getPendingInfo();
});


async function getPendingInfo() {
    let response = await fetch(reimburseUri, { method: 'GET' });
    let respString = await response.text();
    pendingReqsList.innerHTML = '';

    JSON.parse(respString, function (key, value) {
        if (key == "amount") {
            let pendingStatusInfo = document.createElement('p');
            pendingStatusInfo.innerText = "Request of: $" + value + ", Status: Pending";
            pendingReqsList.appendChild(pendingStatusInfo);
        }
    });
}

async function getProcessedInfo() {

    let response = await fetch(processedReqUri, { method: 'GET' });
    let respString = await response.text();
    let obj = {};
    processedReqsList.innerHTML = ''; //prevents repeated rows

    JSON.parse(respString, function (key, value) {
        console.log ("whole string being parsed: " + respString);
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
            let processedInfo = document.createElement('p');
            processedInfo.innerText = "Request of: $" + obj.amount + " | Employee: " + obj.employee + " | Status: Processed | Response: "+ obj.approval + " | Approved By: " + obj.approvedBy;
            processedReqsList.appendChild(processedInfo);
            obj = {};
        }
    });
}


requestForm.addEventListener('submit', (event) => {
    event.preventDefault();
    submitReq();
});

async function submitReq() {
    let req = {};
    req.id = 0;
    req.amount = requestAmount.value;
    req.status = 'pending';
    req.approval = ' ';
    req.approvedBy = ' ';
    req.employeeName = ' ';

    let resp = await fetch(reimburseUri, { method: 'POST', body: JSON.stringify(req) });

    if (resp.status == 200) {
        requestForm.reset();
        feedback.innerText = "Your request has been sent!"
        requestForm.appendChild(feedback);
    }
    else if (resp.status == 400) {
        feedback.id = "invalid";
        feedback.innerText = "Invalid amount";
        requestForm.appendChild(feedback);
    }
}
