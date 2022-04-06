window.onload = function() {
    document.getElementById("apibutton").addEventListener("click", alertAPILimit);
}

function alertAPILimit() {
    var count = document.getElementById("count").innerText;
    console.log(count);
    var limit = document.getElementById("limit").innerText;
    console.log(limit);

    if (count === limit) {
        alert("You have reached your limit. No new calls can be made!");
    }
}

