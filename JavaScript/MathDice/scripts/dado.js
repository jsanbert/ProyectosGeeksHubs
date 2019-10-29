const Dado = {
    lados: -1,
    valorMin: -1,
    valorMax: -1,
    valor: -1,

    //constructor
    crearDado: function(lados, valorMin, valorMax, valor) {
        this.lados = lados;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
        this.valor = valor;
    },

    //getters
    getLados: function() { return this.lados; },
    getValorMin: function() { return this.valorMin; },
    getValorMax: function() { return this.valorMax; },
    getValor: function() { return this.valor; },

    //setters
    setLados: function() { return this.lados; },
    setValorMin: function() { return this.valorMin; },
    setValorMax: function() { return this.valorMax; },
    setValor: function() { return this.valor; },

    //metodos de "clase"
    lanzarDado: function() { this.valor = numeroAleatorio(this.valorMin, this.valorMax); }
};