function addOneBed() {
    document.getElementById("popupOneBed").style.display = "block";
    document.getElementById("popupTwoBeds").style.display = "none";
}

function addTwoBeds() {
    document.getElementById("popupTwoBeds").style.display = "block";
    document.getElementById("popupOneBed").style.display = "none";
}

document.addEventListener("click", function(event) {
    const popupOne = document.getElementById("popupOneBed");
    const popupTwo = document.getElementById("popupTwoBeds");
    const dropdown = document.querySelector(".dropdown");

    if(!dropdown.contains(event.target) &&
        !popupOne.contains(event.target) &&
        !popupTwo.contains(event.target)){

        popupOne.style.display = "none";
        popupTwo.style.display = "none";
    }
});