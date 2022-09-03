$(function () {
    // let jsonProperties = JSON.parse(properties);
    read(properties.listaCampos);
});

function read(obj) {
    for (let k in obj) {
        if (obj[k] instanceof Object) {
            console.log(obj[k]);
            loadInput(obj[k])
        }
    }
}

function loadInput(obj) {
    if (obj.valorDefault !== null && obj.valorDefault !== '')
        $('#' + obj.idCampo).prop('value', obj.valorDefault);

    if (obj.expresionRegular !== null && obj.expresionRegular !== '')
        $('#' + obj.idCampo).prop('pattern', obj.expresionRegular);

    if (obj.minimoCaracteres !== null && obj.minimoCaracteres !== '')
        $('#' + obj.idCampo).attr('minlength', 45);

    if (obj.maximoCaracteres !== null && obj.maximoCaracteres !== '')
        $('#' + obj.idCampo).prop('maxlength', obj.maximoCaracteres);

    if (obj.readOnly)
        $('#' + obj.idCampo).prop('readonly', true);

    if (obj.requerido)
        $('#' + obj.idCampo).prop('required', true);

    let div = document.getElementById('div' + obj.idCampo);
    div.innerHTML = obj.mensajeError;
}


// Ejemplo de JavaScript inicial para deshabilitar el envío de formularios si hay campos no válidos
(function () {
    'use strict'

    // Obtener todos los formularios a los que queremos aplicar estilos de validación de Bootstrap personalizados
    let forms = document.querySelectorAll('.needs-validation')

    // Bucle sobre ellos y evitar el envío
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()