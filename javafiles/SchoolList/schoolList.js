import {
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell
} from '../javaScript/dom_utils.js';
import {getBackendUrl} from '../javaScript/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplaySchools();

});

function fetchAndDisplaySchools() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySchools(JSON.parse(this.responseText));
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/schools', true);
    xhttp.send();
}

function displaySchools(schools) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    schools.schools.forEach(school => {
        tableBody.appendChild(createTableRow(school.schoolName));
    })
    console.log(schools);
}

function createTableRow(school) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(school));
    tr.appendChild(createLinkCell('view', '../schoolView/schoolView.html?school=' + school));
    tr.appendChild(createLinkCell('edit', '../SchoolEdit/schoolEdit.html?school=' + school));
    tr.appendChild(createButtonCell('delete', () => deleteSchool(school)));
    return tr;
}

function deleteSchool(school) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySchools();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/schools/' + school, true);
    xhttp.send();
}
