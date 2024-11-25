document.getElementById('registrationForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const userData = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    };

    fetch('/api/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            // Handle successful registration
        })
        .catch((error) => {
            console.error('Error:', error);
            // Handle errors
        });
});