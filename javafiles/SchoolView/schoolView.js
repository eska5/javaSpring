import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../javaScript/dom_utils.js';
import {getBackendUrl} from '../javaScript/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplaySchool();
    fetchAndDisplayStudents();
});

function fetchAndDisplayStudents() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayStudents(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/schools/' + getParameterByName('school')
        + '/students', true);
    xhttp.send();
}

function displayStudents(students) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    students.students.forEach(student => {
        tableBody.appendChild(createTableRow(student));
    })
}

function createTableRow(student) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(student.student_id));
    tr.appendChild(createTextCell(student.name));
    tr.appendChild(createLinkCell('view', '../StudentView/StudentView.html?student=' + student.student_id +
        '&school=' +  getParameterByName('school') ));
    tr.appendChild(createLinkCell('edit', '../StudentEdit/studentEdit.html?student=' + student.student_id +
        '&school=' +  getParameterByName('school')));
    tr.appendChild(createButtonCell('delete', () => deleteStudent(student)));
    return tr;
}

function deleteStudent(student) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayStudents();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/schools/' + getParameterByName('school')
        + '/students/' + student.student_id, true);
    xhttp.send();
}

function fetchAndDisplaySchool() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySchool(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/schools/' + getParameterByName('school'), true);
    xhttp.send();
}

function displaySchool(school) {
    setTextNode('school name', school.schoolName);
    setTextNode('country', school.country);
    setTextNode('year of foundation', school.year_of_foundation);
}
