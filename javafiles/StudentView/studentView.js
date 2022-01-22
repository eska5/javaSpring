import {
    getParameterByName,
    setTextNode
} from '../javaScript/dom_utils.js';
import {getBackendUrl} from '../javaScript/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayStudent();
});



function fetchAndDisplayStudent() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayStudent(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/schools/' + getParameterByName('school') + '/students/'
        + getParameterByName('student'), true);
    xhttp.send();
}

function displayStudent(student) {
    setTextNode('student_id', student.student_id);
    setTextNode('name', student.name);
    setTextNode('surname', student.surname);
    setTextNode('email', student.email);
    setTextNode('school', student.school);
}
