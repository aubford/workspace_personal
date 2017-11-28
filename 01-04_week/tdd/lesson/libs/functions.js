module.exports = {

  testFunc: function() {
    return 'world hello'
  },

  findMin: function(persona, personb){
    if(persona.age < personb.age) {
      return persona.name
    }else if (persona.age === personb.age){
      return 'They are the same age'
    }else{
      return personb.name
    }
  }

}
