import {getParameterByName} from '../javaScript/dom_utils.js';
import {getBackendUrl} from '../javaScript/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('schoolForm');
    infoForm.addEventListener('submit', event => postInfoAction(event));

    fetchAndDisplaySchool();
});

function fetchAndDisplaySchool() {
    let input = document.getElementById("school");
    input.value = getParameterByName('school');
}

function postInfoAction(event) {
    event.preventDefault();


    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/schools/'+ getParameterByName('school')
        + '/students' , true);

    const request = {
        'name': document.getElementById('name').value,
        'surname': document.getElementById('surname').value,
        'email': document.getElementById('email').value,
        'school': document.getElementById('school').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    alert("New Student has been added !!!");
}
