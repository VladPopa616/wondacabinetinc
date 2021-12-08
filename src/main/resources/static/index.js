
fetch('http://localhost:7001/orders', { mode: 'cors'})
    .then(response => response.json())
    .then(characters=>showCharacters(characters.result));

showCharacters = characters=> {
    const charactersDiv = document.querySelector('#orders');
    characters.forEach(character => {
        const characterElement = document.createElement('p');
        characterElement.innerText = 'Character Name: ${characters.orderStatus}';
        charactersDiv.append(characterElement);
    });
}