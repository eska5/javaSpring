import {getParameterByName} from '../javaScript/dom_utils.js';
import {getBackendUrl} from '../javaScript/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('schoolForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplaySchool();
});

function fetchAndDisplaySchool() {
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
    xhttp.open("GET", getBackendUrl() + '/api/schools/' + getParameterByName('school'), true);
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
    xhttp.open("PUT", getBackendUrl() + '/api/schools/' + getParameterByName('school'), true);

    const request = {
        'schoolName': document.getElementById('schoolName').value,
        'country': document.getElementById('country').value,
        'year_of_foundation': parseInt(document.getElementById('year_of_foundation').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    alert("School has been updated !!!");
}

