document.addEventListener( 'DOMContentLoaded', () => {
    // шукаємо кнопку реєстрації, якщо знаходимо - додаємо обробник
    const signupButton = document.getElementById("signup-button");
    if(signupButton) { signupButton.onclick = signupButtonClick; }
    // шукаємо кнопку автентифікації, якщо знаходимо - додаємо обробник
    const authButton = document.getElementById("auth-button");
    if(authButton) { authButton.onclick = authButtonClick; }
    // шукаємо кнопку додавання товарів, якщо знаходимо - додаємо обробник
    const addItemButton = document.getElementById("addProduct-button");
    if(addItemButton) { addItemButton.onclick = addItemButtonClick; }
    // налаштування модальних вікон
    var elems = document.querySelectorAll('.modal');
    M.Modal.init(elems, {
        "opacity": 	    	0.5, 	// Opacity of the modal overlay.
        "inDuration": 		250, 	// Transition in duration in milliseconds.
        "outDuration": 		250, 	// Transition out duration in milliseconds.
        "onOpenStart": 		null,	// Callback function called before modal is opened.
        "onOpenEnd": 		null,	// Callback function called after modal is opened.
        "onCloseStart":		null,	// Callback function called before modal is closed.
        "onCloseEnd": 		null,	// Callback function called after modal is closed.
        "preventScrolling": true,	// Prevent page from scrolling while modal is open.
        "dismissible": 		true,	// Allow modal to be dismissed by keyboard or overlay click.
        "startingTop": 		'4%',	// Starting top offset
        "endingTop": 		'10%'	// Ending top offset
    });
});

function authButtonClick(e) {
    const emailInput = document.querySelector('input[name="auth-email"]');
    if( ! emailInput ) { throw "'auth-email' not found" ; }
    const passwordInput = document.querySelector('input[name="auth-password"]');
    if( ! passwordInput ) { throw "'auth-password' not found" ; }

    // console.log( emailInput.value, passwordInput.value ) ;
    fetch(`/auth?email=${emailInput.value}&password=${passwordInput.value}`, {
        method: 'PATCH'
    })
        .then( r => r.json() )
        .then( console.log ) ;
}

function signupButtonClick(e) {
    // шукаємо форму - батьківській елемент кнопки (e.target)
    const signupForm = e.target.closest('form') ;
    if( ! signupForm ) {
        throw "Signup form not found" ;
    }
    // всередині форми signupForm знаходимо елементи
    const nameInput = signupForm.querySelector('input[name="user-name"]');
    if( ! nameInput ) { throw "nameInput not found" ; }
    const emailInput = signupForm.querySelector('input[name="user-email"]');
    if( ! emailInput ) { throw "emailInput not found" ; }
    const passwordInput = signupForm.querySelector('input[name="user-password"]');
    if( ! passwordInput ) { throw "passwordInput not found" ; }
    const repeatInput = signupForm.querySelector('input[name="user-repeat"]');
    if( ! repeatInput ) { throw "repeatInput not found" ; }
    const avatarInput = signupForm.querySelector('input[name="user-avatar"]');
    if( ! avatarInput ) { throw "avatarInput not found" ; }
    const errorDiv = document.querySelector('.errorDiv');
    if (!errorDiv) {
        throw "errorDiv not found";
    }


    /// Валідація даних
    let isFormValid = true ;

    if( nameInput.value === "" ) {
        nameInput.classList.remove("valid");
        nameInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        nameInput.classList.remove("invalid");
        nameInput.classList.add("valid");
    }

    if( emailInput.value === "" ) {
        emailInput.classList.remove("valid");
        emailInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        emailInput.classList.remove("invalid");
        emailInput.classList.add("valid");
    }

    if( passwordInput.value === "" ) {
        passwordInput.classList.remove("valid");
        passwordInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        passwordInput.classList.remove("invalid");
        passwordInput.classList.add("valid");
    }

    if( repeatInput.value === "" || passwordInput.value !== repeatInput.value) {
        repeatInput.classList.remove("valid");
        repeatInput.classList.add("invalid");
        isFormValid = false ;
    }
    else {
        repeatInput.classList.remove("invalid");
        repeatInput.classList.add("valid");
    }

    if( ! isFormValid ) {
        passwordInput.value = '';
        repeatInput.value = '';
        errorDiv.style.display = "block";
        /*errorDiv.style.border = "2px solid purple;"
        errorDiv.style.font = "16px";
        errorDiv.style.height = "30px"; */
        errorDiv.textContent = "Виникли помилки реєстрації. Будь ласка спробуйте ще раз!";
        return ;
    }
    /// кінець валідації

    // формуємо дані для передачі на бекенд
    const formData = new FormData() ;
    formData.append( "user-name", nameInput.value ) ;
    formData.append( "user-email", emailInput.value ) ;
    formData.append( "user-password", passwordInput.value ) ;
    if( avatarInput.files.length > 0 ) {
        formData.append( "user-avatar", avatarInput.files[0] ) ;
    }

    // передаємо - формуємо запит
    fetch( window.location.href, { method: 'POST', body: formData } )
        .then( r => r.json() )
        .then( data => {
            console.log(data);
            if (data.meta.status === "success") {
                alert('Реєстрація успішна');
                window.location = '/Java_Web_SPD111/'; // переходимо на головну сторінку
                console.log("Success");
            } else {
                errorDiv.textContent += data.message;
                console.error("Error during registration:", data.message);
                // Тут ви можете зробити щось із повідомленням про помилку, наприклад, вивести його на сторінку або показати модальне вікно
            }
        } ) ;
    /*fetch( window.location.href, { method: 'POST', body: formData } )
        .then( r => r.json() )
        .then( j => {
            console.log(j);
        } ) ;*/
}

