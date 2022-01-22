import {getBackendUrl} from '../javaScript/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('schoolForm');
    infoForm.addEventListener('submit', event => postInfoAction(event));
});

function postInfoAction(event) {
    event.preventDefault();


    const xhttp = new XMLHttpRequest();
    xhttp.open("POST", getBackendUrl() + '/api/schools' , true);

    const request = {
        'schoolName': document.getElementById('schoolName').value,
        'country': document.getElementById('country').value,
        'year_of_foundation': parseInt(document.getElementById('year_of_foundation').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

    alert("New School has been added !!!");
}
