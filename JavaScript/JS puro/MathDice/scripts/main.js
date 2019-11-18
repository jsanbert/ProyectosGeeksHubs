//funciones auxiliares
var numeroAleatorio = function(min, max) {
    return (Math.round(Math.random() * (max - min) + min));
};

// carga juego
var numeroDados = 5;
var x = 3; //x = numero de dados con valores del 1-3
let dados = [];
for(let i = 0; i < numeroDados; i++) {
    dados[i] = Object.create(Dado);
    if(i < (x - 1))
        dados[i].crearDado(6, 1, 3, -1);
    else
        dados[i].crearDado(6, 1, 6, -1);
}

let dodecaedro = Object.create(Dado);
dodecaedro.crearDado(12, 1, 12, -1);

var lanzarDados = function() {
    let element;
    for(let i = 0; i < dados.length; i++) {º
        dados[i].lanzarDado();
        element = document.getElementById("resDado" + (i + 1));
        element.innerHTML = dados[i].getValor();
    }
    dodecaedro.lanzarDado();
    element = document.getElementById("resDodecaedro");
    element.innerHTML = dodecaedro.getValor();

    //mostrar resto del juego y habilitar botones
    document.getElementById("seccion-calculo").style.display = "block";
    habilitarBotones(true);
};

// variables globales de elementos del dom, y event listeners de botones
var elementExpresion = document.getElementById("expresion");
var elementResultado = document.getElementById("resultado");
var dadoSeleccionado = false;
var operadorSeleccionado = true; // lo ponemos a true para que no se empiece insertando un operador
var dadosRestantes = numeroDados; // contador decremental
var clickDado = function(boton) {
    if(dadoSeleccionado) {
        alert("Ahora selecciona un operador");
        return;
    }
    let indice = boton.innerHTML.slice(-1) - 1; //si p.ej. "Dado 3" -> indice = 2
    elementExpresion.innerHTML += dados[indice].getValor();
    boton.disabled = true; // desactivamos boton
    dadosRestantes--;
    dadoSeleccionado = true;
    operadorSeleccionado = false;
}

var clickOperador = function(boton) {
    if(operadorSeleccionado) {
        alert("Ahora selecciona un dado");
        return;
    }
    else if(dadosRestantes == 0) {
        alert("Ya no quedan dados");
        return;
    }
    elementExpresion.innerHTML += boton.innerHTML;
    dadoSeleccionado = false;
    operadorSeleccionado = true;
}
var clickCalcular = function() {
    let expresion = elementExpresion.innerHTML;
    let result;
    try {
        eval(expresion);
    }
    catch(e) { 
        alert("Error en la operación, revisa que esté bien escrita")
    }
    if(eval(expresion) == dodecaedro.getValor()) {
        elementResultado.innerHTML = "¡Correcto!";
        elementResultado.style.color = "green";
    }
    else {
        elementResultado.innerHTML = "¡Incorrecto!";
        elementResultado.style.color = "red";
    }
}
var clickReset = function() {
    dadoSeleccionado = false;
    operadorSeleccionado = true; // ponemos a true para no insertar operador al principio
    dadosRestantes = numeroDados;

    for(let i = 1; i <= 5; i++) 
        document.getElementById("botonDado" + i).disabled = false;

    elementExpresion.innerHTML = "";
    elementResultado.innerHTML = "";
}

var habilitarBotones = function(valor) {
    var botones = document.getElementsByTagName("button");
    for(b in botones) {
        if(b.id != "botonLanzar")
            b.disabled = !valor;
    }
}

//ejecutado al cargar el juego
habilitarBotones(false);