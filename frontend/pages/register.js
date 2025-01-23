document.getElementById('registrationForm').addEventListener('submit', function(e) {
    e.preventDefault();

    // Basic form validation
    const firstName = document.getElementById('firstName').value.trim();
    const lastName = document.getElementById('lastName').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value;

    if (!firstName || !lastName || !email || !password) {
        alert('Please fill in all fields');
        return;
    }

    if (!isValidEmail(email)) {
        alert('Please enter a valid email address');
        return;
    }

    const userData = { firstName, lastName, email, password };

    // Show loading state
    const submitButton = this.querySelector('button[type="submit"]');
    const originalButtonText = submitButton.textContent;
    submitButton.disabled = true;
    submitButton.textContent = 'Registering...';

    fetch('/api/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(userData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            if (data.status === 'success') {
                window.location.href = '/account.html';
            } else {
                throw new Error(data.message || 'Registration failed');
            }
        })
        .catch((error) => {
            console.error('Error:', error);
            alert(`Registration failed: ${error.message}`);
        })
        .finally(() => {
            // Reset button state
            submitButton.disabled = false;
            submitButton.textContent = originalButtonText;
        });
});

function isValidEmail(email) {
    // Basic email validation regex
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

