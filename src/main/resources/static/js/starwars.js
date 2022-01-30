"use strict";
document.getElementById("zoeken").onclick = filmForm;

function filmForm() {
    if (document.getElementById("nummer").value < 1) {
        document.getElementById("moetPositief").hidden = false;
    } else {
        window.location.href = "http://localhost:8080/film/" + document.getElementById("nummer").value;
    }
}
