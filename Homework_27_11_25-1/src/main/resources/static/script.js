async function checkFraction() {
    const num = document.getElementById('numerator').value;
    const den = document.getElementById('denominator').value;
    const resultDiv = document.getElementById('result');

    resultDiv.innerHTML = "Запрос к серверу...";
    resultDiv.className = "result";

    try {
        const response = await fetch(`/api/fractions/is-proper?numerator=${num}&denominator=${den}`);
        const text = await response.text();

        if (text.includes("Ошибка") || !response.ok) {
            resultDiv.className = "result error";
            resultDiv.innerHTML = text;
        } else {
            resultDiv.className = "result success";
            resultDiv.innerHTML = text;
        }
    } catch (error) {
        resultDiv.className = "result error";
        resultDiv.innerHTML = "Ошибка подключения к серверу";
    }
}

window.onload = checkFraction;

document.getElementById('numerator').addEventListener('input', checkFraction);
document.getElementById('denominator').addEventListener('input', checkFraction);

document.addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        checkFraction();
    }
});