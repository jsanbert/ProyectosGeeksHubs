
new Vue ({
  el: '#app',

  data () {
    return {
      name: 'Bitcoin',
      img: 'https://vuejs.org/images/logo.png',

      changePercent: 10,

      price: 9400,

      value: 0,

      prices: [8400, 7900, 9000, 9400, 10000, 12000, 15900],

      pricesWithDays: [
        { day: 'lunes', value: 8400 },
        { day: 'martes', value: 7900 },
        { day: 'miércoles', value: 9000 },
        { day: 'jueves', value: 9400 },
        { day: 'viernes', value: 10000 },
        { day: 'sábado', value: 12000 },
        { day: 'domingo', value: 15900 },
  ],
  
  showPrices: false,

    }
  },

  methods: {
    toggleShowPrices () {
      this.showPrices = !this.showPrices
    }
  },

  computed: {
    convertedValue () {
      if (!this.value){
        return 0
      }

      return this.value/this.price

    }
  }


})