function addItemButtonClick(e) {
    console.log("Hello!");
    // шукаємо форму - батьківській елемент кнопки (e.target)
    const addItemForm = document.getElementById('add-product-form');
    if( ! addItemForm ) {
        throw "AddItem form not found" ;
    }
    // всередині форми signupForm знаходимо елементи
    const productNameInput = addItemForm.querySelector('input[name="product-name"]');
    if( ! productNameInput ) { throw "productNameInput not found" ; }
    const productPriceInput = addItemForm.querySelector('input[name="product-price"]');
    if( ! productPriceInput ) { throw "productPriceInput not found" ; }
    const productDescriptionInput = addItemForm.querySelector('textarea[name="product-description"]');
    if( ! productDescriptionInput ) { throw "productDescriptionInput not found" ; }
    const productPhotoInput = addItemForm.querySelector('input[name="product-photo"]');
    if( ! productPhotoInput ) { throw "productPhotoInput not found" ; }

    /// Валідація даних
    let isFormValid = true ;
    /*
        if( productNameInput.value === "" ) {
            productNameInput.classList.remove("valid");
            productNameInput.classList.add("invalid");
            isFormValid = false ;
        }
        else {
            productNameInput.classList.remove("invalid");
            productNameInput.classList.add("valid");
        }

        if( ! isFormValid ) return ;
        /// кінець валідації
    */
    // формуємо дані для передачі на бекенд
    const formData = new FormData() ;
    formData.append( "product-name", productNameInput.value ) ;
    formData.append( "product-price", productPriceInput.value ) ;
    formData.append( "product-description", productDescriptionInput.value ) ;
    if( productPhotoInput.files.length > 0 ) {
        formData.append( "product-photo", productPhotoInput.files[0] ) ;
    }

    // передаємо - формуємо запит
    fetch( window.location.href, { method: 'POST', body: formData } )
        .then( r => r.json() )
        .then( j => {
            console.log(j);
            /* if( j.status == 1 ) {  // реєстрація успішна
                alert( 'реєстрація успішна' ) ;
                window.location = '/' ;  // переходимо на головну сторінку
            }
            else {  // помилка реєстрації (повідомлення - у полі message)
                alert( j.data.message ) ;
            } */
        } ) ;
}