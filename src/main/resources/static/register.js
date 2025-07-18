document.getElementById('registrationForm').addEventListener('submit', async function(e) {
    e.preventDefault();

    // Basic form validation
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value;
    const accountType = document.getElementById('accountType').value;

    if (!name || !email || !password || !accountType) {
        alert('Please fill in all fields');
        return;
    }

    if (!isValidEmail(email)) {
        alert('Please enter a valid email address');
        return;
    }

    const userData = { name, email, password, accountType };

    // Show loading state
    const submitButton = this.querySelector('button[type="submit"]');
    const originalButtonText = submitButton.textContent;
    submitButton.disabled = true;
    submitButton.textContent = 'Registering...';

    try {
        console.log('Request Payload:', JSON.stringify(userData)); // Log request payload

        const response = await fetch('http://localhost:8080/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData)
        });

        if (!response.ok) {
            const errorText = await response.text();
            console.error('Response Error:', errorText); // Log response error
            throw new Error(`HTTP error! status: ${response.status}, message: ${errorText}`);
        }

        const contentType = response.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            const data = await response.json();

            if (data.status === 'success') {
                // Display success message
                const successMessage = document.createElement('p');
                successMessage.textContent = 'Registration successful! You are now signed in.';
                document.getElementById('registrationForm').appendChild(successMessage);


            } else {
                throw new Error(data.message || 'Registration failed');
            }
        } else {
            throw new Error('Unexpected response format');
        }
    } catch (error) {
        console.error('Error:', error);
        alert(`Registration failed: ${error.message}`);
    } finally {
        // Reset button state
        submitButton.disabled = false;
        submitButton.textContent = originalButtonText;
    }
});

function isValidEmail(email) {
    // Basic email validation regex
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}