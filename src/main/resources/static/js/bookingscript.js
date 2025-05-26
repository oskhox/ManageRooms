function showRoom() {
    const customer = document.getElementById('customerForm');
    const room = document.getElementById('roomForm');

    customer.classList.add('animate-out');
    customer.style.display = 'none';
    customer.classList.remove('animate-out');
    room.style.display = 'block';
    room.classList.add('animate-in');

}

function submitForm() {

    document.getElementById('submitForm').submit();

    function searchRooms() {
        const peopleCount = document.querySelector('[name="peopleCount"]').value;
        const start = document.querySelector('[name="start"]').value;
        const end = document.querySelector('[name="end"]').value;

        fetch(`/rooms/search?peopleCount=${peopleCount}&start=${start}&end=${end}`)
            .then(response => response.text())
            .then(html => {
                document.getElementById('roomResults').innerHTML = html;
            });
        return false;
    }

    function showCustomer() {
        const customer = document.getElementById('customerForm');
        const room = document.getElementById('roomForm');

        room.classList.add('animate-out');
        room.style.display = 'none';
        room.classList.remove('animate-out');
        customer.style.display = 'block';
        customer.classList.add('animate-in');

    }

}