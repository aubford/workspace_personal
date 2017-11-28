var myFunctions = require("../libs/functions")

describe("My Functions", function(){

    it("returns 'world hello'", function(){

        expect(myFunctions.testFunc()).toEqual('world hello')


    })

})


describe("#findMin", function(){

    it('given two people objects, returns the name of youngest person', function(){

      var person1 = {
        age:30,
        name: "Howard"
      }
      var person2 = {
        age: 23,
        name: "Sarah"
      }

      expect(myFunctions.findMin(person1, person2)).toEqual("Sarah")

    })

    it("Given two peole objects, when both are the same age it returns they are the same age",function(){
      var person1 = {
        age:33,
        name: "Joe"
      }
      var person2 = {
        age: 33,
        name: "Jerah"
      }
      expect(myFunctions.findMin(person1,person2)).toEqual("They are the same age")



    })



})
