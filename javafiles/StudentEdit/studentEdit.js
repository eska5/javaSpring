import {getParameterByName} from '../../../../javaScript/dom_utils.js';
import {getBackendUrl} from '../../../../javaScript/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('studentForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayStudent();
});

function fetchAndDisplayStudent() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/schools/' + getParameterByName('school') + '/students/'
        + getParameterByName('student'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplaySchool();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/schools/' + getParameterByName('school') + '/students/'
        + getParameterByName('student'), true);

    const request = {
        'name': document.getElementById('name').value,
        'surname': document.getElementById('surname').value,
        'email': document.getElementById('email').value,
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    alert("Student has been updated !!!");
}

