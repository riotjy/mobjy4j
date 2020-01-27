# mobjy for Java

A collection of utilities to generate java classes and converters from a JSON or YAML data model.

NOTE:
The project is in an early stage. Please be kind to it! ;-)

TO CONTRIBUTORS:
You are welcome to contribute if you're interested and agree to ApL2.0.
Contact Alex for access.

## What does it currently do

RUNNING INSTRUCTIONS:

Run class "Mobjy" with parameter -i set to full path to input YAML file
(see examples/example.yaml in sister project mobjyArch)

It turns this:
```
DervClassOne:   #creates class DervClassOne extending BaseClass
  extends: BaseClass  
 intOneDervOne: int
  strOneDervOne: string
  mapOneDervOne:
    references: RefClassOne
    collection: hashMap
```    
into this:
```
public  class DervClassOne extends BaseClass {

  public Integer intOneDervOne;

  public Integer getIntOneDervOne() {
    return this.intOneDervOne;
  }

  public void setIntOneDervOne(Integer intOneDervOne) {
    this.intOneDervOne = intOneDervOne;
  }


  public String strOneDervOne;

  public String getStrOneDervOne() {
    return this.strOneDervOne;
  }

  public void setStrOneDervOne(String strOneDervOne) {
    this.strOneDervOne = strOneDervOne;
  }


  public HashMap<String, RefClassOne> mapOneDervOne = new HashMap<>();

  public int sizeOfMapOneDervOne() {
    return this.mapOneDervOne.size();
  }

  public RefClassOne getFromMapOneDervOne(String key) {
    return this.mapOneDervOne.get(key);
  }

  public void putToMapOneDervOne(String key, RefClassOne mapOneDervOne) {
    this.mapOneDervOne.put(mapOneDervOne);
  }

  public Set<String> keySetOfMapOneDervOne() {
    return this.mapOneDervOne.keySet();
  }
}
```