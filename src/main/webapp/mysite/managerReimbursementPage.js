'use strict'

let pending = document.getElementById('manager-pending');
let processed = document.getElementById('manager-processed');
let pendingResults = document.getElementById('pending-results');
let processedResults = document.getElementById('processed-results');

let uri = "http://localhost:8080/project-1-jsyuille/managerReimburse";
let ApprovalUri = "http://localhost:8080/project-1-jsyuille/approval";
let x;

pending.addEventListener('submit', (event)=>{
    event.preventDefault();
    getPending();
});


async function getPending() {
    let response = await fetch(uri, { method: 'GET' });
    let respString = await response.text();
    let obj = {};
    pendingResults.innerHTML = '';

    JSON.parse(respString, function (key, value) {

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

            let pendingInfo = document.createElement('p');
            pendingInfo.setAttribute('id',`column ${obj.id}`);
            pendingInfo.innerText = "Request of: $" + obj.amount + ", Status: Processed, Employee: " + obj.employee;
            pendingResults.appendChild(pendingInfo);
            
            let approveButton = document.createElement('button');
            approveButton.setAttribute('class', 'btn btn-success');
            approveButton.setAttribute('type','submit');
            approveButton.setAttribute('id', `approve ${obj.id}`);
            approveButton.setAttribute('onClick', 'approval(this.id)');
            approveButton.innerText = "Approve";
            pendingResults.appendChild(approveButton);

            let denyButton = document.createElement('button');
            denyButton.setAttribute('class', 'btn btn-danger');
            denyButton.setAttribute('type','submit');
            denyButton.setAttribute('id',`deny ${obj.id}`);
            denyButton.setAttribute('onClick','approval(this.id)');
            denyButton.innerText = "Reject";
            pendingResults.appendChild(denyButton);
            obj = {};
        }
    });
}


async function approval (clicked_id) {
    let str = clicked_id.match(/\d+/g).map(Number);
    Number(str);    //Being extra safe because of deserialization errors
    let e;
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
    let resp = await fetch(ApprovalUri, {method: 'POST', body: JSON.stringify(obj)});
    
    if (resp.status == 200) {
        let e = document.getElementById(`column ${str}`);
        let f = document.getElementById(`deny ${str}`);
        let g = document.getElementById(`approve ${str}`);
        g.remove();
        f.remove();
        e.remove();
    }
}


processed.addEventListener('submit',(event)=>{
    event.preventDefault();
    getProcessed();
});

async function getProcessed() {
    let response = await fetch(uri, { method: 'POST', body: null });
    let respString = await response.text();
    let obj = {};
    processedResults.innerHTML = '';

    JSON.parse(respString, function (key, value) {
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
            processedInfo.innerText = "Request of: $" + obj.amount + " | Employee: " + obj.employee + "| Status: Processed | Response: "+ obj.approval + " | Approved By: " + obj.approvedBy;
            processedResults.appendChild(processedInfo);
            obj = {};
        }
    });
}