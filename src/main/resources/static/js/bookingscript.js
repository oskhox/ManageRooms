function showRoom() {
    const customer = document.getElementById('loginForm');
    const room = document.getElementById('registerForm');

    customer.classList.add('animate-out');
    customer.style.display = 'none';
    customer.classList.remove('animate-out');
    room.style.display = 'block';
    room.classList.add('animate-in');

}

function showCustomer() {
    const customer = document.getElementById('loginForm');
    const room = document.getElementById('registerForm');

    room.classList.add('animate-out');
    room.style.display = 'none';
    room.classList.remove('animate-out');
    customer.style.display = 'block';
    customer.classList.add('animate-in');

}

