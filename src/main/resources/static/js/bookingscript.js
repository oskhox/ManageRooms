function showRoom() {
    const customer = document.getElementById('customerForm');
    const room = document.getElementById('roomForm');

    customer.classList.add('animate-out');
    customer.style.display = 'none';
    customer.classList.remove('animate-out');
    room.style.display = 'block';
    room.classList.add('animate-in');

}

function submitForm(){

    document.getElementById('submitForm').submit();
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

