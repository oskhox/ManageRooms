document.addEventListener("DOMContentLoaded", () => {
    const roomSelect = document.getElementById("rooms");
    const roomTypeInput = document.getElementById("roomTypeIdInput");


    roomSelect.addEventListener("change", () => {
        const selectedOption = roomSelect.options[roomSelect.selectedIndex];
        const roomTypeId = selectedOption.getAttribute("data-roomtypeid");
        roomTypeInput.value = roomTypeId;
    });

    const addBedsForm = document.getElementById("addBedsForm");
    addBedsForm.addEventListener("submit", function (e) {
        e.preventDefault();

        const formData = new FormData(addBedsForm);

        fetch("/room/addbeds", {
            method: "POST",
            body: formData
        })
            .then(response => {
                if (!response.ok) throw new Error("Något gick fel");
                return response.text();
            })
            .then(() => {
                document.getElementById("bedMessage").innerText = "Extra säng(ar) tillagda!";
            })
            .catch(() => {
                document.getElementById("bedMessage").innerText = "Kan inte lägga till fler sängar";
            });
    });
});

